package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

import com.smileparser.automator.activity.CreateActivity;
import com.smileparser.automator.database.DatabaseHelper;

/**
 * Created by mayur on 7/10/18.
 */

public abstract class DialogManager {

    Context context;

    public DialogManager(Context context) {
        this.context = context;
    }

    public abstract void show();

    protected void addTriggerEvent(int triggerId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Macro macro = ((CreateActivity) context).macro;
        Trigger trigger = databaseHelper.getTriggerDao().getTriggerById(triggerId);
        TriggerDetails triggerDetails = new TriggerDetails();
        triggerDetails.setTriggerId(trigger.getId());
        triggerDetails.setEventValue(eventValue);
        macro.setTriggerDetails(triggerDetails);

//        Action action = databaseHelper.getActionDao().getActionById(actionId);
//        final ActionDetails actionDetails = new ActionDetails();
//        actionDetails.setActionId(action.getId());
//        macro.setActionDetails(actionDetails);

//        long macroId = 0;
//        if (macro.getId() == 0) {
//            macroId = databaseHelper.getMacroDao().insert(macro);
//        } else {
//            macroId = macro.getId();
//            databaseHelper.getMacroDao().update(macro);
//        }
//        macro.setId(macroId);
//        triggerDetails.setMacroId(macroId);
//        actionDetails.setMacroId(macroId);
//        databaseHelper.getTriggerDetailsDao().insert(triggerDetails);
//        databaseHelper.getActionDetailsDao().insert(actionDetails);
    }

    protected void addActionEvent(int actionId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Macro macro = ((CreateActivity) context).macro;
//        Trigger trigger = databaseHelper.getTriggerDao().getTriggerById(triggerId);
//        TriggerDetails triggerDetails = new TriggerDetails();
//        triggerDetails.setTriggerId(trigger.getId());
//        triggerDetails.setValue(value);
//        macro.setTriggerDetails(triggerDetails);

        Action action = databaseHelper.getActionDao().getActionById(actionId);
        final ActionDetails actionDetails = new ActionDetails();
        actionDetails.setActionId(action.getId());
        actionDetails.setEventValue(eventValue);
        macro.setActionDetails(actionDetails);

//        long macroId = 0;
//        if (macro.getId() == 0) {
//            macroId = databaseHelper.getMacroDao().insert(macro);
//        } else {
//            macroId = macro.getId();
//            databaseHelper.getMacroDao().update(macro);
//        }
//        macro.setId(macroId);
//        triggerDetails.setMacroId(macroId);
//        actionDetails.setMacroId(macroId);
//        databaseHelper.getTriggerDetailsDao().insert(triggerDetails);
//        databaseHelper.getActionDetailsDao().insert(actionDetails);

//        new MacroManager(context).registerMacro(macro);
    }

    void addConstraintEvent(int constraintId, EventValue eventValue) {
        DatabaseHelper databaseHelper = DatabaseHelper.getAppDatabase(context);
        Macro macro = ((CreateActivity) context).macro;

        Constraint constraint = databaseHelper.getConstraintDao().getConstraintById(constraintId);
        final ConstraintDetails constraintDetails = new ConstraintDetails();
        constraintDetails.setConstraintId(constraint.getId());
        constraintDetails.setEventValue(eventValue);
        macro.setConstraintDetails(constraintDetails);

//        long macroId = 0;
//        if (macro.getId() == 0) {
//            macroId = databaseHelper.getMacroDao().insert(macro);
//        } else {
//            macroId = macro.getId();
//            databaseHelper.getMacroDao().update(macro);
//        }
//        macro.setId(macroId);
//        constraintDetails.setMacroId(macroId);
//        databaseHelper.getConstraintDetailsDao().insert(constraintDetails);

//        new MacroManager(context).registerMacro(macro);
    }
}
