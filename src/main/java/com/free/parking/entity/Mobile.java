package com.free.parking.entity;

import javax.persistence.*;

/**
 * Created by veladii on 6/9/17.
 */
@Entity
@Table(name = "mobile")
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    public Mobile() {
    }

    public Mobile(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
