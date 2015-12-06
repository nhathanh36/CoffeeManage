package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.ListPrice;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 18-Nov-15.
 */
public class ListPriceAdapter extends BaseAdapter {
    List<ListPrice> listPrice = new ArrayList<ListPrice>();
    Activity context;

    public ListPriceAdapter(Activity context, List<ListPrice> listPrice) {
        this.listPrice = listPrice;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listPrice.size();
    }

    @Override
    public Object getItem(int position) {
        return listPrice.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseConnection conn = new DatabaseConnection();
        long price = 0;
        ListPrice lp = listPrice.get(position);

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_item_price, null);

        TextView tvProduct = (TextView) rowView.findViewById(R.id.textProduct);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.textPrice);
        TextView tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        tvProduct.setText(String.valueOf(position+1) + ". " + String.valueOf(lp.getProduct().getProductName()));
        tvPrice.setText(lp.getPrice() + " đ");
        Calendar cal = Calendar.getInstance();
        cal.setTime(lp.getDateClass().getDate());
        String date = String.valueOf(cal.get(Calendar.DATE));
        String month = String.valueOf(cal.get(Calendar.MONTH));
        String year = String.valueOf(cal.get(Calendar.YEAR));
        tvDate.setText(date + "/" + month + "/" + year);
        return rowView;
    }
}
