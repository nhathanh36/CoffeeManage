package com.example.huynhthanhnha.myapplication.form;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class Product {
    int ProductId;
    String ProductName;
    String Unit;
    Set<ListPrice> listPrice;
    GroupProduct groupProduct;
    Set<ProductDetails> listproductDetail;

    public Product(int productId, String productName, String unit) {
        ProductId = productId;
        Unit = unit;
        ProductName = productName;
        this.listPrice = new HashSet<ListPrice>();
        this.listproductDetail = new HashSet<ProductDetails>();
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Set<ListPrice> getListPrice() {
        return listPrice;
    }

    public void setListPrice(Set<ListPrice> listPrice) {
        this.listPrice = listPrice;
    }

    public Set<ProductDetails> getListproductDetail() {
        return listproductDetail;
    }

    public void setListproductDetail(Set<ProductDetails> listproductDetail) {
        this.listproductDetail = listproductDetail;
    }

    public void addPrice(ListPrice listPrice){
        this.listPrice.add(listPrice);
    }

    public void addProduct(ProductDetails productDetails) {
        this.listproductDetail.add(productDetails);
    }

    public GroupProduct getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(GroupProduct groupProduct) {
        this.groupProduct = groupProduct;
    }
}
