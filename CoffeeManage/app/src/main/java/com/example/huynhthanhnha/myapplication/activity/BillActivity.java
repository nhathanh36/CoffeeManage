package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.BillDetailsAdapter;
import com.example.huynhthanhnha.myapplication.adapter.CalendarAdapter;
import com.example.huynhthanhnha.myapplication.form.Bill;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;
import com.example.huynhthanhnha.myapplication.form.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by NguyenThanh on 03/12/2015.
 */
public class BillActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Bill> listBill = new ArrayList<Bill>();
    BillAdapter adapterBill;
    ListView listView;
    TextView tvTotal;
    Button btnChooseDate;

    long priceTotal = 0;
    int[] intDate = new int[3];

    AlertDialog.Builder builder;
    public GregorianCalendar month, itemmonth;
    public CalendarAdapter adapter;
    public Handler handler;
    public ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bill);

        //GET DATE CURRENT FOR BILL
        Calendar cal = Calendar.getInstance();
        intDate[0] = cal.get(Calendar.DAY_OF_MONTH);
        intDate[1] = cal.get(Calendar.MONTH) + 1;
        intDate[2] = cal.get(Calendar.YEAR);

        btnChooseDate = (Button)findViewById(R.id.btnChooseDate);
        listView = (ListView) findViewById(R.id.listBill);
        tvTotal = (TextView) findViewById(R.id.tvTotalAll);

        InitBill();

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogCalendar();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Bill bill = (Bill) parent.getItemAtPosition(position);
                ShowPopupBillDetail(view, bill);
            }
        });
    }

    public void ShowDialogCalendar(){
        builder = new AlertDialog.Builder(BillActivity.this);
        LayoutInflater inflater = BillActivity.this.getLayoutInflater();
        TextView title = new TextView(BillActivity.this);
        title.setText("Chọn ngày thống kê?");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        View view = inflater.inflate(R.layout.calendar, null);
        CreateCalendar(view);
        builder.setView(view)
                .setCustomTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    public void ShowPopupBillDetail(View view, final Bill bill){
        PopupMenu popup = new PopupMenu(BillActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.popup_bill_details, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popupBillDetail:
                        ShowDialogBillDetail(bill);
                        break;
                }
                return true;
            }
        });
        popup.show();//showing popup menu
    }

    public void ShowDialogBillDetail(final Bill bill){
        builder = new AlertDialog.Builder(BillActivity.this);
        LayoutInflater inflater = BillActivity.this.getLayoutInflater();
        TextView title = new TextView(BillActivity.this);
        title.setText("CHI TIẾT HÓA ĐƠN");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        View view = inflater.inflate(R.layout.detail_product_bill, null);

        CreateBillDetails(view, bill);

        builder.setView(view)
                .setCustomTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }

    private void CreateBillDetails(View view, Bill bill) {
        List<ProductDetails> productDetailsList = new ArrayList<ProductDetails>();
        ListView listViewBillDetail = (ListView) view.findViewById(R.id.listViewBillDetail);
        conn.Open();
        productDetailsList = conn.getListProductOfBillDetail(bill);
        conn.Close();
        BillDetailsAdapter billAdapter = new BillDetailsAdapter(this, productDetailsList);
        listViewBillDetail.setAdapter(billAdapter);

    }

    public void CreateCalendar(final View view){
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
                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar(view);
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar(view);
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                intDate[0] = Integer.parseInt(separatedTime[2]); //Day
                intDate[1] = Integer.parseInt(separatedTime[1]); //Month
                intDate[2] = Integer.parseInt(separatedTime[0]); //YYear

                InitBill();
                //System.out.println(separatedTime[0] + "/" + separatedTime[1] + "/" + separatedTime[2]);
                //showToast(selectedGridDate);
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

    protected void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
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

    public void InitBill(){
        conn.Open();
        listBill = conn.getListBill(intDate);
        priceTotal = conn.getPriceAllBill(intDate);
        conn.Close();

        btnChooseDate.setText(intDate[0] + "/" + intDate[1] + "/" + intDate[2]);
        tvTotal.setText(String.valueOf(priceTotal) + " VNĐ");
        adapterBill = new BillAdapter(BillActivity.this, listBill);
        listView.setAdapter(adapterBill);
        adapterBill.notifyDataSetChanged();
    }

    public class BillAdapter extends BaseAdapter {
        List<Bill> listBillDetails = new ArrayList<Bill>();
        Activity context;
        public BillAdapter(Activity context, List<Bill> listBillDetails) {

            this.listBillDetails = listBillDetails;
            this.context = context;
        }

        @Override
        public int getCount() {
            return listBillDetails.size();
        }

        @Override
        public Object getItem(int position) {
            return listBillDetails.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            final View rowView = inflater.inflate(R.layout.fragment_bill_item, null);
            final Bill b = listBillDetails.get(position);

            TextView tvSTT = (TextView) rowView.findViewById(R.id.tvSTT);
            tvSTT.setText(String.valueOf(position+1));

            TextView tvNgayLap = (TextView) rowView.findViewById(R.id.tvNgaylap);
            tvNgayLap.setText(String.valueOf(b.getCalendar().get(Calendar.DAY_OF_MONTH)) + "/" +
                    String.valueOf(b.getCalendar().get(Calendar.MONTH) + 1) + "/" +
                    String.valueOf(b.getCalendar().get(Calendar.YEAR)));

            TextView tvOfficer = (TextView) rowView.findViewById(R.id.tvNhanvien);
            tvOfficer.setText(b.getOfficer().getName().toString());

            //Tinh tong tien moi hoa don
            conn.Open();
            long price = conn.getPriceTotalOfBill(b);
            conn.Close();

            TextView tvThanhtien = (TextView) rowView.findViewById(R.id.tvThanhtien);
            tvThanhtien.setText(String.valueOf(price) + " đ");

            return rowView;
        }
    }

};


