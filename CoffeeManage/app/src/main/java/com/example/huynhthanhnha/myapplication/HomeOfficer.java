package com.example.huynhthanhnha.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by NguyenThanh on 15/11/2015.
 */
public class HomeOfficer extends Activity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_officer);


        linearLayout = (LinearLayout) findViewById(R.id.linearListTable);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
