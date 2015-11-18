package com.example.huynhthanhnha.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class GroupProduct {
    int GroupID;
    String GroupProductName;
    Set<Product> listProduct;

    public GroupProduct(int groupID, String groupProductName) {
        GroupID = groupID;
        GroupProductName = groupProductName;
        this.listProduct = new HashSet<Product>();
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
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
