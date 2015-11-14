package com.example.huynhthanhnha.myapplication;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class DateClass {
    Date date;
    Set<ListPrice> listPrices;

    public DateClass(Date date) {
        this.date = date;
        listPrices = new HashSet<ListPrice>();
    }


}
