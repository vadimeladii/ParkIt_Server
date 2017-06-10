package com.free.parking.webservice.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.free.parking.service.ParkingService;

/**
 * Created by veladii on 6/2/17.
 */
@RestController
@RequestMapping(value = "/api/parking")
public class ParkingControllerImpl {

    private final ParkingService parkingService;

    public ParkingControllerImpl(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @RequestMapping(value = "/test/", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public String test() {
        return "Hello Word";
    }

    @RequestMapping(value = "/increment/", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<Void> increment() {
        parkingService.increment();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/decrement/", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<Void> decrement() {
        parkingService.decrement();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/counter/", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<Integer> counter() {
        return ResponseEntity.ok(parkingService.counter());
    }

    @RequestMapping(value = "/add/token/{token}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<Void> addToken(@PathVariable("token") String token) {
        parkingService.addToken(token);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/send/", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<String> send() {
        return parkingService.send();
    }

}
