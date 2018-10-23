package com.smileparser.automator.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smileparser.automator.fragment.Actionfrag;
import com.smileparser.automator.fragment.Constraintfrag;
import com.smileparser.automator.fragment.Triggerfrag;
import com.smileparser.automator.R;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.triggeractionmanager.Macro;
import com.smileparser.automator.triggeractionmanager.TriggerService;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SharedPrefs;
import com.smileparser.automator.utils.Util;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    String sType;
    LinearLayout lnrToolbar;
    TextView txtTitle, txtPrevTitle;
    ImageView imgTrigger, imgAction, imgConstraints, imgPreview, arrawPreview;
    ScrollView scrTrigger, scrActions, scrConstraints;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    Triggerfrag createnewfrag;
    Actionfrag actionfrag;
    Constraintfrag constraintfrag;
    public Macro macro = new Macro();
    private FloatingActionButton saveMacroFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        findViewById(R.id.imgMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        saveMacroFab = findViewById(R.id.save_macro);
        saveMacroFab.setOnClickListener(this);
        imgTrigger = findViewById(R.id.imgTriggers);
        imgTrigger.setOnClickListener(this);
        imgAction = findViewById(R.id.imgActions);
        imgAction.setOnClickListener(this);
        imgConstraints = findViewById(R.id.imgConstraints);
        imgConstraints.setOnClickListener(this);
        txtTitle = findViewById(R.id.txtTitle);
        lnrToolbar = findViewById(R.id.toolbar_old);

        imgPreview = findViewById(R.id.imgPreview);
        imgTrigger.performClick();
        createnewfrag = new Triggerfrag();
        fm = getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame, createnewfrag);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgTriggers:
                createnewfrag = new Triggerfrag();
                fm = getSupportFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, createnewfrag);
                fragmentTransaction.commit();
                lnrToolbar.setBackgroundColor(getResources().getColor(R.color.red));
                txtTitle.setText("Create New");
                imgPreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sType = "1";
                        Intent intent = new Intent( CreateActivity.this, Previous_trigger_Activity.class );
                        intent.putExtra( "Key", sType );

                        startActivity( intent );
                    }
                });
                imgPreview.setImageResource(R.drawable.ic_preview_red);
                imgTrigger.setImageResource(R.drawable.ic_triggers_red);
                imgAction.setImageResource(R.drawable.ic_action_red);
                imgConstraints.setImageResource(R.drawable.ic_constraints_red);
                break;


            case R.id.imgActions:
                lnrToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                txtTitle.setText("Actions");
                imgPreview.setImageResource(R.drawable.ic_preview_blue);
                imgTrigger.setImageResource(R.drawable.ic_trigger_blue);
                imgAction.setImageResource(R.drawable.ic_action_blue);
                imgConstraints.setImageResource(R.drawable.ic_constraints_blue);
                actionfrag = new Actionfrag();
                fm = getSupportFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, actionfrag);
                fragmentTransaction.commit();
                imgPreview.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        sType = "2";
                        Intent intent = new Intent(CreateActivity.this, Previous_trigger_Activity.class);
                        intent.putExtra("Key", sType);
                        startActivity(intent);
                    }
                });
                break;

            case R.id.imgConstraints:
                lnrToolbar.setBackgroundColor(getResources().getColor(R.color.green));
                txtTitle.setText("Constraints");
                imgPreview.setImageResource(R.drawable.ic_preview_green);
                imgTrigger.setImageResource(R.drawable.ic_trigger_green);
                imgAction.setImageResource(R.drawable.ic_action_green);
                imgConstraints.setImageResource(R.drawable.ic_constraints_green);
                constraintfrag = new Constraintfrag();
                fm = getSupportFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, constraintfrag);
                fragmentTransaction.commit();
                imgPreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sType = "3";
                        Intent intent = new Intent(CreateActivity.this, Previous_trigger_Activity.class);
                        intent.putExtra("Key", sType);
                        startActivity(intent);
                    }
                });
                break;

            case R.id.save_macro:
                saveMacroToDb();
                break;

            default:
                break;
        }
    }

    private void saveMacroToDb() {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(this);
        long macroId = databaseHelper.getMacroDao().insert(macro);
        macro.setId(macroId);
        macro.getTriggerDetails().setMacroId(macroId);
        macro.getActionDetails().setMacroId(macroId);
        if (macro.getConstraintDetails() != null)
            macro.getConstraintDetails().setMacroId(macroId);

        databaseHelper.getTriggerDetailsDao().insert(macro.getTriggerDetails());
        databaseHelper.getActionDetailsDao().insert(macro.getActionDetails());
        if (macro.getConstraintDetails() != null)
            databaseHelper.getConstraintDetailsDao().insert(macro.getConstraintDetails());

        refreshServiceIfAlreadyRunning();

        Util.showToast(this, "Macro added.");
        finish();
    }

    void refreshServiceIfAlreadyRunning() {
        boolean isMacroEnabled = SharedPrefs.getPreference(this).getBoolean(Constants.IS_MACRO_ENABLED, false);
        Intent triggerService = new Intent(this, TriggerService.class);
        if (isMacroEnabled) {
            startService(triggerService);
        } else {
            stopService(triggerService);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
