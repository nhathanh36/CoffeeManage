package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.GroupProduct;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 18-Nov-15.
 */
public class ListGroupAdapter extends BaseAdapter {
    List<GroupProduct> listGroup = new ArrayList<GroupProduct>();
    Activity context;

    public ListGroupAdapter(Activity context, List<GroupProduct> listGroup) {
        this.listGroup = listGroup;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listGroup.size();
    }

    @Override
    public Object getItem(int position) {
        return listGroup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseConnection conn = new DatabaseConnection();
        long price = 0;
        GroupProduct group = listGroup.get(position);

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_item_group, null);

        TextView tvProduct = (TextView) rowView.findViewById(R.id.tvlistGroup);
        if (group.getGroupProductName().equals("")) {
            tvProduct.setText("Chưa có nhóm thức uống!");
        } else {
            tvProduct.setText(String.valueOf(group.getGroupProductName()));
        }

        return rowView;
    }

}
