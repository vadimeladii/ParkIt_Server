package com.free.parking.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by veladii on 6/8/17.
 */
public interface ParkingService {
    void increment();

    void decrement();

    Integer counter();

    ResponseEntity<String> send();

    void addToken(String token);

    Integer place();

    void editPlace(Integer place);
}
