package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 19/11/2015.
 */
public class ListProductDetailsAdapter extends BaseAdapter {
    List<ProductDetails> listProductDetail = new ArrayList<ProductDetails>();
    Activity context;

    public ListProductDetailsAdapter(Activity context, List<ProductDetails> listProductDetail) {
        this.listProductDetail = listProductDetail;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listProductDetail.size();
    }

    @Override
    public Object getItem(int position) {
        return listProductDetail.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductDetails table = listProductDetail.get(position);
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.table_item_details, null);

        TextView textView = (TextView) rowView.findViewById(R.id.tvProductName);
        textView.setText(String.valueOf(table.getProduct().getProductName()));

        return rowView;
    }
}
