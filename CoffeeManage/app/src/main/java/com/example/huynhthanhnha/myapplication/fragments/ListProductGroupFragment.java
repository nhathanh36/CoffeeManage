package com.example.huynhthanhnha.myapplication.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.R;
import com.example.huynhthanhnha.myapplication.adapter.ListProductAdapter;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.GroupProduct;
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
    EditText addName;
    EditText addUnit;
    String strGroup;
    String group;
    EditText addPrice;
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Get param from ListProductActivity
        group = this.getArguments().getString("groupActivity");
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        lvProduct = (ListView) rootView.findViewById(R.id.groupListView);
        TextView tvSum = (TextView) rootView.findViewById(R.id.tvSumProduct);
        btnAddProduct = (Button) rootView.findViewById(R.id.btnAddProduct);

        conn.Open();
        listProduct = conn.getListProductGroupByName(group);
        conn.Close();

        tvSum.setText("Tổng số thức uống: " + String.valueOf(listProduct.size()));
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
                                tvProduct.setTextSize(17);
                                builder.setView(dialogView)
                                        .setTitle("Cập nhật giá sản phẩm")
                                                // Add action buttons
                                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                                etPrice = (EditText) dialogView.findViewById(R.id.editPrice);
                                                String price = String.valueOf(etPrice.getText());

                                                if (String.valueOf(price).equals("")) {
                                                    Toast.makeText(getActivity(), "Trường giá không được rỗng!!", Toast.LENGTH_SHORT).show();
                                                } else if (Long.valueOf(price) < 8000) {
                                                    Toast.makeText(getActivity(), "Giá thức uống không được quá nhỏ!!", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    conn.Open();
                                                    conn.UpdatePrice(product, Long.valueOf(price));
                                                    conn.Close();
                                                }

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
//                                final AlertDialog.Builder builderDelete = new AlertDialog.Builder(getActivity());
//                                // Get the layout inflater
//                                LayoutInflater inflaterDelete = getActivity().getLayoutInflater();
//
//                                // Inflate and set the layout for the dialog
//                                // Pass null as the parent view because its going in the dialog layout
//                                builderDelete
//                                        .setTitle("Xoá thức uống")
//                                                // Add action buttons
//                                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int id) {
//                                                conn.Open();
//                                                conn.deleteProduct(product, group);
//                                                conn.Close();
//                                                Toast.makeText(getActivity(), product.getProductName(), Toast.LENGTH_SHORT).show();
//                                                productAdapter.notifyDataSetChanged();
//                                            }
//                                        })
//                                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int id) {
//                                                //LoginDialogFragment.this.getDialog().cancel();
//                                            }
//                                        });
//                                builderDelete.create();
//                                builderDelete.show();
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
                popupMenu.inflate(R.menu.popup_menu_product);
                popupMenu.show();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View rootView = inflater.inflate(R.layout.dialog_add_product, null);

                final List<String> list = new ArrayList<String>();
                conn.Open();
                List<GroupProduct> groupProductLis = conn.getListGroup();
                conn.Close();

                for (GroupProduct gp : groupProductLis) {
                    list.add(String.valueOf(gp.getGroupProductName()));
                }

                final Spinner sp1 = (Spinner) rootView.findViewById(R.id.spinner1);

                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, list);
                adp1.setDropDownViewResource(R.layout.spinner_group);
                sp1.setAdapter(adp1);

                sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                        // TODO Auto-generated method stub
                        strGroup = String.valueOf(list.get(position));
                        // Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                });

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(rootView)
                        .setTitle("Thêm thức uống")
                                // Add action buttons
                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                addName = (EditText) rootView.findViewById(R.id.editAddName);
                                addPrice = (EditText) rootView.findViewById(R.id.editAddPrice);
                                addUnit = (EditText) rootView.findViewById(R.id.editAddUnit);

                                String strName = addName.getText().toString();
                                String strUnit = addUnit.getText().toString();
                                String price = addPrice.getText().toString();
                                if (strName.equals("") || strUnit.equals("") || price.equals("")) {
                                    Toast.makeText(getActivity(), "Các trường nhập không được trống!!", Toast.LENGTH_SHORT).show();
                                } else if (Long.valueOf(price) < 8000) {
                                    Toast.makeText(getActivity(), "Giá thức uống không được quá nhỏ!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    conn.Open();
                                    conn.InsertProduct(strGroup, strName, strUnit, Long.valueOf(price));
                                    conn.Close();
                                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    productAdapter.notifyDataSetChanged();
                                }
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
