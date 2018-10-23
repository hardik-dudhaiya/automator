package com.smileparser.automator.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.triggeractionmanager.TriggerDao;
import com.smileparser.automator.triggeractionmanager.TriggerService;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    final Context context = this;
    RelativeLayout createLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   getActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.drawerLayout);
        createLayout = findViewById(R.id.rlvCreateNew);



        findViewById(R.id.imgMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );

    navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

       int id=item.getItemId();{
    if (id==R.id.upgrade_to_pro){

        Toast.makeText( MainActivity.this,"Upgradete",Toast.LENGTH_SHORT );
    }

            }
            DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawerLayout );
            drawer.closeDrawer( GravityCompat.START );
            return true;

        }
    } );
      //  addMenuItemInNavMenuDrawer();

        createLayout.setOnClickListener(this);

        /*createLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });*/
        findViewById(R.id.rlvExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById( R.id.rlvExport ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create a Dialog component
                final Dialog dialog = new Dialog( context,R.style.creativeDialogTheme );
                //tell the Dialog to use the custom_dialog.xml as it's layout description
                dialog.setContentView( R.layout.custom_dialog );
                dialog.show();



            }
        } );

        SwitchCompat macroEnabledSwitch = findViewById(R.id.enable_event);
        boolean isMacroEnabled = SharedPrefs.getPreference(this).getBoolean(Constants.IS_MACRO_ENABLED, false);
        macroEnabledSwitch.setChecked(isMacroEnabled);
        manageService(isMacroEnabled);

        macroEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean enabled) {
                manageService(enabled);
                SharedPrefs.getPreference(MainActivity.this).edit().
                        putBoolean(Constants.IS_MACRO_ENABLED, enabled).commit();

            }
        });

        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(this);
        TriggerDao triggerDao = databaseHelper.getTriggerDao();
        triggerDao.getAll();//db gets created because of this :)
    }

    void manageService(boolean isMacroEnabled) {
        Intent triggerService = new Intent(MainActivity.this, TriggerService.class);
        if (isMacroEnabled) {
            startService(triggerService);
        } else {
            stopService(triggerService);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.rlvCreateNew:
                startActivity(new Intent(MainActivity.this, CreateMacroActivity.class));
                break;

        }
    }
}



