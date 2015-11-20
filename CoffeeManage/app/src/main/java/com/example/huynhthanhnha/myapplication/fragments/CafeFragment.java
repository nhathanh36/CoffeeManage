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
import com.example.huynhthanhnha.myapplication.form.GroupProduct;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Huynh Thanh Nha on 19-Nov-15.
 */
public class CafeFragment extends Fragment{
    DatabaseConnection conn = new DatabaseConnection();
    ListView listCafe;
    ArrayList<String> forchild = new ArrayList<String>();
    List<GroupProduct> listGroup = new ArrayList<GroupProduct>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_group, null);
        listCafe = (ListView) rootView.findViewById(R.id.groupListView);

        conn.Open();
        listGroup = conn.getListGroupProduct();
        conn.Close();

        for (GroupProduct gp: listGroup) {
            Set<Product> pro = gp.getListProduct();
            if (pro.size() == 0) {
                forchild.add("Chưa có thức uống");
            } else {
                for (Product p : pro) {
                    forchild.add(p.getProductName());
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,forchild);
        listCafe.setAdapter(adapter);

        return rootView;
    }
}
