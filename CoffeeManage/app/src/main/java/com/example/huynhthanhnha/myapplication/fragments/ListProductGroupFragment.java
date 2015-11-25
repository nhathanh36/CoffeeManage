package com.example.huynhthanhnha.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
    ListView lvProduct;
    TextView tvProduct;
    EditText etPrice;
    Button btnAddProduct;
//    Button btnSavePrice;
//    Button btnCancelPrice;
//    LinearLayout linearUpdate;
    List<Product> listProduct = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Get param from ListProductActivity
        final String group= this.getArguments().getString("groupActivity");
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        lvProduct = (ListView) rootView.findViewById(R.id.groupListView);
        btnAddProduct = (Button) rootView.findViewById(R.id.btnAddProduct);
//        linearUpdate = (LinearLayout) rootView.findViewById(R.id.linearUpdatePrice);
//        btnSavePrice = (Button) rootView.findViewById(R.id.btnSavePrice);
//        btnCancelPrice = (Button) rootView.findViewById(R.id.btnCancelPrice);
        //linearUpdate.setVisibility(View.GONE);

        conn.Open();
        listProduct = conn.getListProductGroupByName(group);
        conn.Close();

        final ListProductAdapter productAdapter = new ListProductAdapter(this.getActivity(), listProduct);

        lvProduct.setAdapter(productAdapter);
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lvProduct.setVisibility(View.GONE);
                //linearUpdate.setVisibility(View.VISIBLE);
                final Product product = (Product) parent.getItemAtPosition(position); // get Item in position

                // Dialog show list edit, delete,...
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                //final View dialogView = inflater.inflate(R.layout.dialog_list_edit, null);
                builder//setView(dialogView)
                        .setItems(R.array.showList, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // sign in the user ...
                            }
                        });
                        //.setTitle("Thêm thức uống")
                        // Add action buttons
//                TextView tvShowUpdatePrice = (TextView) dialogView.findViewById(R.id.tvShowUpdatePrice);
//                TextView tvShowDelete = (TextView) dialogView.findViewById(R.id.tvShowDeleteProduct);
//                TextView tvShowEditName = (TextView) dialogView.findViewById(R.id.tvShowEditName);
                AlertDialog dialog = builder.create();
                //builder.show();

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.copyFrom(dialog.getWindow().getAttributes());
                wmlp.width = 50;
                wmlp.height = 500;
                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                wmlp.x = -170;
                wmlp.y = 200;
                dialog.getWindow().setAttributes(wmlp);

                dialog.show();

                // Dialog update price
//                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                // Get the layout inflater
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//
//                // Inflate and set the layout for the dialog
//                // Pass null as the parent view because its going in the dialog layout
//                final View dialogView = inflater.inflate(R.layout.dialog_update_price, null);
//                builder.setView(dialogView)
//                        .setTitle("Cập nhật giá sản phẩm")
//                                // Add action buttons
//                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                tvProduct = (TextView) dialogView.findViewById(R.id.tvNameProduct);
//                                System.out.println("TEXT VIEW: " + tvProduct.getText());
//
//                                tvProduct.setText(String.valueOf(product.getProductName()));
//
//                                etPrice = (EditText) dialogView.findViewById(R.id.editPrice);
//                                long price = Long.valueOf(etPrice.getText().toString());
//
//                                conn.Open();
//                                conn.UpdatePrice(product, price);
//                                conn.Close();
//                                productAdapter.notifyDataSetChanged();
//                                lvProduct.setAdapter(productAdapter);
//                            }
//                        })
//                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                //LoginDialogFragment.this.getDialog().cancel();
//                            }
//                        });
//                builder.create();
//                builder.show();

//                // Click cancel button
//                btnCancelPrice.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        lvProduct.setVisibility(View.VISIBLE);
//                        linearUpdate.setVisibility(View.GONE);
//                    }
//                });
//                // Click save button
//                btnSavePrice.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        long price = Long.valueOf(etPrice.getText().toString());
//
//                        conn.Open();
//                        conn.UpdatePrice(product, price);
//                        conn.Close();
//                        productAdapter.notifyDataSetChanged();
//                        lvProduct.setAdapter(productAdapter);
//                        lvProduct.setVisibility(View.VISIBLE);
//                        linearUpdate.setVisibility(View.GONE);
//                    }
//                });
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
                builder.setView(inflater.inflate(R.layout.dialog_add_product, null))
                        .setTitle("Thêm thức uống")
                                // Add action buttons
                        .setPositiveButton("Lưu lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
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
