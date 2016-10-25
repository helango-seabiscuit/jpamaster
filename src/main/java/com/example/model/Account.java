package com.example.model;

import javax.persistence.*;

/**
 * Created by helangovan on 10/13/16.
 */
@Entity
@Access(AccessType.FIELD)
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private  String accName;
//    @Column(name = "acc_no")
//    private  String accName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "acc_no")
    public String getAccNameForRecord() {
        return accName;
    }

    public void setAccNameForRecord(String aname) {
        this.accName = aname;
    }
}
