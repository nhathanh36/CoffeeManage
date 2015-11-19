package com.example.huynhthanhnha.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 19-Nov-15.
 */
public class NuocKhoangFragment extends Fragment{
    DatabaseConnection conn = new DatabaseConnection();
    ListView listWate;
    ArrayList<String> forchild = new ArrayList<String>();
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_nuoc_khoang, null);
        listWate = (ListView) rootView.findViewById(R.id.nuocKhoangListView);

        conn.Open();
        listProduct = conn.getListProductByGroup("Nước khoáng");
        conn.Close();

        if (listProduct.size() == 0) {
            forchild.add("Chưa có thức uống!");
        } else {
            Iterator iterator = listProduct.iterator();
            while (iterator.hasNext()) {
                Product pro = (Product) iterator.next();
                forchild.add(pro.getProductName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,forchild);
        listWate.setAdapter(adapter);

        return rootView;
    }
}
