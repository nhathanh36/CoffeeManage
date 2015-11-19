package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.GroupProduct;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;
import com.example.huynhthanhnha.myapplication.fragments.CafeFragment;
import com.example.huynhthanhnha.myapplication.fragments.KemChuoiFragment;
import com.example.huynhthanhnha.myapplication.fragments.LiptonFragment;
import com.example.huynhthanhnha.myapplication.fragments.NuocKhoangFragment;
import com.example.huynhthanhnha.myapplication.fragments.SinhToFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 18-Nov-15.
 */
public class ListProductActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    Fragment fragment = null;
    ListView mainlist;
    ArrayList<String> formain = new ArrayList<String>();
    DatabaseConnection conn = new DatabaseConnection();
    List<Product> listProduct = new ArrayList<Product>();
    List<GroupProduct> listGroupProduct = new ArrayList<GroupProduct>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product);

        //createListProduct();

        conn.Open();
        listGroupProduct = conn.getListGroupProduct();
        conn.Close();

        mainlist = (ListView) findViewById(R.id.left_drawer_child);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        mDrawerLayout.setDrawerListener(mDrawerListener);
        //  childlist = (ListView) findViewById(R.id.listView2);
        Iterator inte = listGroupProduct.iterator();
        while (inte.hasNext()) {
            GroupProduct gp = (GroupProduct) inte.next();
            formain.add(String.valueOf(gp.getProductName()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formain);
        mainlist.setAdapter(adapter);
        mDrawerLayout.openDrawer(mainlist);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                String getGroup = formain.get(arg2);
                Iterator iterator = listGroupProduct.iterator();
                switch (getGroup) {
                    case "Cafe":
                        fragment = new CafeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        mDrawerLayout.closeDrawer(mainlist);
                        break;
                    case "Sinh tố":
                        fragment = new SinhToFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        mDrawerLayout.closeDrawer(mainlist);
                        break;
                    case "Nước khoáng":
                        fragment = new NuocKhoangFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        mDrawerLayout.closeDrawer(mainlist);
                        break;
                    case "Trà-Lipton":
                        fragment = new LiptonFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        mDrawerLayout.closeDrawer(mainlist);
                        break;
                    case "Kem chuối":
                        fragment = new KemChuoiFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        mDrawerLayout.closeDrawer(mainlist);
                        break;
                }
            }
        });

    }

    private DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {

        @Override
        public void onDrawerStateChanged(int status) {

        }

        @Override
        public void onDrawerSlide(View view, float slideArg) {

        }

        @Override
        public void onDrawerOpened(View view) {
        }

        @Override
        public void onDrawerClosed(View view) {
        }
    };

    /*public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProduct);
        conn.Open();
        conn.TestDB();
        conn.PrintProductPrice();
        listProduct = conn.getListProduct();
        conn.Close();

        System.out.println("List price: " + listProduct.get(0).getListPrice().size());
        listView.setAdapter(new ListProductAdapter(this, listProduct));

        // Handle when user click on item in list view
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // get table user clicked
                Product entry = (Product) adapterView.getItemAtPosition(position);
            }
        });*/
    //}
}
