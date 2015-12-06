package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 06/12/2015.
 */
public class BillDetailsAdapter extends BaseAdapter {
    DatabaseConnection conn = new DatabaseConnection();
    List<ProductDetails> listProductDetail = new ArrayList<ProductDetails>();
    Activity context;

    public BillDetailsAdapter(Activity context, List<ProductDetails> listProductDetail) {
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
        ProductDetails productDetails = listProductDetail.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.detail_product_item_bill, null);

        TextView productName = (TextView) rowView.findViewById(R.id.tvProductName);
        productName.setText(String.valueOf(position + 1) + ". " + String.valueOf(productDetails.getProduct().getProductName()));

        TextView unitSales = (TextView) rowView.findViewById(R.id.tvUnitSales);
        unitSales.setText(String.valueOf(productDetails.getUnitSales()));

        TextView numberSales = (TextView) rowView.findViewById(R.id.tvDonviban);
        numberSales.setText(productDetails.getProduct().getUnit());

        TextView price = (TextView) rowView.findViewById(R.id.tvPriceProduct);
        conn.Open();
        long priceValue = conn.getPriceProductInCurrentBill(productDetails.getProduct().getProductId(),productDetails.getBill().getCalendar());
        price.setText(String.valueOf(priceValue) + "đ X");
        conn.Close();

        return rowView;
    }

}
