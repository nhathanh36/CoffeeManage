package com.example.huynhthanhnha.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.activity.CalendarActivity;
import com.example.huynhthanhnha.myapplication.activity.ListTableDetails;
import com.example.huynhthanhnha.myapplication.form.Bill;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BillFragment extends Fragment{
    DatabaseConnection conn = new DatabaseConnection();
    List<Bill> listBill = new ArrayList<Bill>();
    BillAdapter adapterBill;
    ListView listView;
    public BillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn.Open();
        //listBill = conn.getListBill();
        conn.Close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bill, container, false);
        listView = (ListView) view.findViewById(R.id.listBill);
        conn.Open();
        //listBill = conn.getListBill();
        conn.Close();
        adapterBill = new BillAdapter(getActivity(), listBill);
        listView.setAdapter(adapterBill);

        return view;
    }

    public void InitBill(){
        conn.Open();
        //listBill = conn.getListBill();
        conn.Close();
        adapterBill = new BillAdapter(getActivity(), listBill);
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
            tvNgayLap.setText(String.valueOf(b.getCalendar().get(Calendar.DAY_OF_MONTH)) + "-" +
                    String.valueOf(b.getCalendar().get(Calendar.MONTH) + 1) + "-" +
                    String.valueOf(b.getCalendar().get(Calendar.YEAR)));

            TextView tvOfficer = (TextView) rowView.findViewById(R.id.tvNameOfficer);
            //tvOfficer.setText(b.getOfficer().getName().toString());


            return rowView;
        }
    }

}
