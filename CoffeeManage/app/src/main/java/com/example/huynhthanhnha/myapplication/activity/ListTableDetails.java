package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_details);
        IdTable = getIntent().getExtras().getInt("IdTable");

        TextView numTable = (TextView) findViewById(R.id.tableNumber);
        numTable.setText("Bàn " + String.valueOf(IdTable));

        createListProduct();
    }

    public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProductDetails);
        conn.Open();
        listProductDetails = conn.getListProductOfTable(IdTable);
        conn.getListProductOfGroup(1);
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
