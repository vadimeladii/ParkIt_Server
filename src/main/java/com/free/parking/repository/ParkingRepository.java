package com.free.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.free.parking.entity.Parking;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;

/**
 * Created by veladii on 6/8/17.
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Modifying
    @Query(value = "UPDATE Parking p SET p.counter = (p.counter + 1) WHERE p.id = 1 and p.counter < p.place")
    void increment();

    @Modifying
    @Query(value = "UPDATE Parking p SET p.counter = (p.counter - 1) WHERE p.id = 1 and p.counter > 0")
    void decrement();

    @Modifying
    @Query(value = "UPDATE Parking p SET p.place = :place WHERE p.id = 1")
    Integer editPlace(@Param("place") Integer place);
}
