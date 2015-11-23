package com.example.huynhthanhnha.myapplication.form;

/**
 * Created by NguyenThanh on 14/11/2015.
 */
public class ProductDetails {
    int ProductDetailID;
    int unitSales;
    Product product;
    Bill bill;

    public ProductDetails(int ProductDetailID, int unitSales) {
        this.unitSales = unitSales;
        this.ProductDetailID = ProductDetailID;
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

    public int getProductDetailID() {
        return ProductDetailID;
    }

    public void setProductDetailID(int productDetailID) {
        ProductDetailID = productDetailID;
    }
}
