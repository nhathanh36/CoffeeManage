package com.example.huynhthanhnha.myapplication;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ProductDetails {
    int unitSales;
    Product product;

    public ProductDetails(int unitSales, Product product) {
        this.unitSales = unitSales;
        this.product = product;
    }

    public int getUnitSales() {
        return unitSales;
    }

    public void setUnitSales(int unitSales) {
        this.unitSales = unitSales;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
