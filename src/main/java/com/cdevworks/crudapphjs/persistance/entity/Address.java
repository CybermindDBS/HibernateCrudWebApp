package com.cdevworks.crudapphjs.persistance.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    String state;
    String city;


    public Address(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public Address() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
