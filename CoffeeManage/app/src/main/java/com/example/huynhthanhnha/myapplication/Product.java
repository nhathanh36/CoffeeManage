package com.example.huynhthanhnha.myapplication;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class Product {
    String ProductName;
    float Price;
    String Unit;

    public Product(String productName, float price, String unit) {
        ProductName = productName;
        Price = price;
        Unit = unit;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
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
}
