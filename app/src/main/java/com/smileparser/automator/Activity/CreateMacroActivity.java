package com.smileparser.automator.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smileparser.automator.Fragment.Actionfrag;
import com.smileparser.automator.Fragment.Constraintfrag;
import com.smileparser.automator.Fragment.Triggerfrag;
import com.smileparser.automator.R;
import com.smileparser.automator.adapter.ViewPagerAdapter;

public class CreateMacroActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "home";
    LinearLayout lnrToolbar;
    TextView txtTitle;
    ImageView imgPreview;
    String sType;
    private ViewPager viewPager;
    MenuItem prevMenuItem;
    BottomNavigationView bottomNavigationView;
    Triggerfrag triggerfrag;
    Actionfrag actionfrag;
    Constraintfrag constraintfrag;

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.imgPreview:
                Intent intent = new Intent( CreateMacroActivity.this, Previous_trigger_Activity.class );
                intent.putExtra( "Key", sType );

                startActivity( intent );
                break;
        }
    }

    class C05691 implements BottomNavigationView.OnNavigationItemSelectedListener {
        C05691() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Bundle bundle1;
            switch (item.getItemId()) {
                case R.id.menu_triggers:
                    /*viewPager.setCurrentItem(0);
                    load(0);*/
                    CreateMacroActivity.this.TAG = "triggers";
                    //selectedFragment = new HomeFragment();
                    imgPreview.setImageResource(R.drawable.ic_preview_red);
                    lnrToolbar.setBackgroundColor(getResources().getColor(R.color.red));
                    bottomNavigationView.setItemBackgroundResource(R.color.red);
                    sType = "1";
                    selectedFragment = new Triggerfrag();
                    Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

                    break;
                case R.id.menu_actions:
                    /*viewPager.setCurrentItem(1);
                    load(1);*/
                    CreateMacroActivity.this.TAG = "actions";
                    lnrToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                    imgPreview.setImageResource(R.drawable.ic_preview_blue);
                    bottomNavigationView.setItemBackgroundResource(R.color.blue);
                    sType = "2";

                    selectedFragment = new Actionfrag();

                    Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

                    break;
                case R.id.menu_constraints:
                    /*viewPager.setCurrentItem(2);
                    load(2);*/
                    CreateMacroActivity.this.TAG = "constraints";
                    lnrToolbar.setBackgroundColor(getResources().getColor(R.color.green));
                    imgPreview.setImageResource(R.drawable.ic_preview_green);
                    sType = "3";
                    bottomNavigationView.setItemBackgroundResource(R.color.green);
                    selectedFragment = new Constraintfrag();

                    Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
            CreateMacroActivity.this.openFragment(selectedFragment);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_macro);

        lnrToolbar = findViewById(R.id.toolbar);
        txtTitle = findViewById(R.id.txtTitle);
        imgPreview = findViewById(R.id.imgPreview);
        txtTitle.setText("Create New");
        imgPreview.setOnClickListener(this);

       // viewPager = (ViewPager) findViewById(R.id.frm_container);

         bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new C05691());
        bottomNavigationView.setSelectedItemId(R.id.menu_triggers);


        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                //load(position);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        //setupViewPager(viewPager);

    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frm_container, fragment, this.TAG);
        ft.commit();
    }

   /* private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        triggerfrag=new Triggerfrag();
        actionfrag=new Actionfrag();
        constraintfrag=new Constraintfrag();
        adapter.addFragment(triggerfrag);
        adapter.addFragment(actionfrag);
        adapter.addFragment(constraintfrag);
        viewPager.setAdapter(adapter);
    }


    public  void load(int position)
    {
        if(position == 0)
        {
            CreateMacroActivity.this.TAG = "triggers";
            //selectedFragment = new HomeFragment();
            imgPreview.setImageResource(R.drawable.ic_preview_red);
            lnrToolbar.setBackgroundColor(getResources().getColor(R.color.red));
            sType = "1";
            //selectedFragment = new Triggerfrag();
            Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

        }
        else if (position == 1)
        {
            CreateMacroActivity.this.TAG = "actions";
            lnrToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
            imgPreview.setImageResource(R.drawable.ic_preview_blue);
            sType = "2";

            //selectedFragment = new Actionfrag();

            Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

        }
        else if(position == 2)
        {
            CreateMacroActivity.this.TAG = "constraints";
            lnrToolbar.setBackgroundColor(getResources().getColor(R.color.green));
            imgPreview.setImageResource(R.drawable.ic_preview_green);
            sType = "3";
            //selectedFragment = new Constraintfrag();

            Toast.makeText(CreateMacroActivity.this, ""+TAG, Toast.LENGTH_SHORT).show();

        }

    }*/
}
