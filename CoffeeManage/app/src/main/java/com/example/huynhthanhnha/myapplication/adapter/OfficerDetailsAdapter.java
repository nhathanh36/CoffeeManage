package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Officer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 23/11/2015.
 */
public class OfficerDetailsAdapter extends BaseAdapter {
    DatabaseConnection conn = new DatabaseConnection();
    List<Officer> listOfficer = new ArrayList<Officer>();
    Activity context;

    public OfficerDetailsAdapter(Activity context, List<Officer> listOfficer) {
        this.listOfficer = listOfficer;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOfficer.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfficer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        Officer officer = listOfficer.get(position);

        View rowView = inflater.inflate(R.layout.list_officer_details, null);
        TextView textName = (TextView) rowView.findViewById(R.id.tvNameOfficer);
        textName.setText(officer.getName());

        TextView textCMND = (TextView) rowView.findViewById(R.id.tvCMND);
        textName.setText(officer.getCMND());

        TextView textDate = (TextView) rowView.findViewById(R.id.tvDate);
        //textName.setText(String.valueOf(officer.getWorkDate().getTime()));

        TextView textUsername = (TextView) rowView.findViewById(R.id.tvUsername);
        textName.setText(officer.getUsername());

        TextView textPass = (TextView) rowView.findViewById(R.id.tvPasswork);
        textName.setText(officer.getPassword());

        return rowView;
    }
}
