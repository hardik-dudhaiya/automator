package com.smileparser.automator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.App;
import com.smileparser.automator.R;
import com.smileparser.automator.db_helper.ActionsLabelsModel;
import com.smileparser.automator.db_helper.ConstraintLabelsModel;
import com.smileparser.automator.db_helper.SubActionsLabelsModel;
import com.smileparser.automator.db_helper.SubConstraintLabelsModel;
import com.smileparser.automator.db_helper.SubTriggerLabelsModel;
import com.smileparser.automator.db_helper.TriggerLabelsModel;
import com.smileparser.automator.utils.Constants;
import com.smileparser.automator.utils.SharedPrefs;

import java.sql.SQLException;
import java.util.List;

public class ActivitySplashPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //SharedPrefs.SaveStringData(Constants.IS_FIRST_LAUNCH, "");
        if (SharedPrefs.getStringPref(Constants.IS_FIRST_LAUNCH).equalsIgnoreCase("")) {
            /*Save Triggers*/
            SaveTriggersLabels();

            /*Save Actions*/
            saveActionsLabels();

            /*Save Constraints*/
            saveConstraintsLabels();

            SharedPrefs.SaveStringData(Constants.IS_FIRST_LAUNCH, "1");
            new Handler().postDelayed(() -> {
                startActivity(new Intent(ActivitySplashPage.this, ActivityMainPage.class));
                finish();
            }, 2500);
        } else {
            /*try {
                Dao<TriggerLabelsModel, Integer> userDao = App.getDataBaseHelper().getDao(TriggerLabelsModel.class);
                List<TriggerLabelsModel> labelsModels = userDao.queryForAll();
                for (TriggerLabelsModel model : labelsModels) {
                    Log.e("Android->", "TriggerLabelsModel: " + new Gson().toJson(model));
                }

                Dao<ActionsLabelsModel, Integer> userDao1 = App.getDataBaseHelper().getDao(ActionsLabelsModel.class);
                List<ActionsLabelsModel> labelsModels1 = userDao1.queryForAll();
                for (ActionsLabelsModel model : labelsModels1) {
                    Log.e("Android->", "ActionsLabelsModel: " + new Gson().toJson(model));
                }

                Dao<ConstraintLabelsModel, Integer> userDao2 = App.getDataBaseHelper().getDao(ConstraintLabelsModel.class);
                List<ConstraintLabelsModel> labelsModels2 = userDao2.queryForAll();
                for (ConstraintLabelsModel model : labelsModels2) {
                    Log.e("Android->", "ConstraintLabelsModel: " + new Gson().toJson(model));
                }


                Dao<SubTriggerLabelsModel, Integer> userDao4 = App.getDataBaseHelper().getDao(SubTriggerLabelsModel.class);
                List<SubTriggerLabelsModel> labelsModels3 = userDao4.queryForAll();
                for (SubTriggerLabelsModel model : labelsModels3) {
                    Log.e("Android->", "SubTriggerLabelsModel: " + new Gson().toJson(model));
                }

                Dao<SubConstraintLabelsModel, Integer> userDao5 = App.getDataBaseHelper().getDao(SubConstraintLabelsModel.class);
                List<SubConstraintLabelsModel> labelsModels4 = userDao5.queryForAll();
                for (SubConstraintLabelsModel model : labelsModels4) {
                    Log.e("Android->", "SubConstraintLabelsModel: " + new Gson().toJson(model));
                }

                Dao<SubActionsLabelsModel, Integer> userDao6 = App.getDataBaseHelper().getDao(SubActionsLabelsModel.class);
                List<SubActionsLabelsModel> labelsModels6 = userDao6.queryForAll();
                for (SubActionsLabelsModel model : labelsModels6) {
                    Log.e("Android->", "SubActionsLabelsModel: " + new Gson().toJson(model));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }*/

            new Handler().postDelayed(() -> {
                startActivity(new Intent(ActivitySplashPage.this, ActivityMainPage.class));
                finish();
            }, 1500);
        }

    }

    private void saveConstraintsLabels() {
        Dao<ConstraintLabelsModel, Integer> constraintLabelsModels = App.getDataBaseHelper().getDao(ConstraintLabelsModel.class);
        String string2 = getResources().getString(R.string.constraint_labels);
        for (String string : string2.split(",")) {
            ConstraintLabelsModel user = new ConstraintLabelsModel();
            user.setName(string);
            try {
                constraintLabelsModels.createOrUpdate(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Dao<SubConstraintLabelsModel, Integer> subcategoryDao = App.getDataBaseHelper().getDao(SubConstraintLabelsModel.class);
        /*Save Inner Triggers*/
        String s1 = getResources().getString(R.string.constraint_bettery);
        for (String string : s1.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 1);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s2 = getResources().getString(R.string.constraint_bettery);
        for (String string : s2.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 2);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s3 = getResources().getString(R.string.constraint_connectivity_data);
        for (String string : s3.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 3);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s4 = getResources().getString(R.string.constraint_date_time_data);
        for (String string : s4.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 4);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s5 = getResources().getString(R.string.constraint_device_state_data);
        for (String string : s5.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 5);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s6 = getResources().getString(R.string.constraint_media_data);
        for (String string : s6.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 6);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s7 = getResources().getString(R.string.constraint_phone_data);
        for (String string : s7.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 7);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s8 = getResources().getString(R.string.constraint_screen_sensor_data);
        for (String string : s8.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 8);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s9 = getResources().getString(R.string.constraint_sensors_data);
        for (String string : s9.split(",")) {
            SubConstraintLabelsModel model = new SubConstraintLabelsModel();
            model.setcId((long) 9);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveActionsLabels() {
        Dao<ActionsLabelsModel, Integer> actionsLabelsModelIntegerDao = App.getDataBaseHelper().getDao(ActionsLabelsModel.class);
        String string1 = getResources().getString(R.string.actions_labels);
        for (String string : string1.split(",")) {
            ActionsLabelsModel user = new ActionsLabelsModel();
            user.setName(string);
            try {
                actionsLabelsModelIntegerDao.createOrUpdate(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Dao<SubActionsLabelsModel, Integer> subcategoryDao = App.getDataBaseHelper().getDao(SubActionsLabelsModel.class);
        /*Save Inner Triggers*/
        String s1 = getResources().getString(R.string.ap_application_labels);
        for (String string : s1.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 1);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s2 = getResources().getString(R.string.action_camera_photo_data);
        for (String string : s2.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 2);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s3 = getResources().getString(R.string.action_connectivity_data);
        for (String string : s3.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 3);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s4 = getResources().getString(R.string.action_date_time_data);
        for (String string : s4.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 4);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s5 = getResources().getString(R.string.action_device_action_data);
        for (String string : s5.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 5);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s6 = getResources().getString(R.string.action_device_setting_data);
        for (String string : s6.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 6);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s7 = getResources().getString(R.string.action_location_data);
        for (String string : s7.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 7);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s8 = getResources().getString(R.string.action_media_data);
        for (String string : s8.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 8);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s9 = getResources().getString(R.string.action_messaging_data);
        for (String string : s9.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 9);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s10 = getResources().getString(R.string.action_phone_data);
        for (String string : s10.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 10);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s11 = getResources().getString(R.string.action_screen_data);
        for (String string : s11.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 11);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s12 = getResources().getString(R.string.action_volume_data);
        for (String string : s12.split(",")) {
            SubActionsLabelsModel model = new SubActionsLabelsModel();
            model.setcId((long) 12);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void SaveTriggersLabels() {
        Dao<TriggerLabelsModel, Integer> labelsModels = App.getDataBaseHelper().getDao(TriggerLabelsModel.class);
        String triggerLabels = getResources().getString(R.string.trigger_labels);
        for (String string : triggerLabels.split(",")) {
            TriggerLabelsModel user = new TriggerLabelsModel();
            user.setName(string);
            try {
                labelsModels.createOrUpdate(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Dao<SubTriggerLabelsModel, Integer> subcategoryDao = App.getDataBaseHelper().getDao(SubTriggerLabelsModel.class);
        /*Save Inner Triggers*/
        String s1 = getResources().getString(R.string.tr_battery_labels);
        for (String string : s1.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 1);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s2 = getResources().getString(R.string.tr_call_sms_labels);
        for (String string : s2.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 2);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s3 = getResources().getString(R.string.tr_connectivity_labels);
        for (String string : s3.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 3);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s4 = getResources().getString(R.string.tr_date_time_labels);
        for (String string : s4.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 4);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s5 = getResources().getString(R.string.tr_device_event_labels);
        for (String string : s5.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 5);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String s6 = getResources().getString(R.string.tr_location_labels);
        for (String string : s6.split(",")) {
            SubTriggerLabelsModel model = new SubTriggerLabelsModel();
            model.setcId((long) 6);
            model.setName(string);
            try {
                subcategoryDao.createOrUpdate(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}