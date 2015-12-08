package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListGroupAdapter;
import com.example.huynhthanhnha.myapplication.adapter.ListPriceAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.GroupProduct;
import com.example.huynhthanhnha.myapplication.form.ListPrice;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Huynh Thanh Nha on 05-Dec-15.
 */
public class ShowPriceActivity extends Activity {
    ListView lvPrice;
    TextView tvUserName;
    TextView tvAuth;
    DatabaseConnection conn = new DatabaseConnection();
    List<ListPrice> listPrices = new ArrayList<ListPrice>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_price);

        lvPrice = (ListView) findViewById(R.id.lvPrice);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvAuth = (TextView) findViewById(R.id.tvAuth);
        tvUserName.setText("Tên: " + Login.getUser().getName().toString());
        tvAuth.setText("Cấp: " + Login.getUser().getPer().getDetails().toString());

        conn.Open();
        listPrices = conn.getAllListPrice();
        conn.Close();

        final ListPriceAdapter priceAdapter = new ListPriceAdapter(ShowPriceActivity.this, listPrices);
        lvPrice.setAdapter(priceAdapter);
        lvPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ListPrice price = (ListPrice) parent.getItemAtPosition(position); // get Item in position

                // Dialog show list edit, delete,...
                PopupMenu popupMenu = new PopupMenu(ShowPriceActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itemDetailPrice:
                                Intent intent = new Intent(ShowPriceActivity.this, ShowDetailPrice.class);
                                intent.putExtra("productName", price.getProduct().getProductName());
                                startActivity(intent);

                                return true;
                        }
                        return true;

                    }
                });
                popupMenu.inflate(R.menu.popup_menu_price);
                popupMenu.show();
            }
        });
    }
}
