package com.smileparser.automator.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smileparser.automator.R;

public class ActivitySplashPage extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splashscreen );

        //runnable method//
        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {

                startActivity( new Intent( ActivitySplashPage.this,ActivityMainPage.class ) );
                finish();
            }
        },SPLASH_TIME_OUT );
    }
}