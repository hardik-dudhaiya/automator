package com.smileparser.automator.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.triggeractionmanager.TriggerDao;
import com.smileparser.automator.triggeractionmanager.TriggerService;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SharedPrefs;

public class ActivityMainPage extends AppCompatActivity {

    private DrawerLayout _drawerLayout;
    private LinearLayout _toolbar, _bottomBar;
    private SwitchCompat _enableEvent;
    private RelativeLayout _rlvMyList, _rlvCreateNew, _rlvTemplates, _rlvSettings;
    private RelativeLayout _rlvExport, _rlvExit;
    private ImageView _imgExit, _imgExport, _imgSettings, _imgTemplates, _imgMyList, _imgCreateNew, _imgMenu;
    private NavigationView _navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        handleClicks();
    }

    private void assignViews() {
        _drawerLayout = findViewById(R.id.drawerLayout);
        _toolbar = findViewById(R.id.toolbar);
        _imgMenu = findViewById(R.id.imgMenu);
        _enableEvent = findViewById(R.id.enable_event);
        _bottomBar = findViewById(R.id.bottomBar);
        _rlvMyList = findViewById(R.id.rlvMyList);
        _imgMyList = findViewById(R.id.imgMyList);
        _rlvCreateNew = findViewById(R.id.rlvCreateNew);
        _imgCreateNew = findViewById(R.id.imgCreateNew);
        _rlvTemplates = findViewById(R.id.rlvTemplates);
        _imgTemplates = findViewById(R.id.imgTemplates);
        _rlvSettings = findViewById(R.id.rlvSettings);
        _imgSettings = findViewById(R.id.imgSettings);
        _rlvExport = findViewById(R.id.rlvExport);
        _imgExport = findViewById(R.id.imgExport);
        _rlvExit = findViewById(R.id.rlvExit);
        _imgExit = findViewById(R.id.imgExit);
        _navView = findViewById(R.id.nav_view);
        _enableEvent.setChecked(SharedPrefs.getBoolPref(Constants.IS_MACRO_ENABLED));
        manageService(SharedPrefs.getBoolPref(Constants.IS_MACRO_ENABLED));

        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(this);
        TriggerDao triggerDao = databaseHelper.getTriggerDao();
        triggerDao.getAll();//db gets created because of this :)
    }

    private void handleClicks() {
        _imgMenu.setOnClickListener(v -> {
            _drawerLayout.openDrawer(Gravity.START);
        });

        _navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.upgrade_to_pro: {
                    toggleDrawer();
                    Toast.makeText(ActivityMainPage.this, "Under Development", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    toggleDrawer();
                    Toast.makeText(ActivityMainPage.this, "Nothing", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });

        _rlvCreateNew.setOnClickListener(V -> startActivity(new Intent(ActivityMainPage.this, ActivityCreateMacroPage.class)));

        _rlvExit.setOnClickListener(V -> onBackPressed());

        _rlvExport.setOnClickListener(V -> {
            final Dialog dialog = new Dialog(ActivityMainPage.this, R.style.creativeDialogTheme);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.show();
        });

        _enableEvent.setOnCheckedChangeListener((compoundButton, enabled) -> {
            manageService(enabled);
            SharedPrefs.SaveBooleanData(Constants.IS_MACRO_ENABLED, enabled);
        });
    }

    private void toggleDrawer() {
        if (_drawerLayout.isDrawerOpen(GravityCompat.START))
            _drawerLayout.closeDrawer(GravityCompat.START);
        else
            _drawerLayout.openDrawer(GravityCompat.START);
    }

    void manageService(boolean isMacroEnabled) {
        Intent triggerService = new Intent(ActivityMainPage.this, TriggerService.class);
        if (isMacroEnabled) {
            startService(triggerService);
        } else {
            stopService(triggerService);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            toggleDrawer();
        } else {
            super.onBackPressed();
        }
    }
}