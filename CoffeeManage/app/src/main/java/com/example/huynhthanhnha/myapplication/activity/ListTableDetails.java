package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.AddProductDetailsAdaper;
import com.example.huynhthanhnha.myapplication.adapter.ListProductDetailsAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 19/11/2015.
 */
public class ListTableDetails extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<ProductDetails> listProductDetails = new ArrayList<ProductDetails>();
    List<Product> listProductGroup = new ArrayList<Product>();
    RelativeLayout relativeDetails;
    RelativeLayout relativeAdd;
    int IdTable;
    Button btnAdd;
    Button btnComplete;
    Button btnMonmoi;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_details);
        IdTable = getIntent().getExtras().getInt("IdTable");

        TextView numTable = (TextView) findViewById(R.id.tableNumber);
        numTable.setText("Bàn " + String.valueOf(IdTable));

        createListProduct();

        relativeDetails = (RelativeLayout) findViewById(R.id.relativeProductDetails);
        relativeAdd = (RelativeLayout) findViewById(R.id.relativeAddProduct);

        btnMonmoi = (Button) findViewById(R.id.btnMonmoi);
        btnMonmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeAdd.setVisibility(View.VISIBLE);
                relativeDetails.setVisibility(View.GONE);

//                conn.Open();
//                System.out.println("=====THEM SAN PHAM CAFE CHO BAN====");
//                Product product = new Product(1, "Cafe đá", "Ly");
//                Product pd2 = new Product(2,"Cafe sữa", "Ly");
//                System.out.println("=====TEN BAN=======================" + IdTable);
//                conn.InsertProductForBill(product, 2, IdTable);
//                conn.InsertProductForBill(pd2, 5, IdTable);
//                conn.Close();
                createListProduct();

            }
        });

        btnThanhToan = (Button) findViewById(R.id.btnThanhtoan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conn.Open();
                conn.updateStateForBill(IdTable);
                conn.Close();
                createListProduct();
            }
        });

        btnComplete = (Button) findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeAdd.setVisibility(View.GONE);
                relativeDetails.setVisibility(View.VISIBLE);
                createListProduct();
            }
        });

        createListProductGroup();
        createListProduct();

    }

    public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProductDetails);
        conn.Open();
        listProductDetails = conn.getListProductOfTable(IdTable);
        conn.Close();
        listView.setAdapter(new ListProductDetailsAdapter(this, listProductDetails));

        TextView countProduct = (TextView) findViewById(R.id.textTongsomon);
        countProduct.setText("Tổng số món: " + String.valueOf(listProductDetails.size()));
    }

    public void createListProductGroup() {
        ListView listProductDetailsForAdd = (ListView) findViewById(R.id.listProductDetailsForAdd);
        conn.Open();
        listProductGroup = conn.getListProduct();
        conn.getProductDetails();
        conn.Close();
        listProductDetailsForAdd.setAdapter(new AddProductDetailsAdaper(this, listProductGroup, IdTable));

    }
}
