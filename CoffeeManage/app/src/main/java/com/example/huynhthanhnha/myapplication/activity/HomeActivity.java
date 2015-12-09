package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;

import lecho.lib.hellocharts.model.Line;

/**
 * Created by Huynh Thanh Nha on 12-Nov-15.
 */
public class HomeActivity extends Activity {
    LinearLayout lnOfficer;
    LinearLayout report;
    LinearLayout statistic;
    TextView textViewName;
    ImageView imgLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        textViewName = (TextView) findViewById(R.id.textShowName);
        textViewName.setText("Chào, " + Login.getUser().getName());

        lnOfficer = (LinearLayout) findViewById(R.id.linearListOfficer);
        statistic = (LinearLayout) findViewById(R.id.linearStatistic);
        report = (LinearLayout) findViewById(R.id.linearReport);
        imgLogout = (ImageView) findViewById(R.id.imgLogout);

        lnOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OfficerDetailsActivity.class));
            }
        });

        statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChartActivity.class));
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BillActivity.class));
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
