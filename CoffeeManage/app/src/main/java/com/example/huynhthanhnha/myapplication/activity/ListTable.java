package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.db4o.ObjectSet;
import com.example.huynhthanhnha.myapplication.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.Table;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class ListTable extends Activity {
    List<Table> listTable = new ArrayList<Table>();
    DatabaseConnection conn = new DatabaseConnection();
    ObjectSet<Table> obsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_table);
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
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // get table user clicked
                Table entry = (Table) adapterView.getItemAtPosition(position);
            }
        });*/
    }
}
