package com.example.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by helangovan on 10/24/16.
 create table vacation(
 emp_id bigint,
 startdate date,
 days_abs integer,
 foreign key (emp_id) references Employee(id)
 );

 */
@Embeddable
public class VacationEntry {

    @Temporal(TemporalType.DATE)
    private Calendar startdate;

    @Column(name = "DAYS")
    private int daysTaken;

    public Calendar getStartdate() {
        return startdate;
    }

    public void setStartdate(Calendar startDate) {
        this.startdate = startDate;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }
}
