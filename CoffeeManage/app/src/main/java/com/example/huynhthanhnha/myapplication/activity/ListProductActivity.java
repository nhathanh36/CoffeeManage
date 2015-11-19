package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 18-Nov-15.
 */
public class ListProductActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product);

        createListProduct();
    }

    public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProduct);
        conn.Open();
        listProduct = conn.getListProduct();
        conn.Close();

        System.out.println("List price: " + listProduct.get(0).getListPrice().size());
        listView.setAdapter(new ListProductAdapter(this, listProduct));

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
