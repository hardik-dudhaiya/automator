package com.smileparser.automator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.R;
import com.smileparser.automator.db_helper.CategoryModel;
import com.smileparser.automator.db_helper.MyDatabaseHelper;
import com.smileparser.automator.db_helper.SubCategoryModel;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SharedPrefs;

import java.sql.SQLException;

public class ActivitySplashPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //SharedPrefs.SaveStringData(Constants.IS_FIRST_LAUNCH, "");
        if (SharedPrefs.getStringPref(Constants.IS_FIRST_LAUNCH).equalsIgnoreCase("")) {
            MyDatabaseHelper helper = new MyDatabaseHelper(this);
            Dao<CategoryModel, Integer> categoryDao = helper.getDao(CategoryModel.class);
            Dao<SubCategoryModel, Integer> subcategoryDao = helper.getDao(SubCategoryModel.class);
            try {
                /*Save Triggers*/
                saveTriggers(categoryDao, subcategoryDao);

                /*Save Actions*/
                String actionLabels = getResources().getString(R.string.actions_labels);
                for (String string : actionLabels.split(",")) {
                    CategoryModel user = new CategoryModel();
                    user.setName(string);
                    categoryDao.createOrUpdate(user);
                }

                /*Save Constraints*/
                String constraintLabels = getResources().getString(R.string.constraint_labels);
                for (String string : constraintLabels.split(",")) {
                    CategoryModel user = new CategoryModel();
                    user.setName(string);
                    categoryDao.createOrUpdate(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            SharedPrefs.SaveStringData(Constants.IS_FIRST_LAUNCH, "1");
        } else {
            try {
                MyDatabaseHelper helper = new MyDatabaseHelper(this);
                Dao<CategoryModel, Integer> userDao = helper.getDao(CategoryModel.class);

                CategoryModel categoryModel = userDao.queryForId(1);
                Log.e("Android->", "onCreate: " + new Gson().toJson(categoryModel));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        new Handler().postDelayed(() -> {
            startActivity(new Intent(ActivitySplashPage.this, ActivityMainPage.class));
            finish();
        }, 3000);
    }

    private void saveTriggers(Dao<CategoryModel, Integer> categoryDao, Dao<SubCategoryModel, Integer> subcategoryDao) throws SQLException {
        String triggerLabels = getResources().getString(R.string.trigger_labels);
        for (String string : triggerLabels.split(",")) {
            CategoryModel user = new CategoryModel();
            user.setName(string);
            categoryDao.createOrUpdate(user);
        }

        /*Save Inner Triggers*/
        String s1 = getResources().getString(R.string.tr_battery_labels);
        for (String string : s1.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 1);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }

        String s2 = getResources().getString(R.string.tr_call_sms_labels);
        for (String string : s2.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 2);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }

        String s3 = getResources().getString(R.string.tr_connectivity_labels);
        for (String string : s3.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 3);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }

        String s4 = getResources().getString(R.string.tr_date_time_labels);
        for (String string : s4.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 4);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }

        String s5 = getResources().getString(R.string.tr_device_event_labels);
        for (String string : s5.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 5);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }

        String s6 = getResources().getString(R.string.tr_location_labels);
        for (String string : s6.split(",")) {
            SubCategoryModel model = new SubCategoryModel();
            model.setcId((long) 6);
            model.setName(string);
            subcategoryDao.createOrUpdate(model);
        }
    }
}
