package com.example.huynhthanhnha.myapplication.form;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class Table {
    int idTable;
    int numOfPeople;
    Set<Bill> listBill;

    public Table(int idTable, int numOfPeople) {
        this.idTable = idTable;
        this.numOfPeople = numOfPeople;
        this.listBill = new HashSet<Bill>();
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public  void addBill(Bill bill){
        this.listBill.add(bill);
    }
}
