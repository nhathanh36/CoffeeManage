package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.db4o.ObjectSet;
import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListTableAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class GridTableActivity extends Activity {
    List<Table> listTableMove = new ArrayList<Table>();
    List<Table> listTable = new ArrayList<Table>();
    String date = "";
    TextView tvDate;
    Spinner spinner;
    DatabaseConnection conn = new DatabaseConnection();
    ObjectSet<Table> obsTable;
    TextView nameUser;
    TextView permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_table);
        //System.out.println("External" + this.getDir("data", 0) + "Enviroment: " + Environment.getDataDirectory());

        nameUser = (TextView) findViewById(R.id.tvNameUser);
        permission = (TextView) findViewById(R.id.tvPermission);

        nameUser.setText("Tên: " + Login.getUser().getName().toString());

        createGridTable();

    }

    private void createGridTable() {
        GridView gridView = (GridView) findViewById(R.id.gvListTable);
        Calendar currentDate = Calendar.getInstance();
        date =  "Ngày " + String.valueOf(currentDate.get(Calendar.DATE))+"/"+
                String.valueOf(currentDate.get(Calendar.MONTH)+1)+"/"+
                String.valueOf(currentDate.get(Calendar.YEAR));
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText(date);
        // get table in database
        //System.out.println("FILE: " + this.getFilesDir().toString());
        conn.Open();
        listTable = conn.getTable();
        conn.Close();

        gridView.setAdapter(new ListTableAdapter(this, listTable));

        // Handle when user click on item in grid view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Table tablePosition = (Table) adapterView.getItemAtPosition(position);
                PopupMenu popup = new PopupMenu(GridTableActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_gird_table, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popupGridEnter:
                                // get table user clicked
                                Intent intent = new Intent(GridTableActivity.this, ListTableDetails.class);
                                intent.putExtra("IdTable", tablePosition.getIdTable());
                                startActivity(intent);
                                break;
                            case R.id.popupGridMove:
                                //Toast.makeText(GridTableActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                ShowDialogMoveTable(tablePosition);
                                break;
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
    }

    private void ShowDialogMoveTable(Table table){
        AlertDialog.Builder builder = new AlertDialog.Builder(GridTableActivity.this);
        LayoutInflater inflater = GridTableActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_move_table, null);

        final List<String> list = new ArrayList<String>();
        conn.Open();
        listTableMove = conn.getListTableEmpty(table.getIdTable());
        conn.Close();

        for(Table tb: listTableMove){
            list.add("Bàn " + tb.getIdTable());
        }

        final Spinner sp1= (Spinner) view.findViewById(R.id.spinner);

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(GridTableActivity.this, android.R.layout.simple_list_item_1,list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(GridTableActivity.this, list.get(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        TextView title = new TextView(GridTableActivity.this);
        title.setText("Chọn bàn để chuyển");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(18);
        title.setTextColor(Color.BLUE);

        builder.setView(view)
                //.setTitle("Chọn bàn để chuyển")
                .setCustomTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
}
