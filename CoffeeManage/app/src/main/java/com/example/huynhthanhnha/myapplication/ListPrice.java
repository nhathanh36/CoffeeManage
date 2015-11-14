package com.example.huynhthanhnha.myapplication;

import java.util.Date;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ListPrice {
    long price;
    String details;
    DateClass dateClass;
    Product product;

    public ListPrice(long price) {
        this.price = price;
        this.dateClass = new DateClass(new Date());
        //this.product = new Product();
    }

    public ListPrice(String details, long price) {
        this.details = details;
        this.price = price;
        this.dateClass = new DateClass(new Date());
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
