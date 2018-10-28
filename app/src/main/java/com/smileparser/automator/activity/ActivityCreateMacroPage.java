package com.smileparser.automator.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.App;
import com.smileparser.automator.R;
import com.smileparser.automator.db_helper.ActionModel;
import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.db_helper.MacroModel;
import com.smileparser.automator.db_helper.TriggerModel;
import com.smileparser.automator.fragment.FragmentMainPage;
import com.smileparser.automator.fragment.Inner_action_Fragment;
import com.smileparser.automator.fragment.Inner_constraint_Fragment;
import com.smileparser.automator.fragment.Inner_createnew_Fragment;
import com.smileparser.automator.triggeractionmanager.TriggerService;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SecondaryTextView;
import com.smileparser.automator.utils.SharedPrefs;
import com.smileparser.automator.utils.Utility;

import java.sql.SQLException;

import javax.annotation.Nullable;

public class ActivityCreateMacroPage extends BaseActivity {

    @SuppressLint("StaticFieldLeak")
    public static ActivityCreateMacroPage sInstance;
    public MacroModel macro;
    Bundle bundle = new Bundle();
    boolean AllGranted = false;
    private LinearLayout _toolbar;
    private ImageView _imgMenu, _imgPreview;
    private SecondaryTextView _txtTitle;
    private BottomNavigationView _bottomNavigation;
    private FloatingActionButton _saveMacro;
    private String sType = "";

    public static ActivityCreateMacroPage getInstance() {
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        //setContentView(R.layout.activity_create_macro);
        assignViews();
        handleClicks();
        _bottomNavigation.setSelectedItemId(R.id.menu_triggers);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_create_macro;
    }

    private void assignViews() {
        macro = new MacroModel();
        _toolbar = findViewById(R.id.toolbar);
        _imgMenu = findViewById(R.id.imgMenu);
        _txtTitle = findViewById(R.id.txtTitle);
        _imgPreview = findViewById(R.id.imgPreview);
        _bottomNavigation = findViewById(R.id.bottomNavigation);
        _saveMacro = findViewById(R.id.save_macro);
    }

    private void handleClicks() {
        _bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_triggers: {
                    sType = "1";
                    _imgPreview.setImageResource(R.drawable.ic_preview_red);
                    _toolbar.setBackgroundColor(getResources().getColor(R.color.red));
                    _bottomNavigation.setItemBackgroundResource(R.color.red);
                    Toast.makeText(this, "Load: - Triggers", Toast.LENGTH_SHORT).show();
                    setTitle();
                    bundle.putString("callingPage", sType);
                    openFragment(new FragmentMainPage(), "Triggers");
                }
                break;

                case R.id.menu_actions: {
                    sType = "2";
                    _imgPreview.setImageResource(R.drawable.ic_preview_blue);
                    _toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                    _bottomNavigation.setItemBackgroundResource(R.color.blue);
                    Toast.makeText(this, "Load: - Actions", Toast.LENGTH_SHORT).show();
                    setTitle();
                    bundle.putString("callingPage", sType);
                    openFragment(new FragmentMainPage(), "Actions");
                }
                break;

                case R.id.menu_constraints: {
                    sType = "3";
                    _imgPreview.setImageResource(R.drawable.ic_preview_green);
                    _toolbar.setBackgroundColor(getResources().getColor(R.color.green));
                    _bottomNavigation.setItemBackgroundResource(R.color.green);
                    Toast.makeText(this, "Load: - Constrains", Toast.LENGTH_SHORT).show();
                    setTitle();
                    bundle.putString("callingPage", sType);
                    openFragment(new FragmentMainPage(), "Constrains");
                }
                break;
            }
            return false;
        });

        _imgPreview.setOnClickListener(V -> startActivity(new Intent(ActivityCreateMacroPage.this, Previous_trigger_Activity.class).putExtra("Key", sType)));

        _imgMenu.setOnClickListener(V -> previewBack());

        _saveMacro.setOnClickListener(V -> {
            if (macro != null) {
                if (macro.getTriggerModel() != null) {
                    if (macro.getActionModel() != null)
                        saveMacroToDb();
                    else
                        Utility.showToast(this, "Please Select At Least one action");
                } else
                    Utility.showToast(this, "Please Select At Least one trigger");
            }
        });
    }

    private void setTitle() {
        _txtTitle.setText("Create New");
    }

    private void openFragment(Fragment myFragment, String tag) {
        if (bundle != null) {
            myFragment.setArguments(bundle);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.frm_container, myFragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void previewBack() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_left);
        finish();
    }

    private void saveMacroToDb() {

        Dao<MacroModel, Integer> macroModels = App.getDataBaseHelper().getDao(MacroModel.class);
        try {
            long macroId = macroModels.create(macro);
            macro.setId(macroId);
            macro.getTriggerModel().setMacroId(macroId);
            macro.getActionModel().setMacroId(macroId);

            if (macro.getConstraintModel() != null)
                macro.getConstraintModel().setMacroId(macroId);

            App.getDataBaseHelper().getDao(TriggerModel.class).create(macro.getTriggerModel());

            App.getDataBaseHelper().getDao(ActionModel.class).create(macro.getActionModel());

            if (macro.getConstraintModel() != null)
                App.getDataBaseHelper().getDao(ConstraintModel.class).create(macro.getConstraintModel());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        refreshServiceIfAlreadyRunning();

        Utility.showToast(this, "Macro Created Successfully");
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
    public void onBackPressed() {
        previewBack();
        super.onBackPressed();
    }

    public void loadInnerFragment(@Nullable Bundle bundle, Fragment innerFragment, String tag) {
        if (bundle != null) {
            this.bundle = bundle;
        }
        openFragment(innerFragment, tag);
    }

    public void loadInnerFragmentByType(@Nullable Bundle bundle, String callType, String tag) {
        if (bundle != null) {
            this.bundle = bundle;
        }
        //openFragment(new FragmentChildPage(), tag);

        switch (callType) {
            case "1": {//Triggers
                openFragment(new Inner_createnew_Fragment(), tag);
            }
            break;

            case "2": {//Actions
                openFragment(new Inner_action_Fragment(), tag);
            }
            break;

            case "3": {//Constraints
                openFragment(new Inner_constraint_Fragment(), tag);
            }
            break;
        }
    }
}