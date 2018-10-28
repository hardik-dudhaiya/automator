package com.smileparser.automator.triggers_events;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.smileparser.automator.contracts.TriggerableContract;
import com.smileparser.automator.db_helper.ActionModel;
import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.db_helper.MacroModel;
import com.smileparser.automator.db_helper.MyDatabaseHelper;
import com.smileparser.automator.db_helper.TriggerModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mayur on 3/10/18.
 */

public class MacroManagerNew {

    private Context context;

    public MacroManagerNew(Context context) {
        this.context = context;
    }

    public void registerMacro(MacroModel macro) {
        try {
            Dao<TriggerModel, Integer> modelDao = new MyDatabaseHelper(context).getDao(TriggerModel.class);
            TriggerModel triggerModels = modelDao.queryForId(Integer.valueOf(String.valueOf(macro.getTriggerModel().getTriggerId())));
            registerTrigger(triggerModels);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerTrigger(TriggerModel triggerDetails) {
        TriggerableContract contract = new FactoryTriggerEvent().getTrigger(context, triggerDetails);
        contract.registerEvent(triggerDetails1 -> {
            long macroId = triggerDetails.getMacroId();
            Dao<ConstraintModel, Integer> constraintModelsDao = new MyDatabaseHelper(context).getDao(ConstraintModel.class);
            try {
                List<ConstraintModel> constraintModel = constraintModelsDao.queryForEq("cMacroId", (int) macroId);

                boolean allConstraintsValid = true;
                for (ConstraintModel constraintDetails : constraintModel) {
                    allConstraintsValid &= new FactoryConstraintEvent().getConstraints(context, constraintDetails).apply();
                }
                if (allConstraintsValid) {
                    Dao<ActionModel, Integer> actionModels = new MyDatabaseHelper(context).getDao(ActionModel.class);
                    if (actionModels != null) {
                        List<ActionModel> model = actionModels.queryBuilder().where().eq("aMacroId", (int) macroId).query();
                        //List<ActionModel> model = actionModels.queryForEq("aMacroId", (int) macroId);
                        if (model != null) {
                            for (ActionModel actionDetails : model) {
                                new FactoryActionEvent().getAction(context, actionDetails).execute();
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}