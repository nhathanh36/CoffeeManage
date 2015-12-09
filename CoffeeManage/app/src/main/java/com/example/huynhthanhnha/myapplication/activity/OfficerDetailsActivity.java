package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.CalendarAdapter;
import com.example.huynhthanhnha.myapplication.adapter.OfficerDetailsAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Officer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
    Button btnAddOfficer;
    AlertDialog.Builder builder;

    public GregorianCalendar month, itemmonth;
    AlertDialog.Builder calBuilder;
    public ArrayList<String> items;
    public Handler handler;
    public CalendarAdapter calAdapter;
    int[] intDate = new int[3];

    RadioGroup radioGrooupSex;
    RadioButton radioBtnSex;

    EditText addName;
    EditText addUsername;
    EditText addPass;
    EditText addCMND;
    Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_officer);

        //GET DATE CURRENT FOR BILL
        Calendar cal = Calendar.getInstance();
        intDate[0] = cal.get(Calendar.DAY_OF_MONTH);
        intDate[1] = cal.get(Calendar.MONTH) + 1;
        intDate[2] = cal.get(Calendar.YEAR);

        imgBack = (ImageView) findViewById(R.id.imgBackListOfficer);
        textViewSearch = (TextView) findViewById(R.id.inputSearchOfficer);
        listViewOfficer = (ListView) findViewById(R.id.listViewOfficer);
        btnAddOfficer = (Button) findViewById(R.id.btnAddOfficer);

        InitListOfficer();

        listViewOfficer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Officer officer = (Officer) parent.getItemAtPosition(position);
                ShowPopupOfficer(view, officer);
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

        btnAddOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOfficer();
            }
        });
    }

    private void InitListOfficer() {
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
            if (!p.getName().contains(textToSearch)) {
                it.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void ShowPopupOfficer(final View view, final Officer officer) {
        PopupMenu popup = new PopupMenu(OfficerDetailsActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.popup_list_officer, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popupModifyOfficer:
                        ShowDialogModify(officer);
                        break;

                    case R.id.popupDeleteOfficer:
                        ShowDiaglogDelete(officer);
                        break;
                }
                return true;
            }
        });
        popup.show();//showing popup menu
    }

    private void ShowDialogModify(final Officer officer) {
        final EditText editName;
        final EditText editCMND;
        final EditText editSex;

        // Dialog edit name
        final AlertDialog.Builder modifyBuilder = new AlertDialog.Builder(OfficerDetailsActivity.this);
        // Get the layout inflater
        LayoutInflater inflater1 = OfficerDetailsActivity.this.getLayoutInflater();
        TextView title = new TextView(OfficerDetailsActivity.this);
        title.setText("CHỈNH SỬA THÔNG TIN");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater1.inflate(R.layout.dialog_edit_officer, null);
        editName = (EditText) view.findViewById(R.id.editNameOfficer);
        editCMND = (EditText) view.findViewById(R.id.editCMND);

        editSex = (EditText) view.findViewById(R.id.editSex);

        modifyBuilder.setView(view)
                .setCustomTitle(title)
                        // Add action buttons
                .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String strName;
                        String strCMND;
                        String strUsername;
                        String strPass;
                        String strDate;
                        String strSex;

                        strName = editName.getText().toString();
                        strCMND = editCMND.getText().toString();
                        strSex = editSex.getText().toString();

                        conn.Open();
                        conn.updateOfficer(officer, strName, strCMND, strSex);
                        conn.Close();
                        InitListOfficer();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        modifyBuilder.create();
        modifyBuilder.show();
    }

    private void ShowDiaglogDelete(final Officer officer) {
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
                        conn.Open();
                        conn.deleteOfficer(officer);
                        conn.Close();
                        InitListOfficer();
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

    public void AddOfficer() {

        // Dialog edit name
        final AlertDialog.Builder addBuilder = new AlertDialog.Builder(OfficerDetailsActivity.this);
        // Get the layout inflater
        LayoutInflater inflater1 = OfficerDetailsActivity.this.getLayoutInflater();
        TextView title = new TextView(OfficerDetailsActivity.this);
        title.setText("THÊM NHÂN VIÊN");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater1.inflate(R.layout.dialog_add_officer, null);
        btnDate = (Button) view.findViewById(R.id.btnDateWork);
        radioGrooupSex = (RadioGroup) view.findViewById(R.id.radioSex);
        addName = (EditText) view.findViewById(R.id.addNameOfficer);
        // get selected radio button from radioGroup
        int selectedId = radioGrooupSex.getCheckedRadioButtonId();
        radioBtnSex = (RadioButton) findViewById(selectedId);

        btnDate.setText(intDate[0] + "/" + intDate[1] + "/" + intDate[2]);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogCalendar();
            }
        });

        addBuilder.setView(view)
                .setCustomTitle(title)
                        // Add action buttons
                .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String strName;
                        String strCMND;
                        String strDateWork;
                        String strSex;
//                        conn.Open();
//                        conn.updateOfficer(officer, strName, strCMND, strSex);
//                        conn.Close();
//                        InitListOfficer();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        addBuilder.create();
        addBuilder.show();
    }

    public void ShowDialogCalendar(){
        calBuilder = new AlertDialog.Builder(OfficerDetailsActivity.this);
        LayoutInflater inflater = OfficerDetailsActivity.this.getLayoutInflater();
        TextView title = new TextView(OfficerDetailsActivity.this);
        title.setText("Chọn ngày vào làm");
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setPadding(10, 10, 10, 10);
        title.setHeight(60);
        title.setTextSize(16);
        title.setTextColor(Color.BLUE);
        View view = inflater.inflate(R.layout.calendar, null);
        CreateCalendar(view);
        calBuilder.setView(view)
                .setCustomTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AddOfficer();
                    }
                });
        calBuilder.create();
        calBuilder.show();
    }

    public void CreateCalendar(final View view){
        Locale.setDefault(Locale.US);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();

        items = new ArrayList<String>();
        calAdapter = new CalendarAdapter(this, month);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(calAdapter);

        handler = new Handler();
        handler.post(calendarUpdater);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

        RelativeLayout previous = (RelativeLayout) view.findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar(view);
            }
        });

        RelativeLayout next = (RelativeLayout) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar(view);

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar(view);
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar(view);
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                intDate[0] = Integer.parseInt(separatedTime[2]); //Day
                intDate[1] = Integer.parseInt(separatedTime[1]); //Month
                intDate[2] = Integer.parseInt(separatedTime[0]); //YYear

                //System.out.println(separatedTime[0] + "/" + separatedTime[1] + "/" + separatedTime[2]);
                //showToast(selectedGridDate);
            }
        });
    }

    public Runnable calendarUpdater = new Runnable() {
        @Override
        public void run() {
            items.clear();
            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add("2012-09-12");
                items.add("2012-10-07");
                items.add("2012-10-15");
                items.add("2012-10-20");
                items.add("2012-11-30");
                items.add("2012-11-28");
            }
            calAdapter.setItems(items);
            calAdapter.notifyDataSetChanged();
        }
    };

    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    public void refreshCalendar(View view) {
        TextView title = (TextView) view.findViewById(R.id.title);

        calAdapter.refreshDays();
        calAdapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }
}
