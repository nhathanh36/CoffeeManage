package com.example.huynhthanhnha.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        //final Animation animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
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
