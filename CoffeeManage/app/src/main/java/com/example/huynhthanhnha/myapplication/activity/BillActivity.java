package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.Bill;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;
import com.example.huynhthanhnha.myapplication.form.Table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by NguyenThanh on 03/12/2015.
 */
public class BillActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Bill> listBill = new ArrayList<Bill>();
    BillAdapter adapterBill;
    ListView listView;
    long priceTotal = 0;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bill);

        List<String> list = new ArrayList<String>();
        list.add("Theo ngày");
        list.add("Theo tháng");
        list.add("Theo năm");


        listView = (ListView) findViewById(R.id.listBill);
        tvTotal = (TextView) findViewById(R.id.tvTotalAll);

        InitBill();


        final Spinner spBill= (Spinner) findViewById(R.id.spinnerBill);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(BillActivity.this,
                android.R.layout.simple_list_item_1,list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item); //simple_spinner_dropdown_item
        spBill.setAdapter(adp);

        spBill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void InitBill(){
        conn.Open();
        //listBill = conn.getListBill();
        priceTotal = conn.getPriceAllBill();

        Calendar cal = Calendar.getInstance();
        cal.set(2015, 9, 24);
        conn.getPriceProductInCurrentBill(1, cal);
        conn.Close();

        tvTotal.setText(String.valueOf(priceTotal) + " VNĐ");

        adapterBill = new BillAdapter(BillActivity.this, listBill);
        listView.setAdapter(adapterBill);
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


