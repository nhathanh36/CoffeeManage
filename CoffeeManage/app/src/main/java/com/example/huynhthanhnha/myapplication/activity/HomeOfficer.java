package com.example.huynhthanhnha.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import android.widget.Button;
import android.widget.LinearLayout;

import com.example.huynhthanhnha.myapplication.ListTable;
import com.example.huynhthanhnha.myapplication.R;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class HomeOfficer extends AppCompatActivity {
    private Toolbar toolbar;
    LinearLayout linearLayout;
    private LinearLayout img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_officer);

        ///
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        img = (LinearLayout) findViewById(R.id.linearListProduct);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeOfficer.this, CustomViewIconTextTabsActivity.class));
            }
        });
        //

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        linearLayout = (LinearLayout) findViewById(R.id.linearListTable);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.startAnimation(anim);
                startActivity(new Intent(HomeOfficer.this, ListTable.class));
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
