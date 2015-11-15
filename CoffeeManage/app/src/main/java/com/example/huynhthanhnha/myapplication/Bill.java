package com.example.huynhthanhnha.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class Bill {
    String date;
    Set<ProductDetails> listDetailProduct;
    Table table;
    boolean state;
    Officer officer;

    public Bill(String date) {
        this.date = date;
        this.listDetailProduct = new HashSet<ProductDetails>();
        this.state = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<ProductDetails> getListDetailProduct() {
        return listDetailProduct;
    }

    public void setListDetailProduct(Set<ProductDetails> listDetailProduct) {
        this.listDetailProduct = listDetailProduct;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public void addListDetailProduct (ProductDetails productDetails) {
        this.listDetailProduct.add(productDetails);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
