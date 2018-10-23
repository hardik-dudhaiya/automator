package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

import com.smileparser.automator.Activity.MainActivity;
import com.smileparser.automator.database.DatabaseHelper;

import java.util.List;

/**
 * Created by mayur on 3/10/18.
 */

public class MacroManager {

    Context context;

    public MacroManager(Context context) {
        this.context = context;
    }

    public void registerMacro(Macro macro) {
        List<TriggerDetails> triggerDetailsList = DatabaseHelper.getAppDatabase(context).getTriggerDetailsDao().getTriggerDetailsByMacroId(macro.getId());
        for (TriggerDetails triggerDetails : triggerDetailsList) {
            registerTrigger(triggerDetails);
        }
    }

    void registerTrigger(TriggerDetails triggerDetails) {
        Triggerable trigger = new TriggerFactory().getTrigger(context, triggerDetails);
        trigger.registerEvent(new OnTriggerListener() {
            @Override
            public void onTriggered(TriggerDetails triggerDetails) {
                //TODO
                //get macroId from triggerId then get actions from macroId and execute
                long macroId = triggerDetails.getMacroId();

                List<ConstraintDetails> constraintDetailsList = DatabaseHelper.getAppDatabase(context).getConstraintDetailsDao().getConsraintDetailsListByMacro(macroId);

                boolean allConstraintsValid = true;
//                if (constraintDetailsList.size() == 0) {
//                    allConstraintsValid = true;
//                } else {
                for (ConstraintDetails constraintDetails : constraintDetailsList) {
                    Constraintable constraintable = new ConstraintFactory().getConstraint(context, constraintDetails);
                    allConstraintsValid &= constraintable.apply();
                }
//                }

                if (allConstraintsValid) {
                    List<ActionDetails> actionDetailsList = DatabaseHelper.getAppDatabase(context).
                            getActionDetailsDao().getActionDetailsListByMacro(macroId);
                    for (ActionDetails actionDetails : actionDetailsList) {
                        //TODO
                        //pass action id to factory and get object of action then call execute
                        //Below for testing
                        new ActionFactory().getAction(context, actionDetails).execute();
                    }
                }

            }
        });
    }
}
