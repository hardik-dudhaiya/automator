package com.smileparser.automator.triggers_events;

import android.content.Context;

import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.triggeractionmanager.Constraintable;

public class FactoryConstraintEvent {

    public Constraintable getConstraints(Context context, ConstraintModel constraintDetails) {
        if (constraintDetails.getId() == 1) {
            return new ConstraintsBatteryLevel(context, constraintDetails);
        } else if (constraintDetails.getId() == 2) {
            return new ConstraintsBatterySaver(context, constraintDetails);
        } else if (constraintDetails.getId() == 3) {
            return new ConstraintsPowerConnection(context, constraintDetails);
        }
        return null;
    }

}