package com.example.huynhthanhnha.myapplication.form;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class GroupProduct {
    int GroupID;
    String GroupProductName;
    String Details;
    Set<Product> listProduct;

    public GroupProduct() {
    }

    public GroupProduct(int groupID, String groupProductName) {
        GroupID = groupID;
        GroupProductName = groupProductName;
        this.listProduct = new HashSet<Product>();
    }

    public GroupProduct(int groupID, String groupProductName, String details) {
        GroupID = groupID;
        GroupProductName = groupProductName;
        Details = details;
        this.listProduct = new HashSet<Product>();
    }


    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getProductName() {
        return GroupProductName;
    }

    public void setProductName(String productName) {
        GroupProductName = productName;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getGroupProductName() {
        return GroupProductName;
    }

    public Set<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void setGroupProductName(String groupProductName) {
        GroupProductName = groupProductName;
    }

    public void addProduct(Product product) {
        this.listProduct.add(product);
    }

    public void deleteProduct(Product product) {
        this.listProduct.remove(product);
    }
}
