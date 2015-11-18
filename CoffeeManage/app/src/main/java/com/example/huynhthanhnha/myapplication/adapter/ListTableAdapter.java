package com.example.huynhthanhnha.myapplication.adapter;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.Table;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Huynh Thanh Nha on 16-Nov-15.
 */
public class ListTableAdapter extends BaseAdapter {
    List<Table> listTable = new ArrayList<Table>();
    Activity context;

    public ListTableAdapter(Activity context, List<Table> listTable) {
        this.listTable = listTable;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTable.size();
    }

    @Override
    public Object getItem(int position) {
        return listTable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Table table = listTable.get(position);
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.grid_item_table, null);

        /*get image in database and set to view*/
        /*ImageView imageView = (ImageView) rowView.findViewById(R.id.imageTable);
        imageView.setImageResource(table.getImage());*/
        TextView textView = (TextView) rowView.findViewById(R.id.tvTable);
        textView.setText("Bàn " + String.valueOf(table.getIdTable()));

        return rowView;
    }
}
