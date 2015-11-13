package com.example.huynhthanhnha.myapplication;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class GroupProduct {
    String GroupProductName;
    String Details;

    public GroupProduct(String groupProductName) {
        GroupProductName = groupProductName;
    }

    public GroupProduct(String groupProductName, String details) {
        GroupProductName = groupProductName;
        Details = details;
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
}
