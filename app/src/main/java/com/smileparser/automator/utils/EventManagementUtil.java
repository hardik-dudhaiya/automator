package com.smileparser.automator.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.App;
import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.activity.CreateActivity;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.db_helper.EventValueModel;
import com.smileparser.automator.db_helper.SubCategoryModel;
import com.smileparser.automator.db_helper.TriggerModel;
import com.smileparser.automator.triggeractionmanager.Action;
import com.smileparser.automator.triggeractionmanager.ActionDetails;
import com.smileparser.automator.triggeractionmanager.Constraint;
import com.smileparser.automator.triggeractionmanager.ConstraintDetails;
import com.smileparser.automator.triggeractionmanager.EventValue;
import com.smileparser.automator.triggeractionmanager.Macro;

import java.sql.SQLException;

public class EventManagementUtil {
    public static void addTriggerEvent(int triggerId, EventValueModel eventValue) {
        Dao<SubCategoryModel, Integer> categoryDao = App.getDataBaseHelper().getDao(SubCategoryModel.class);
        try {
            SubCategoryModel categoryModel = categoryDao.queryForId(triggerId);
            TriggerModel model = new TriggerModel();
            model.setTriggerId(categoryModel.getId());
            model.settValue(eventValue);
            ActivityCreateMacroPage.getInstance().macro.setTriggerModel(model);
            Log.e("Android->", "addTriggerEvent: "+new Gson().toJson(ActivityCreateMacroPage.getInstance().macro));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addActionEvent(Context context, int actionId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Macro macro = ((CreateActivity) context).macro;
        Action action = databaseHelper.getActionDao().getActionById(actionId);
        final ActionDetails actionDetails = new ActionDetails();
        actionDetails.setActionId(action.getId());
        actionDetails.setEventValue(eventValue);
        macro.setActionDetails(actionDetails);
    }

    public static void addConstraintEvent(Context context, int constraintId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Macro macro = ((CreateActivity) context).macro;

        Constraint constraint = databaseHelper.getConstraintDao().getConstraintById(constraintId);
        final ConstraintDetails constraintDetails = new ConstraintDetails();
        constraintDetails.setConstraintId(constraint.getId());
        constraintDetails.setEventValue(eventValue);
        macro.setConstraintDetails(constraintDetails);
    }
}
