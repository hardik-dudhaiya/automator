package com.smileparser.automator.triggers_events;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.smileparser.automator.db_helper.ConstraintModel;
import com.smileparser.automator.triggeractionmanager.ConstraintDetails;
import com.smileparser.automator.triggeractionmanager.Constraintable;

/**
 * Created by mayur on 8/10/18.
 */

public class ConstraintsPowerConnection implements Constraintable {

    private static final int POWER_CONNECTED = 1;
    private static final int POWER_DISCONNECTED = 2;
    private final ConstraintModel constraintDetails;
    private Context context;

    public ConstraintsPowerConnection(Context context, ConstraintModel constraintDetails) {
        this.context = context;
        this.constraintDetails = constraintDetails;
    }

    @Override
    public boolean apply() {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

        switch (constraintDetails.gettValue().getOption()) {
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
