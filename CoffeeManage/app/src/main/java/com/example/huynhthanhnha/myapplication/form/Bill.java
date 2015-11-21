package com.example.huynhthanhnha.myapplication.form;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class Bill {
    int billID;
    Calendar calendar;
    Set<ProductDetails> listDetailProduct;
    Table table;
    boolean state;
    Officer officer;

    public Bill(int billID, Calendar calendar, Table table, boolean state, Officer officer) {
        this.billID = billID;
        this.calendar = calendar;
        this.table = table;
        this.state = state;
        this.officer = officer;
        this.listDetailProduct = new HashSet<ProductDetails>();
    }

    public Bill(int billID, Calendar calendar) {
        this.billID = billID;
        this.calendar = calendar;
        this.listDetailProduct = new HashSet<ProductDetails>();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
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

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }
}
