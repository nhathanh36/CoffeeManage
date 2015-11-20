package com.example.huynhthanhnha.myapplication.form;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ProductDetails {
    int unitSales;
    Product product;
    Bill bill;

    public ProductDetails(Product product, int unitSales) {
        this.unitSales = unitSales;
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
