package com.example.huynhthanhnha.myapplication.form;

import java.util.Calendar;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ListPrice {
    long price;
    DateClass dateClass;
    Product product;

    public ListPrice() {
    }

    public ListPrice(DateClass date, Product prod, long price) {
        this.price = price;
        this.dateClass = date;
        this.product = prod;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public DateClass getDateClass() {
        return dateClass;
    }

    public void setDateClass(DateClass dateClass) {
        this.dateClass = dateClass;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
