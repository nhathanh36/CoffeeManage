package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.db4o.ObjectSet;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.Table;
import com.example.huynhthanhnha.myapplication.adapter.ListTableAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class GridTableActivity extends Activity {
    List<Table> listTable = new ArrayList<Table>();
    DatabaseConnection conn = new DatabaseConnection();
    ObjectSet<Table> obsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_table);
        createGridTable();
    }

    private void createGridTable() {
        GridView gridView = (GridView) findViewById(R.id.gvListTable);

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
                // get table user clicked
                //khong co lay id ma no lay doi tuong ak, getItemPosition(tai vi tri nao)
               // dong nay nek a: Table entry = (Table) adapterView.getItemAtPosition(position);
                Table entry = (Table) adapterView.getItemAtPosition(position);
                System.out.println("ID ban: " + entry.getIdTable());
                Intent intent = new Intent(GridTableActivity.this, ListTableDetails.class);
                intent.putExtra("IdTable", entry.getIdTable());
                startActivity(intent);
            }
        });
    }
}
