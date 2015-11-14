package com.example.huynhthanhnha.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class GroupProduct {
    String GroupProductName;
    String Details;
    Set<Product> listProduct;

    public GroupProduct(String groupProductName) {
        GroupProductName = groupProductName;
    }

    public GroupProduct(String groupProductName, String details) {
        GroupProductName = groupProductName;
        Details = details;
        this.listProduct = new HashSet<Product>();
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

    public void setGroupProductName(String groupProductName) {
        GroupProductName = groupProductName;
    }

    public void addProduct(Product product){
        this.listProduct.add(product);
    }
}
