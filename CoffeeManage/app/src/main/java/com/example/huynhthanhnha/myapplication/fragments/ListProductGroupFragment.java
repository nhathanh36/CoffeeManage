package com.example.huynhthanhnha.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 19-Nov-15.
 */
public class ListProductGroupFragment extends Fragment{
    DatabaseConnection conn = new DatabaseConnection();
    ListView listCafe;
    ArrayList<String> forchild = new ArrayList<String>();
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Get param from ListProductActivity
        String group= this.getArguments().getString("groupActivity");
        View rootView = inflater.inflate(R.layout.fragment_group, null);
        listCafe = (ListView) rootView.findViewById(R.id.groupListView);
        System.out.println("ARGU: " + group);

        conn.Open();
        listProduct = conn.getListProductGroupByName(group);
        //conn.TestDB();
        conn.Close();

        ListProductAdapter productAdapter = new ListProductAdapter(this.getActivity(), listProduct);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,forchild);

        listCafe.setAdapter(productAdapter);

        return rootView;
    }
}
