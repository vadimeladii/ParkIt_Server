package com.free.parking.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.free.parking.entity.Mobile;
import com.free.parking.repository.MobileRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.free.parking.notification.AndroidPushNotificationsService;
import com.free.parking.notification.FirebaseResponse;
import com.free.parking.repository.ParkingRepository;
import com.free.parking.service.ParkingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by veladii on 6/8/17.
 */
public class ParkingServiceImpl implements ParkingService {

    private final AndroidPushNotificationsService androidPushNotificationsService;

    private final ParkingRepository parkingRepository;

    private final MobileRepository mobileRepository;

    public ParkingServiceImpl(AndroidPushNotificationsService androidPushNotificationsService, ParkingRepository parkingRepository,
            MobileRepository mobileRepository) {
        this.androidPushNotificationsService = androidPushNotificationsService;
        this.parkingRepository = parkingRepository;
        this.mobileRepository = mobileRepository;
    }

    @Override
    @Transactional
    public void increment() {
        parkingRepository.increment();
        int counter = counter();
        if (counter < 10) {
            send();
        }
    }

    @Override
    @Transactional
    public void decrement() {
        parkingRepository.decrement();
    }

    @Override
    public Integer counter() {
        return (place() - parkingRepository.findOne((long) 1).getCounter());
    }

    @Override
    public void addToken(String token) {
        final boolean[] existToken = { false };
        mobileRepository.findAll().stream().map(Mobile::getToken).forEach(dbToken -> {
            if (dbToken.equals(token))
                existToken[0] = true;
        });
        if (!existToken[0])
            mobileRepository.save(new Mobile(token));
    }

    @Override
    public Integer place() {
        return parkingRepository.findOne((long) 1).getPlace();
    }

    @Override
    @Transactional
    public void editPlace(Integer place) {
        parkingRepository.editPlace(place);
    }

    @Override
    public ResponseEntity<String> send() {
        mobileRepository.findAll().forEach(mobile -> {
            sendNotification(mobile.getToken(), counter());
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<String> sendNotification(String token, Integer counter) {
        JSONObject body = new JSONObject();
        body.put("to", token);
        body.put("priority", "high");
        body.put("sound", "default");

        JSONObject notification = new JSONObject();
        notification.put("body", "There are " + (counter) + " places left");
        notification.put("title", "ParkIt");

        body.put("notification", notification);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                System.out.println("push notification sent ok!");
            } else {
                System.out.println("error sending push notifications: " + firebaseResponse.toString());
            }
            return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
    }

}
