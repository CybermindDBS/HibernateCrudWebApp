package com.cdevworks.crudapphjs.persistance.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class FullTimeEmployee extends Employee {

    int salary;


    public FullTimeEmployee(String name, Address address, LocalDate doj, int salary) {
        super(name, address, doj);
        this.salary = salary;
    }

    public FullTimeEmployee() {
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + address.state +
                ", city=" + address.city +
                ", pay=" + salary+" (monthly)"+
                ", doj=" + doj +
                ", version=" + version +
                ", last_updated=" + last_updated +
                '}';
    }
}
