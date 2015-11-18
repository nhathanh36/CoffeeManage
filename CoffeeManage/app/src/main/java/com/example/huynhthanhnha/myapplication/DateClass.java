package com.example.huynhthanhnha.myapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class DateClass {
    Calendar date;
    Set<ListPrice> listPrices;

    public DateClass(Calendar date) {
        this.date = date;
        listPrices = new HashSet<ListPrice>();
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Set<ListPrice> getListPrices() {
        return listPrices;
    }

    public void setListPrices(Set<ListPrice> listPrices) {
        this.listPrices = listPrices;
    }

     public void addListPrices(ListPrice listPrice){
         this.listPrices.add(listPrice);
     }
}
