package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

/**
 * Created by mayur on 3/10/18.
 */

public class ConstraintFactory {

    public Constraintable getConstraint(Context context, ConstraintDetails constraintDetails) {
        if (constraintDetails.getConstraintId() == 1) {
            return new BatteryLevelConstraint(context, constraintDetails);
        } else if (constraintDetails.getConstraintId() == 2) {
            return new BatterySaverConstraint(context, constraintDetails);
        } else if (constraintDetails.getConstraintId() == 3) {
            return new PowerConnectionConstraint(context, constraintDetails);
        }
        return null;
    }

}
