package com.example.model;

import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by helangovan on 10/22/16.
 */

/**
 * create sequence dep_seq
 *     MINVALUE 1
 *     START WITH 1
 *     INCREMENT BY 50
 *
 *     But MySql dont have sequence creation instead use AUTO_INCREMENT
 *     alter table department add column id integer auto_increment primary key;
 */

@Table(name = "department")
@Entity
@Access(AccessType.FIELD)
public class Department {


    //@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    @OrderBy("firstName ASC ")
    private Collection<Employee> employees;

    @Embedded
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
