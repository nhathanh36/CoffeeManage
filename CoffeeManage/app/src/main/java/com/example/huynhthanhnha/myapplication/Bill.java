package com.example.huynhthanhnha.myapplication;

import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class Bill {
    String date;
    Set<ProductDetails> listDetailProduct;
    Table table;
    Officer officer;

    public Bill(String date, Set<ProductDetails> listDetailProduct) {
        this.date = date;
        this.listDetailProduct = listDetailProduct;
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
}
