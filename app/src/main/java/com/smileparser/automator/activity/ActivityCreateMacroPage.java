package com.smileparser.automator.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.fragment.FragmentChildPage;
import com.smileparser.automator.fragment.FragmentMainPage;
import com.smileparser.automator.fragment.Inner_action_Fragment;
import com.smileparser.automator.fragment.Inner_constraint_Fragment;
import com.smileparser.automator.fragment.Inner_createnew_Fragment;
import com.smileparser.automator.utils.SecondaryTextView;

import javax.annotation.Nullable;

public class ActivityCreateMacroPage extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static ActivityCreateMacroPage sInstance;
    Bundle bundle = new Bundle();
    private LinearLayout _toolbar;
    private ImageView _imgMenu, _imgPreview;
    private SecondaryTextView _txtTitle;
    private BottomNavigationView _bottomNavigation;
    private String sType = "";

    public static ActivityCreateMacroPage getInstance() {
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        setContentView(R.layout.activity_create_macro);
        assignViews();
        handleClicks();
        _bottomNavigation.setSelectedItemId(R.id.menu_triggers);
    }

    private void assignViews() {
        _toolbar = findViewById(R.id.toolbar);
        _imgMenu = findViewById(R.id.imgMenu);
        _txtTitle = findViewById(R.id.txtTitle);
        _imgPreview = findViewById(R.id.imgPreview);
        _bottomNavigation = findViewById(R.id.bottomNavigation);
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
        openFragment(new FragmentChildPage(),tag);

        /*switch (callType) {
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
        }*/
    }
}