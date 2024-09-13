package com.cdevworks.crudapphjs.persistance.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class PartTimeEmployee extends Employee {

    int hourly_wage;

    public PartTimeEmployee(String name, Address address, LocalDate doj, int hourly_wage) {
        super(name, address, doj);
        this.hourly_wage = hourly_wage;
    }

    public PartTimeEmployee() {

    }

    public int getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(int hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + address.state +
                ", city=" + address.city +
                ", pay=" + hourly_wage+" (hourly)"+
                ", doj=" + doj +
                ", version=" + version +
                ", last_updated=" + last_updated +
                '}';
    }
}
