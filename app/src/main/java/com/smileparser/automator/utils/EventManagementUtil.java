package com.smileparser.automator.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.App;
import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.db_helper.ActionModel;
import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.db_helper.EventValueModel;
import com.smileparser.automator.db_helper.SubActionsLabelsModel;
import com.smileparser.automator.db_helper.SubConstraintLabelsModel;
import com.smileparser.automator.db_helper.SubTriggerLabelsModel;
import com.smileparser.automator.db_helper.TriggerModel;

import java.sql.SQLException;

public class EventManagementUtil {

    public static void addTriggerEvent(int triggerId, EventValueModel eventValue) {
        Dao<SubTriggerLabelsModel, Integer> categoryDao = App.getDataBaseHelper().getDao(SubTriggerLabelsModel.class);
        try {
            SubTriggerLabelsModel categoryModel = categoryDao.queryForId(triggerId);
            TriggerModel model = new TriggerModel();
            model.setTriggerId(categoryModel.getId());
            model.settValue(eventValue);
            ActivityCreateMacroPage.getInstance().macro.setTriggerModel(model);

            Log.e("Android->", "addTriggerEvent: " + new Gson().toJson(ActivityCreateMacroPage.getInstance().macro));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addActionEvent(int actionId, EventValueModel eventValue) {
        Dao<SubActionsLabelsModel, Integer> categoryDao = App.getDataBaseHelper().getDao(SubActionsLabelsModel.class);
        try {
            SubActionsLabelsModel categoryModel = categoryDao.queryForId(actionId);
            ActionModel model = new ActionModel();
            model.setActionId(categoryModel.getId());
            model.settValue(eventValue);
            ActivityCreateMacroPage.getInstance().macro.setActionModel(model);

            Log.e("Android->", "addActionEvent: " + new Gson().toJson(ActivityCreateMacroPage.getInstance().macro));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addConstraintEvent(int constraintId, EventValueModel eventValue) {
        Dao<SubConstraintLabelsModel, Integer> categoryDao = App.getDataBaseHelper().getDao(SubConstraintLabelsModel.class);
        try {
            SubConstraintLabelsModel categoryModel = categoryDao.queryForId(constraintId);
            ConstraintModel model = new ConstraintModel();
            model.setActionId(categoryModel.getId());
            model.settValue(eventValue);
            ActivityCreateMacroPage.getInstance().macro.setConstraintModel(model);

            Log.e("Android->", "addConstraintEvent: " + new Gson().toJson(ActivityCreateMacroPage.getInstance().macro));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}