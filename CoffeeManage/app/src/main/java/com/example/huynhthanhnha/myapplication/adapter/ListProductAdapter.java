package com.example.huynhthanhnha.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.DateClass;
import com.example.huynhthanhnha.myapplication.form.ListPrice;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 18-Nov-15.
 */
public class ListProductAdapter extends BaseAdapter {
    List<Product> listProduct = new ArrayList<Product>();
    Activity context;

    public ListProductAdapter(Activity context, List<Product> listProduct) {
        this.listProduct = listProduct;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseConnection conn = new DatabaseConnection();
        long price = 0;
        Product product = listProduct.get(position);

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_item_product, null);

        TextView tvProduct = (TextView) rowView.findViewById(R.id.tvlistProduct);
        if (product.getProductName().equals("")) {
            tvProduct.setText("Chưa có thức uống!");
        } else {
            tvProduct.setText(String.valueOf(product.getProductName()));
        }
        TextView tvPrice = (TextView) rowView.findViewById(R.id.tvPrice);
        conn.Open();
        price = conn.getPriceOfProduct(product.getProductId());
        conn.Close();

        tvPrice.setText(String.valueOf(price) + " đ");
        return rowView;
    }
}
