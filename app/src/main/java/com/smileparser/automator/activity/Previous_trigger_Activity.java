package com.smileparser.automator.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileparser.automator.R;

public class Previous_trigger_Activity extends AppCompatActivity {
    LinearLayout lnrToolbarr;
    TextView txtPrevTitle;
    String stYpe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_trigger_);
        if (getIntent() != null) {
            if (getIntent().getStringExtra("Key") != null) {
                stYpe = getIntent().getStringExtra("Key");
            }
        }
        assignViews();

        switch (stYpe) {
            case "1":
                lnrToolbarr.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_red));
                break;

            case "2":
                lnrToolbarr.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
                break;

            case "3":
                lnrToolbarr.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                break;
        }

        findViewById(R.id.arraw).setOnClickListener(v -> previewBack());
    }

    private void assignViews() {
        lnrToolbarr = findViewById(R.id.toolbarr);
        txtPrevTitle = findViewById(R.id.txtPrevTitle);
        lnrToolbarr = findViewById(R.id.toolbarr);
        txtPrevTitle.setText("Preview");
    }

    @Override
    public void onBackPressed() {
        previewBack();
        super.onBackPressed();
    }

    private void previewBack() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }
}