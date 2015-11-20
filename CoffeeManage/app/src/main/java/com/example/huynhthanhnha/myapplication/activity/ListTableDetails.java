package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
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
    int IdTable;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_details);
        IdTable = getIntent().getExtras().getInt("IdTable");

        TextView numTable = (TextView) findViewById(R.id.tableNumber);
        numTable.setText("Bàn " + String.valueOf(IdTable));

        createListProduct();

        btnAdd = (Button) findViewById(R.id.btnMonmoi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conn.Open();
                System.out.println("=====THEM SAN PHAM CAFE CHO BAN====");
                Product product = new Product(1,"Cafe đá", "Ly");
                System.out.println("=====TEN BAN=======================" + IdTable);
                conn.InsertProductForBill(product, 2, IdTable);
                conn.Close();

                //Refresh data
                createListProduct();
            }
        });
    }

    public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProductDetails);
        conn.Open();
        listProductDetails = conn.getListProductOfTable(IdTable);
        conn.TestUpdate();
        //conn.TestBill();
        conn.getIDBill();
        conn.Close();
        listView.setAdapter(new ListProductDetailsAdapter(this, listProductDetails));

        // Handle when user click on item in list view
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // get table user clicked
                Product entry = (Product) adapterView.getItemAtPosition(position);
            }
        });*/
    }
}
