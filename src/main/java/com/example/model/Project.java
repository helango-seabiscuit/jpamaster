package com.example.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by helangovan on 10/23/16.
 *
 * create table project(
 id integer primary key auto_increment,
 name varchar(100)
 );

 */
@Entity
@Table(name = "project")
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Collection<Employee> employees;

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
}
