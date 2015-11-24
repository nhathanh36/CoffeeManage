package com.example.huynhthanhnha.myapplication;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by NguyenThanh on 24/11/2015.
 */
public class MyObject implements Comparable<MyObject> {

    private Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    @Override
    public int compareTo(MyObject o) {
        if (getDateTime() == null || o.getDateTime() == null)
            return 0;
        return getDateTime().compareTo(o.getDateTime());
    }
}