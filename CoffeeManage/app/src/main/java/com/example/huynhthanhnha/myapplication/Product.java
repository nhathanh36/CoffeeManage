package com.example.huynhthanhnha.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class Product {
    String ProductName;
    String Unit;
    Set<ListPrice> listPrice;

    public Product(String productName, String unit) {
        ProductName = productName;
        Unit = unit;
        this.listPrice = new HashSet<ListPrice>();
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Set<ListPrice> getListPrice() {
        return listPrice;
    }

    public void setListPrice(Set<ListPrice> listPrice) {
        this.listPrice = listPrice;
    }

    public void addPrice(ListPrice listPrice){
        this.listPrice.add(listPrice);
    }
}
