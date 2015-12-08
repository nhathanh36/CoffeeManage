package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListPriceAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ListPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 08-Dec-15.
 */
public class ShowDetailPrice extends Activity {
    ListView lvPrice;
    String productName;
    DatabaseConnection conn = new DatabaseConnection();
    List<ListPrice> listPrices = new ArrayList<ListPrice>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_price);

        lvPrice = (ListView) findViewById(R.id.lvPrice);

        productName = getIntent().getExtras().getString("productName");

        conn.Open();
        listPrices = conn.getListPriceOfProduct(productName);
        conn.Close();

        final ListPriceAdapter priceAdapter = new ListPriceAdapter(ShowDetailPrice.this, listPrices);
        lvPrice.setAdapter(priceAdapter);
    }
}
