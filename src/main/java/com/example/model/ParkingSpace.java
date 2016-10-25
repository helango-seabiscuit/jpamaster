package com.example.model;

import javax.persistence.*;

/**
 * Created by helangovan on 10/22/16.
 *
 * create table parkingspace(
 id integer primary key auto_increment,
 lot integer,
 location varchar(100)
 );
 */
@Entity
@Table(name = "parkingspace")
public class ParkingSpace {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="lot")
    private int lot;

    @Column(name = "location")
    private String location;

    @OneToOne(mappedBy = "parkingSpace")//attribute name in owning side i.e. Employee
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
