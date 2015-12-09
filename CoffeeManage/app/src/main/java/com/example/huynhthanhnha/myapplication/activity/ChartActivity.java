package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.CalendarAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenThanh on 08/12/2015.
 */
public class ChartActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Product> listProduct = new ArrayList<Product>();
    RadioButton radioProduct;
    RadioButton radioBill;
    HorizontalBarChart bc;
    Button btnStartDate;
    Button btnEndDate;

    int[] intStartDate = new int[3];
    int[] intEndDate = new int[3];

    AlertDialog.Builder builder;
    public GregorianCalendar month, itemmonth;
    public CalendarAdapter adapter;
    public Handler handler;
    public ArrayList<String> items;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_bill);

        bc = (HorizontalBarChart) findViewById(R.id.chart);
        radioProduct = (RadioButton) findViewById(R.id.radioProduct);
        radioBill = (RadioButton) findViewById(R.id.radioBill);

        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);

        //Init data
        InitData();

        //Show chart product
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

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogCalendar(true);
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogCalendar(false);
            }
        });

    }

    private void InitData(){
        Calendar cal = Calendar.getInstance();
        intEndDate[0] = intStartDate[0] = cal.get(Calendar.DAY_OF_MONTH);
        intEndDate[1] = intStartDate[1] = cal.get(Calendar.MONTH)+1;
        intEndDate[2] = intStartDate[2] = cal.get(Calendar.YEAR);

        btnStartDate.setText(intStartDate[0] + "/" + intStartDate[1] + "/" + intStartDate[2]);
        btnEndDate.setText(intEndDate[0] + "/" + intEndDate[1] + "/" + intEndDate[2]);

        conn.Open();
        listProduct = conn.getListProduct();
        conn.Close();
    }

    private void DataChartProduct(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int i = 0;
        int numQuanltiy = 0;
        Calendar startDate = Calendar.getInstance();
        startDate.set(intStartDate[2], intStartDate[1]-1, intStartDate[0]);

        Calendar endDate = Calendar.getInstance();
        endDate.set(intEndDate[2], intEndDate[1]-1, intEndDate[0]);

        conn.Open();
        for(Product p: listProduct){
            numQuanltiy = conn.getQuanlitySalesOfProduct(p, startDate, endDate);
            entries.add(new BarEntry(numQuanltiy, i));
            labels.add(p.getProductName());
            i++;
        }
        conn.Close();

        BarDataSet dataset = new BarDataSet(entries, "Số lượng bán");
        //dataset.setColor(Color.rgb(0, 155, 0));
        dataset.setColors(ColorTemplate.PASTEL_COLORS);

        BarData data = new BarData(labels, dataset);
        bc.setData(data);
        bc.animateY(2000);
        bc.setDescription("");
        data.notifyDataChanged();
    }

    private void DataChartBill(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int i = 0;
        int sumRevenue = 0;
        Calendar startDate = Calendar.getInstance();
        startDate.set(intStartDate[2], intStartDate[1]-1, intStartDate[0]);

        Calendar endDate = Calendar.getInstance();
        endDate.set(intEndDate[2], intEndDate[1]-1, intEndDate[0]);

        conn.Open();
        for(Product p: listProduct){
            sumRevenue = conn.getRevenueSalesOfProduct(p, startDate, endDate);
            entries.add(new BarEntry(sumRevenue, i));
            labels.add(p.getProductName());
            i++;
        }
        conn.Close();

        BarDataSet dataset = new BarDataSet(entries, "Số lượng bán");
        //dataset.setColor(Color.rgb(0, 155, 0));
        dataset.setColors(ColorTemplate.PASTEL_COLORS);

        BarData data = new BarData(labels, dataset);
        data.setValueTextSize(9);
        bc.setData(data);
        bc.animateY(2000);
        bc.setDescription("");
        data.notifyDataChanged();
    }

    public void ShowDialogCalendar(final boolean startDate){
        builder = new AlertDialog.Builder(ChartActivity.this);
        LayoutInflater inflater = ChartActivity.this.getLayoutInflater();
        TextView title = new TextView(ChartActivity.this);
        title.setText("Chọn ngày bắt đầu");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        View view = inflater.inflate(R.layout.calendar, null);
        CreateCalendar(view, startDate);
        builder.setView(view)
                .setCustomTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Get value of 2 calendar
                        Calendar startCal = Calendar.getInstance();
                        startCal.set(intStartDate[2], intStartDate[1], intStartDate[0]);
                        Calendar endCal = Calendar.getInstance();
                        endCal.set(intEndDate[2], intEndDate[1], intEndDate[0]);

                        if (startDate) { //Start date button was clicked
                            btnStartDate.setText(intStartDate[0] + "/" + intStartDate[1] + "/" + intStartDate[2]);
                        } else {
                            btnEndDate.setText(intEndDate[0]+"/"+intEndDate[1]+"/"+intEndDate[2]);
                        }

                        //Compare
                        if(sdf.format(startCal.getTime()).compareTo(sdf.format(endCal.getTime())) > 0) {
                            //Show toast
                            String string = "Ngày bắt đầu phải trước ngày kết thúc!!";
                            Toast.makeText(getBaseContext(), string, Toast.LENGTH_SHORT).show();
                            btnEndDate.setText(intStartDate[0] + "/" + intStartDate[1] + "/" + intStartDate[2]);
                        }

                        if(radioProduct.isChecked()) {
                            DataChartProduct();
                        }
                        else {
                            DataChartBill();
                        }
                    }
                });
        builder.create();
        builder.show();
    }

    public void CreateCalendar(final View view, final boolean startDate){
        Locale.setDefault(Locale.US);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();

        items = new ArrayList<String>();
        adapter = new CalendarAdapter(this, month);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        handler = new Handler();
        handler.post(calendarUpdater);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

        RelativeLayout previous = (RelativeLayout) view.findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar(view);
            }
        });

        RelativeLayout next = (RelativeLayout) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar(view);

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarAdapter.dayString.get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*","");
                int gridvalue = Integer.parseInt(gridvalueString);
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar(view);
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar(view);
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                if(startDate) {
                    intStartDate[0] = Integer.parseInt(separatedTime[2]); //Day
                    intStartDate[1] = Integer.parseInt(separatedTime[1]); //Month
                    intStartDate[2] = Integer.parseInt(separatedTime[0]); //Year
                }
                else{
                    intEndDate[0] = Integer.parseInt(separatedTime[2]); //Day
                    intEndDate[1] = Integer.parseInt(separatedTime[1]); //Month
                    intEndDate[2] = Integer.parseInt(separatedTime[0]); //Year
                }
            }
        });
    }

    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    public void refreshCalendar(View view) {
        TextView title = (TextView) view.findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    public Runnable calendarUpdater = new Runnable() {
        @Override
        public void run() {
            items.clear();
            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add("2012-09-12");
                items.add("2012-10-07");
                items.add("2012-10-15");
                items.add("2012-10-20");
                items.add("2012-11-30");
                items.add("2012-11-28");
            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };
}




        /*
        ColorTemplate.LIBERTY_COLORS
        ColorTemplate.COLORFUL_COLORS
        ColorTemplate.JOYFUL_COLORS
        ColorTemplate.PASTEL_COLORS
        ColorTemplate.VORDIPLOM_COLORS

         */
