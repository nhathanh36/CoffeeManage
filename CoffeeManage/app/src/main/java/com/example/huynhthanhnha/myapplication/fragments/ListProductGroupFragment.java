package com.example.huynhthanhnha.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 19-Nov-15.
 */
public class ListProductGroupFragment extends Fragment{
    DatabaseConnection conn = new DatabaseConnection();
    ListView listCafe;
    TextView tvProduct;
    EditText etPrice;
    Button btnSavePrice;
    Button btnCancelPrice;
    LinearLayout linearUpdate;
    ArrayList<String> forchild = new ArrayList<String>();
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Get param from ListProductActivity
        final String group= this.getArguments().getString("groupActivity");
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        listCafe = (ListView) rootView.findViewById(R.id.groupListView);
        tvProduct = (TextView) rootView.findViewById(R.id.tvNameProduct);
        etPrice = (EditText) rootView.findViewById(R.id.editPrice);
        linearUpdate = (LinearLayout) rootView.findViewById(R.id.linearUpdatePrice);
        btnSavePrice = (Button) rootView.findViewById(R.id.btnSavePrice);
        btnCancelPrice = (Button) rootView.findViewById(R.id.btnCancelPrice);
        linearUpdate.setVisibility(View.GONE);

        System.out.println("ARGU: " + group);

        conn.Open();
        listProduct = conn.getListProductGroupByName(group);
        //conn.TestDB();
        conn.Close();

        final ListProductAdapter productAdapter = new ListProductAdapter(this.getActivity(), listProduct);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,forchild);

        listCafe.setAdapter(productAdapter);
        listCafe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listCafe.setVisibility(View.GONE);
                linearUpdate.setVisibility(View.VISIBLE);
                final Product product = (Product) parent.getItemAtPosition(position); // get Item in position

                tvProduct.setText(String.valueOf(product.getProductName()));
                // Click cancel button
                btnCancelPrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listCafe.setVisibility(View.VISIBLE);
                        linearUpdate.setVisibility(View.GONE);
                    }
                });
                // Click save button
                btnSavePrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long price = Long.valueOf(etPrice.getText().toString());
//                        System.out.println("/*********************************/");
//                        System.out.println("EDIT PRICE: " + strEditPrice);
//                        System.out.println("/*********************************/");

                        conn.Open();
                        System.out.println("PRICE BEFORE SET: " + conn.getPriceOfProduct(product.getProductId()));
                        conn.UpdatePrice(product, price);
                        conn.Close();
                        listCafe.invalidate();
                        listCafe.deferNotifyDataSetChanged();
                        linearUpdate.setVisibility(View.GONE);
                    }
                });
            }
        });

        return rootView;
    }
}
