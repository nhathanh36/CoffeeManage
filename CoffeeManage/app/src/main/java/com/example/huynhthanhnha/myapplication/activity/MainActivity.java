package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.form.DatabaseConnection;
import com.example.huynhthanhnha.myapplication.R;

import java.util.ArrayList;

import EDU.purdue.cs.bloat.decorate.Main;


public class MainActivity extends Activity {
    Button btn;
    TextView tUsername;
    TextView tPassword;
    DatabaseConnection conn = new DatabaseConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.out.println(this.getDir("data",0));
        //conn = new DatabaseConnection(this.getFilesDir().toString());
        //startActivity(new Intent(MainActivity.this, HomeOfficerActivity.class));

        btn = (Button) findViewById(R.id.signIn);
        tUsername = (TextView) findViewById(R.id.textUsername);
        tPassword = (TextView) findViewById(R.id.textPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conn.Open();
                int value = conn.CheckLogin(tUsername.getText().toString(), tPassword.getText().toString());

                if (value == 1) {
                    Login.setUser(conn.getUser(tUsername.getText().toString()));
                    conn.Close();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else if (value == 2) {
                    Login.setUser(conn.getUser(tUsername.getText().toString()));
                    startActivity(new Intent(MainActivity.this, HomeOfficerActivity.class));
                    conn.Close();
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT);
                    toast.show();
                    conn.Close();
                }
            }
        });

        registerForContextMenu(btn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
        menu.add(0, v.getId(), 0, "Action 3");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
