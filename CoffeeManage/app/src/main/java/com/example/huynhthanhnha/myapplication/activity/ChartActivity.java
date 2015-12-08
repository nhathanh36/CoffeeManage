package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by NguyenThanh on 08/12/2015.
 */
public class ChartActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    RadioButton radioProduct;
    RadioButton radioBill;
    BarChart bc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_bill);

        bc = (BarChart) findViewById(R.id.chart);
        radioProduct = (RadioButton) findViewById(R.id.radioProduct);
        radioBill = (RadioButton) findViewById(R.id.radioBill);

        Calendar start = Calendar.getInstance();
        start.set(2015, 10, 1);
        conn.Open();
        conn.getBillFromDateToDate(start, Calendar.getInstance());
        conn.Close();

        DataChartProduct();

        radioProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioProduct.setChecked(true);
                radioBill.setChecked(false);
                DataChartProduct();
            }
        });

        radioBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioProduct.setChecked(false);
                radioBill.setChecked(true);
                DataChartBill();
            }
        });




    }

    private void DataChartProduct(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "Quanlity sales");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");



        BarData data = new BarData(labels, dataset);
        bc.setData(data);
        data.notifyDataChanged();

    }

    private void DataChartBill(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "Quanlity sales");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");

        BarData data = new BarData(labels, dataset);
        bc.setData(data);
        data.notifyDataChanged();


    }
}
