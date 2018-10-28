package com.smileparser.automator.triggers_events;

import android.content.Context;
import android.os.PowerManager;

import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.triggeractionmanager.Constraintable;

/**
 * Created by mayur on 8/10/18.
 */

public class ConstraintsBatterySaver implements Constraintable {

    private static final int BATTERY_SAVER_ENABLED = 1;
    private static final int BATTERY_SAVER_DISABLED = 2;
    private final ConstraintModel constraintDetails;
    private Context context;

    public ConstraintsBatterySaver(Context context, ConstraintModel constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    @Override
    public boolean apply() {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        switch (constraintDetails.gettValue().getOption()) {
            case BATTERY_SAVER_ENABLED:
                if (pm != null) {
                    return pm.isPowerSaveMode();
                }

            case BATTERY_SAVER_DISABLED:
                if (pm != null) {
                    return !pm.isPowerSaveMode();
                }
        }
        return false;
    }
}