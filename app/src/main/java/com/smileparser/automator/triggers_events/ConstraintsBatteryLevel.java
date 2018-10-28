package com.smileparser.automator.triggers_events;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.triggeractionmanager.Constraintable;

/**
 * Created by mayur on 7/10/18.
 */

public class ConstraintsBatteryLevel implements Constraintable {

    public static final int BATTERY_LESS_THAN = 1;
    public static final int BATTERY_GREATER_THAN = 2;
    public static final int BATTERY_EQUAL_TO = 3;
    private final ConstraintModel constraintDetails;
    private Context context;

    public ConstraintsBatteryLevel(Context context, ConstraintModel constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    private static int getBatteryPercentage(Context context) {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float batteryPct = level / (float) scale;

        return (int) (batteryPct * 100);
    }

    @Override
    public boolean apply() {
        int option = constraintDetails.gettValue().getOption();
        switch (option) {
            case 1:
                int registeredLevel = Integer.parseInt(constraintDetails.gettValue().getValue());
                if (getBatteryPercentage(context) < registeredLevel) {
                    return true;
                }

            case 2:
                registeredLevel = Integer.parseInt(constraintDetails.gettValue().getValue());
                if (getBatteryPercentage(context) > registeredLevel) {
                    return true;
                }

            case 3:
                registeredLevel = Integer.parseInt(constraintDetails.gettValue().getValue());
                if (getBatteryPercentage(context) == registeredLevel) {
                    return true;
                }
        }
        return false;
    }
}
