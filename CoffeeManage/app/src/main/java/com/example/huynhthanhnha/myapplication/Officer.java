package com.example.huynhthanhnha.myapplication;

import java.util.Date;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class Officer extends User {
    Date WorkDate;
    public Officer(String username, String password, String name, String CMND) {
        super(username, password, name, CMND);
    }

    public Date getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(Date workDate) {
        WorkDate = workDate;
    }
}
