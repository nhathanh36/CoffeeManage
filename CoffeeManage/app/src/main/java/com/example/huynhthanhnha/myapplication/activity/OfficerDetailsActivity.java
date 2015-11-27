package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductDetailsAdapter;
import com.example.huynhthanhnha.myapplication.adapter.OfficerDetailsAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Officer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 23/11/2015.
 */
public class OfficerDetailsActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Officer> listOfficer = new ArrayList<Officer>();
    ListView lOfficer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_officer);

        lOfficer = (ListView) findViewById(R.id.listViewOfficer);
        conn.Open();
        listOfficer = conn.getListOfficer();
        conn.Close();

        OfficerDetailsAdapter adapter = new OfficerDetailsAdapter(this, listOfficer);
        lOfficer.setAdapter(adapter);
        lOfficer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.print("PHIPHI");
            }
        });
    }
}
