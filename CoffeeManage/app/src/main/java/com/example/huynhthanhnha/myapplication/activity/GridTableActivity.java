package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.db4o.ObjectSet;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListTableAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;
import com.example.huynhthanhnha.myapplication.form.Table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class GridTableActivity extends Activity {
    List<Table> listTable = new ArrayList<Table>();
    String date = "";
    TextView tvDate;
    DatabaseConnection conn = new DatabaseConnection();
    ObjectSet<Table> obsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_table);
        //System.out.println("External" + this.getDir("data", 0) + "Enviroment: " + Environment.getDataDirectory());
        createGridTable();

    }

    private void createGridTable() {
        GridView gridView = (GridView) findViewById(R.id.gvListTable);
        Calendar currentDate = Calendar.getInstance();
        date =  "Ngày " + String.valueOf(currentDate.get(Calendar.DATE))+"/"+
                String.valueOf(currentDate.get(Calendar.MONTH)+1)+"/"+
                String.valueOf(currentDate.get(Calendar.YEAR));
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText(date);
        // get table in database
        //System.out.println("FILE: " + this.getFilesDir().toString());
        conn.Open();
        listTable = conn.getTable();
        conn.Close();

        gridView.setAdapter(new ListTableAdapter(this, listTable));



        // Handle when user click on item in grid view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Table tablePosition = (Table) adapterView.getItemAtPosition(position);
                PopupMenu popup = new PopupMenu(GridTableActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_gird_table, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popupGridEnter:
                                // get table user clicked
                                Intent intent = new Intent(GridTableActivity.this, ListTableDetails.class);
                                intent.putExtra("IdTable", tablePosition.getIdTable());
                                startActivity(intent);
                                break;
                            case R.id.popupGridMove:
                                Toast.makeText(GridTableActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
    }
}
