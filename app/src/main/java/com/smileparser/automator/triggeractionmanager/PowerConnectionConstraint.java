package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by mayur on 8/10/18.
 */

public class PowerConnectionConstraint implements Constraintable {

    public static final int POWER_CONNECTED = 1;
    public static final int POWER_DISCONNECTED = 2;
    private final ConstraintDetails constraintDetails;
    Context context;

    public PowerConnectionConstraint(Context context, ConstraintDetails constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    @Override
    public boolean apply() {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

        switch (constraintDetails.getEventValue().getOption()) {
            case POWER_CONNECTED:
                if (plugged == BatteryManager.BATTERY_PLUGGED_AC ||
                        plugged == BatteryManager.BATTERY_PLUGGED_USB ||
                        plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                    return true;
                }

            case POWER_DISCONNECTED:
                if (plugged != BatteryManager.BATTERY_PLUGGED_AC &&
                        plugged != BatteryManager.BATTERY_PLUGGED_USB &&
                        plugged != BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                    return true;
                }
        }
        return false;
    }
}
