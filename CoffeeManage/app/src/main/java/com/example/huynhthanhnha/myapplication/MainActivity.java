package com.example.huynhthanhnha.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    Button btn;
    TextView tUsername;
    TextView tPassword;
    DatabaseConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseConnection(this.getFilesDir().toString());
        db.Open();
        //db.InitData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.signIn);
        tUsername = (TextView) findViewById(R.id.textUsername);
        tPassword = (TextView) findViewById(R.id.textPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.test();
                int value = db.CheckLogin(tUsername.getText().toString(), tPassword.getText().toString());
                if (value == 1) startActivity(new Intent(MainActivity.this, Home.class));
                else if(value == 2) startActivity(new Intent(MainActivity.this, HomeOfficer.class));
                     else System.out.println("Value login is: " + String.valueOf(value));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
