package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by mayur on 7/10/18.
 */

public class BatteryLevelConstraint implements Constraintable {

    public static final int BATTERY_LESS_THAN = 1;
    public static final int BATTERY_GREATER_THAN = 2;
    public static final int BATTERY_EQUAL_TO = 3;
    private final ConstraintDetails constraintDetails;
    Context context;

    public BatteryLevelConstraint(Context context, ConstraintDetails constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    @Override
    public boolean apply() {
        int option = constraintDetails.getEventValue().getOption();
        switch (option) {
            case 1:
                int registeredLevel = Integer.parseInt(constraintDetails.getEventValue().getValue());
                if (getBatteryPercentage(context) < registeredLevel) {
                    return true;
                }

            case 2:
                registeredLevel = Integer.parseInt(constraintDetails.getEventValue().getValue());
                if (getBatteryPercentage(context) > registeredLevel) {
                    return true;
                }

            case 3:
                registeredLevel = Integer.parseInt(constraintDetails.getEventValue().getValue());
                if (getBatteryPercentage(context) == registeredLevel) {
                    return true;
                }
        }
        return false;
    }

    public static int getBatteryPercentage(Context context) {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float batteryPct = level / (float) scale;

        return (int) (batteryPct * 100);
    }
}
