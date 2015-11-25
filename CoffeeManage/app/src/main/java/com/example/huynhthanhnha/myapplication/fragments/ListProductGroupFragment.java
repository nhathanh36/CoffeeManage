package com.example.huynhthanhnha.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huynh Thanh Nha on 19-Nov-15.
 */
public class ListProductGroupFragment extends Fragment {
    DatabaseConnection conn = new DatabaseConnection();
    ListView lvProduct;
    TextView tvProduct;
    EditText etPrice;
    EditText etName;
    Button btnAddProduct;
    EditText addGroup;
    EditText addName;
    EditText addUnit;
    EditText addPrice;
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Get param from ListProductActivity
        final String group = this.getArguments().getString("groupActivity");
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        lvProduct = (ListView) rootView.findViewById(R.id.groupListView);
        btnAddProduct = (Button) rootView.findViewById(R.id.btnAddProduct);

        conn.Open();
        listProduct = conn.getListProductGroupByName(group);
        conn.Close();

        final ListProductAdapter productAdapter = new ListProductAdapter(this.getActivity(), listProduct);

        lvProduct.setAdapter(productAdapter);
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Product product = (Product) parent.getItemAtPosition(position); // get Item in position

                // Dialog show list edit, delete,...
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itemUpdatePrice:
                                // Dialog update price
                                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                // Inflate and set the layout for the dialog
                                // Pass null as the parent view because its going in the dialog layout
                                final View dialogView = inflater.inflate(R.layout.dialog_update_price, null);
                                tvProduct = (TextView) dialogView.findViewById(R.id.tvNameProduct);
                                tvProduct.setText(String.valueOf(product.getProductName()));
                                builder.setView(dialogView)
                                        .setTitle("Cập nhật giá sản phẩm")
                                                // Add action buttons
                                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                                etPrice = (EditText) dialogView.findViewById(R.id.editPrice);
                                                long price = Long.valueOf(etPrice.getText().toString());

                                                conn.Open();
                                                conn.UpdatePrice(product, price);
                                                conn.Close();

                                                productAdapter.notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //LoginDialogFragment.this.getDialog().cancel();
                                            }
                                        });
                                builder.create();
                                builder.show();
                                return true;
                            case R.id.itemDelete:

                                return true;
                            case R.id.itemEditName:
                                // Dialog edit name
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                                // Get the layout inflater
                                LayoutInflater inflater1 = getActivity().getLayoutInflater();

                                // Inflate and set the layout for the dialog
                                // Pass null as the parent view because its going in the dialog layout
                                final View dialogView1 = inflater1.inflate(R.layout.dialog_edit_name, null);
                                builder1.setView(dialogView1)
                                        .setTitle("Cập nhật tên sản phẩm")
                                                // Add action buttons
                                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                                etName = (EditText) dialogView1.findViewById(R.id.editName);
                                                String newName = etName.getText().toString();

                                                conn.Open();
                                                conn.UpdateNameProduct(product.getProductId(), newName);
                                                conn.Close();
                                                //listProduct.clear();
                                                productAdapter.notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //LoginDialogFragment.this.getDialog().cancel();
                                            }
                                        });
                                builder1.create();
                                builder1.show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View rootView = inflater.inflate(R.layout.dialog_add_product, null);
                addName = (EditText) rootView.findViewById(R.id.editAddName);
                addGroup = (EditText) rootView.findViewById(R.id.editAddGroup);
                addPrice = (EditText) rootView.findViewById(R.id.editAddPrice);
                addUnit = (EditText) rootView.findViewById(R.id.editAddUnit);

                final String strGroup = addGroup.getText().toString();
                final String strName = addName.getText().toString();
                final String strUnit = addUnit.getText().toString();
                final long price = Long.parseLong(addPrice.getText().toString());
                builder.setView(rootView)
                        .setTitle("Thêm thức uống")
                                // Add action buttons
                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                conn.Open();
                                conn.InsertProduct(strGroup, strName, strUnit, price);
                                conn.Close();
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //LoginDialogFragment.this.getDialog().cancel();
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        return rootView;
    }
}
