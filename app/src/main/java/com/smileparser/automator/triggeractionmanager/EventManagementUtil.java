package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.activity.CreateActivity;
import com.smileparser.automator.database.DatabaseHelper;

public class EventManagementUtil {

    public static void addTriggerEvent(Context context, int triggerId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Trigger trigger = databaseHelper.getTriggerDao().getTriggerById(triggerId);
        TriggerDetails triggerDetails = new TriggerDetails();
        triggerDetails.setTriggerId(trigger.getId());
        triggerDetails.setEventValue(eventValue);
        ActivityCreateMacroPage.getInstance().macro.setTriggerDetails(triggerDetails);
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
