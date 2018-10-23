package com.smileparser.automator.Activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileparser.automator.R;

public class Previous_trigger_Activity extends AppCompatActivity  {


    LinearLayout lnrToolbarr;
    TextView txtPrevTitle;
    String stYpe;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_previous_trigger_ );
        stYpe = getIntent().getStringExtra( "Key" );
        lnrToolbarr = findViewById( R.id.toolbarr );
        txtPrevTitle = findViewById( R.id.txtPrevTitle );
        lnrToolbarr = findViewById( R.id.toolbarr );
        txtPrevTitle.setText( "Preview" );


        if (stYpe.endsWith("1")) {
            lnrToolbarr.setBackgroundColor(R.color.dark_red );
        } else if (stYpe.endsWith("2")) {
            lnrToolbarr.setBackgroundColor(R.color.blue );
        } else if (stYpe.endsWith("3")) {
            lnrToolbarr.setBackgroundColor( R.color.green );
        } else {
        }





        //onbackpressed//
        findViewById( R.id.arraw ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        } );

    }


}
