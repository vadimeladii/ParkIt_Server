package com.free.parking.config;

import com.free.parking.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.free.parking.notification.AndroidPushNotificationsService;
import com.free.parking.repository.ParkingRepository;
import com.free.parking.service.ParkingService;
import com.free.parking.service.impl.ParkingServiceImpl;
import com.free.parking.webservice.impl.ParkingControllerImpl;

/**
 * Created by veladii on 6/8/17.
 */
@Configuration
public class ParkingControllerConfig {

    @Autowired
    public ParkingRepository parkingRepository;

    @Autowired
    public AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    public MobileRepository mobileRepository;

    @Bean
    public ParkingService parkingService(){
        return new ParkingServiceImpl(androidPushNotificationsService, parkingRepository, mobileRepository);
    }

    @Bean
    public ParkingControllerImpl parkingControllerImpl() {
        return new ParkingControllerImpl(parkingService());
    }
}
