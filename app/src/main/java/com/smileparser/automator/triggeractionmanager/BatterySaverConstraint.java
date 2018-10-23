package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by mayur on 8/10/18.
 */

public class BatterySaverConstraint implements Constraintable {

    public static final int BATTERY_SAVER_ENABLED = 1;
    public static final int BATTERY_SAVER_DISABLED = 2;
    private final ConstraintDetails constraintDetails;
    Context context;

    public BatterySaverConstraint(Context context, ConstraintDetails constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    @Override
    public boolean apply() {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        switch (constraintDetails.getEventValue().getOption()) {
            case BATTERY_SAVER_ENABLED:
                return pm.isPowerSaveMode();

            case BATTERY_SAVER_DISABLED:
                return !pm.isPowerSaveMode();
        }
        return false;
    }
}
