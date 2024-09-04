package com.cdevworks.crudapphjs.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    Integer id;
    String name;
    Integer salary;
    String address;
    Integer supervisor_id;

    public Employee(Integer id, String name, Integer salary, String address, Integer supervisor_id) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.supervisor_id = supervisor_id;
    }

    public Employee() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(Integer supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", supervisor_id=" + supervisor_id +
                '}';
    }
}
