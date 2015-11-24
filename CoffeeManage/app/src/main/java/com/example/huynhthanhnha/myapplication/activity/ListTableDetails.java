package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huynhthanhnha.myapplication.R;
//import com.example.huynhthanhnha.myapplication.adapter.AddProductDetailsAdaper;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.form.Product;
import com.example.huynhthanhnha.myapplication.form.ProductDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 19/11/2015.
 */
public class ListTableDetails extends Activity {
    DatabaseConnection conn = new DatabaseConnection();
    List<ProductDetails> listProductDetails = new ArrayList<ProductDetails>();
    List<Product> listProductGroup = new ArrayList<Product>();
    RelativeLayout relativeDetails;
    RelativeLayout relativeAdd;
    int IdTable;
    Button btnAdd;
    Button btnComplete;
    Button btnMonmoi;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_details);
        IdTable = getIntent().getExtras().getInt("IdTable");

        TextView numTable = (TextView) findViewById(R.id.tableNumber);
        numTable.setText("Bàn " + String.valueOf(IdTable));

        createListProduct();

        relativeDetails = (RelativeLayout) findViewById(R.id.relativeProductDetails);
        relativeAdd = (RelativeLayout) findViewById(R.id.relativeAddProduct);

        btnMonmoi = (Button) findViewById(R.id.btnMonmoi);
        btnMonmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeAdd.setVisibility(View.VISIBLE);
                relativeDetails.setVisibility(View.GONE);
                createListProduct();

            }
        });

        btnThanhToan = (Button) findViewById(R.id.btnThanhtoan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListTableDetails.this);
                // Get the layout inflater
                LayoutInflater inflater = ListTableDetails.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.dialog_confirm, null))
                        .setTitle("Bạn muốn thanh toán?")
                                // Add action buttons
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                conn.Open();
                                conn.updateStateForBill(IdTable);
                                conn.Close();
                                createListProduct();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        createListProductGroup();
        createListProduct();

    }

    public void createListProduct() {
        ListView listView = (ListView) findViewById(R.id.listProductDetails);
        conn.Open();
        listProductDetails = conn.getListProductOfTable(IdTable);
        conn.Close();
        ListProductDetailsAdapter adapter = new ListProductDetailsAdapter(this, listProductDetails);
        listView.setAdapter(adapter);

        TextView countProduct = (TextView) findViewById(R.id.textTongsomon);
        countProduct.setText("Tổng số món: " + String.valueOf(listProductDetails.size()));
    }

    public void createListProductGroup() {
        ListView listProductDetailsForAdd = (ListView) findViewById(R.id.listProductDetailsForAdd);
        conn.Open();
        listProductGroup = conn.getListProduct();
        conn.getProductDetails();
        conn.Close();
        AddProductDetailsAdaper adaper = new AddProductDetailsAdaper(this, listProductGroup, IdTable, relativeDetails, relativeAdd);
        listProductDetailsForAdd.setAdapter(adaper);
    }

    public class ListProductDetailsAdapter extends BaseAdapter {
        List<ProductDetails> listProductDetail = new ArrayList<ProductDetails>();
        Activity context;

        public ListProductDetailsAdapter(Activity context, List<ProductDetails> listProductDetail) {
            this.listProductDetail = listProductDetail;
            this.context = context;
        }
        @Override
        public int getCount() {
            return listProductDetail.size();
        }

        @Override
        public Object getItem(int position) {
            return listProductDetail.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ProductDetails productDetails = listProductDetail.get(position);
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.table_item_details, null);

            TextView productName = (TextView) rowView.findViewById(R.id.tvProductName);
            productName.setText(String.valueOf(position+1) + ". " + String.valueOf(productDetails.getProduct().getProductName()));

            TextView unitSales = (TextView) rowView.findViewById(R.id.tvUnitSales);
            unitSales.setText(String.valueOf(productDetails.getUnitSales()));

            TextView donviBan = (TextView) rowView.findViewById(R.id.tvDonviban);
            donviBan.setText( productDetails.getProduct().getUnit());

            return rowView;
        }
    }

    public class AddProductDetailsAdaper extends BaseAdapter {
        List<Product> listProductGroup = new ArrayList<Product>();
        DatabaseConnection conn = new DatabaseConnection();
        int TableID = 1;
        EditText unitSales;
        Activity context;
        RelativeLayout relativeDetails;
        RelativeLayout relativeAdd;

        public AddProductDetailsAdaper(Activity context, List<Product> listProductGroup, int TableID, RelativeLayout relativeDetails, RelativeLayout relativeAdd) {
            this.listProductGroup = listProductGroup;
            this.context = context;
            this.TableID = TableID;
            this.relativeAdd = relativeAdd;
            this.relativeDetails = relativeDetails;
        }

        @Override
        public int getCount() {
            return listProductGroup.size();
        }

        @Override
        public Object getItem(int position) {
            return listProductGroup.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Product product = listProductGroup.get(position);
            LayoutInflater inflater = context.getLayoutInflater();
            final View rowView = inflater.inflate(R.layout.add_product_item, null);

            TextView productName = (TextView) rowView.findViewById(R.id.tvProductName);
            productName.setText(String.valueOf(product.getProductName()));

            //When user click each item
            ImageView imgAddProduct = (ImageView) rowView.findViewById(R.id.imgAddProduct);
            imgAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conn.Open();
                    unitSales = (EditText) rowView.findViewById(R.id.editSoluong);
                    if (unitSales.getText().length() != 0 && Integer.valueOf(unitSales.getText().toString()) != 0) {
                        conn.InsertProductForBill(product, Integer.valueOf(unitSales.getText().toString()), TableID);
                    }
                    conn.Close();

                    relativeAdd.setVisibility(View.GONE);
                    relativeDetails.setVisibility(View.VISIBLE);
                    createListProduct();
                }
            });

            return rowView;
        }
    }

}
