package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;

/**
 * Created by Huynh Thanh Nha on 12-Nov-15.
 */
public class HomeActivity extends Activity {
    ImageView imgOfficer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        System.out.println("Login: ID " + Login.getIdUser());
        //
        imgOfficer = (ImageView) findViewById(R.id.imgOfficer);
        imgOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OfficerDetailsActivity.class));
            }
        });
    }
}
