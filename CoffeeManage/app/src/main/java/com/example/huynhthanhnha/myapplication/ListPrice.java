package com.example.huynhthanhnha.myapplication;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ListPrice {
    long price;
    DateClass dateClass;
    Product product;

    public ListPrice(Calendar cal, Product prod, long price) {
        this.price = price;
        this.dateClass = new DateClass(cal);
        this.product = prod;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
