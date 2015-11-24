package com.example.huynhthanhnha.myapplication.form;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class DateClass {
    Date date;
    Set<ListPrice> listPrices;

    public DateClass() {
        listPrices = new HashSet<ListPrice>();
    }

    public DateClass(Date date) {
        this.date = date;
        listPrices = new HashSet<ListPrice>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
