package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductDetailsAdapter;
import com.example.huynhthanhnha.myapplication.adapter.OfficerDetailsAdapter;
import com.example.huynhthanhnha.myapplication.form.Bill;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Officer;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NguyenThanh on 23/11/2015.
 */
public class OfficerDetailsActivity extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<Officer> listOfficer = new ArrayList<Officer>();
    ListView listViewOfficer;
    TextView textViewSearch;
    OfficerDetailsAdapter adapter;
    ImageView imgBack;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_officer);

        imgBack = (ImageView) findViewById(R.id.imgBackListOfficer);
        textViewSearch = (TextView) findViewById(R.id.inputSearchOfficer);
        listViewOfficer = (ListView) findViewById(R.id.listViewOfficer);

        InitListOfficer();

        listViewOfficer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowPopupOfficer(view);
            }
        });

        textViewSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InitListOfficer();
                if (!s.toString().equals("")) {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OfficerDetailsActivity.this, HomeActivity.class));
            }
        });
    }

    private void InitListOfficer(){
        //Get data from database
        conn.Open();
        listOfficer = conn.getListOfficer();
        conn.Close();

        adapter = new OfficerDetailsAdapter(this, listOfficer);
        listViewOfficer.setAdapter(adapter);
    }

    private void searchItem(String textToSearch) {
        Iterator<Officer> it = listOfficer.iterator();
        while (it.hasNext()) {
            Officer p = it.next();
            if(!p.getName().contains(textToSearch)) {
                it.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void ShowPopupOfficer(final View view){
        PopupMenu popup = new PopupMenu(OfficerDetailsActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.popup_list_officer, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popupModifyOfficer:
                        ShowDialogModify();
                        break;

                    case R.id.popupDeleteOfficer:
                        ShowDiaglogDelete();
                        break;
                }
                return true;
            }
        });
        popup.show();//showing popup menu
    }

    private void ShowDialogModify(){
        //Show form to modify officer
        ///Write form......
    }

    private void ShowDiaglogDelete(){
        builder = new AlertDialog.Builder(OfficerDetailsActivity.this);
        LayoutInflater inflater = OfficerDetailsActivity.this.getLayoutInflater();
        TextView title = new TextView(OfficerDetailsActivity.this);
        title.setText("BẠN CÓ MUỐN XÓA KHÔNG?");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        builder.setView(inflater.inflate(R.layout.dialog_confirm, null))
                .setCustomTitle(title)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Delete officer
                        //Connect database and delete it
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Nothing to do
                    }
                });
        builder.create();
        builder.show();
    }

}
