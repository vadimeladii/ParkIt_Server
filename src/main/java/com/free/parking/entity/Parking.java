package com.free.parking.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by veladii on 6/9/17.
 */
@Entity
@Table(name = "parking")
public class Parking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "counter")
    private Integer counter;

    @Column(name = "place")
    private Integer place;

    public Parking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", counter=" + counter +
                ", place=" + place +
                '}';
    }
}
