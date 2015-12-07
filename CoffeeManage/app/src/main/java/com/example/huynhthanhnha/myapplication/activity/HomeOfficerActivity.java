package com.example.huynhthanhnha.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.R;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class HomeOfficerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RelativeLayout linearLayout;
    RelativeLayout imgGroup;
    RelativeLayout imgPrice;
    RelativeLayout product;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_officer);
        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        imgGroup = (RelativeLayout) findViewById(R.id.demo);
        imgGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGroup.startAnimation(anim);
                startActivity(new Intent(HomeOfficerActivity.this, ShowGroupActivity.class));
            }
        });

        imgPrice = (RelativeLayout) findViewById(R.id.listPrice);
        imgPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPrice.startAnimation(anim);
                startActivity(new Intent(HomeOfficerActivity.this, ShowPriceActivity.class));
            }
        });


        linearLayout = (RelativeLayout) findViewById(R.id.linearListTable);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.startAnimation(anim);
                startActivity(new Intent(HomeOfficerActivity.this, GridTableActivity.class));
            }
        });

        product = (RelativeLayout) findViewById(R.id.linearListProduct);
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.startAnimation(anim);
                startActivity(new Intent(HomeOfficerActivity.this, ListProductActivity.class));
            }
        });

    }


}





/*

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        final Animation myAnimpress = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        final LinearLayout myButton = (LinearLayout) findViewById(R.id.linearListTable);
        myButton.setAnimation(myAnimpress);
 */
