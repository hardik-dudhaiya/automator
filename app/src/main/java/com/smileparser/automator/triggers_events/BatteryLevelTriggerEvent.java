package com.smileparser.automator.triggers_events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.smileparser.automator.contracts.TriggerableContract;
import com.smileparser.automator.db_helper.TriggerModel;

/**
 * Created by mayur on 30/9/18.
 */

public class BatteryLevelTriggerEvent implements TriggerableContract {

    private static final int BATTERY_LEVEL_INCREASING = 1;
    private static final int BATTERY_LEVEL_DECREASING = 2;
    private static final int BATTERY_LEVEL_ANY = 3;
    private final Context context;
    private final TriggerModel triggerDetails;
    private onTriggerFiredListener onTriggerListener;

    BatteryLevelTriggerEvent(Context context, TriggerModel triggerDetails) {
        this.context = context;
        this.triggerDetails = triggerDetails;
    }

    private static boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int charging = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return charging == BatteryManager.BATTERY_STATUS_CHARGING || charging == BatteryManager.BATTERY_STATUS_FULL;
    }

    @Override
    public void registerEvent(onTriggerFiredListener onTriggerListener) {
        this.onTriggerListener = onTriggerListener;
        registerBattery();
    }

    private void registerBattery() {
        context.registerReceiver(new PowerConnectionReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public class PowerConnectionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryPercentage = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            switch (triggerDetails.gettValue().getOption()) {
                case BATTERY_LEVEL_INCREASING:
                    if (batteryPercentage == Integer.parseInt(triggerDetails.gettValue().getValue())) {
                        if (isCharging(context))
                            onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;

                case BATTERY_LEVEL_DECREASING:
                    if (batteryPercentage == Integer.parseInt(triggerDetails.gettValue().getValue())) {
                        //TODO call callback of onTriggerListener because charging increased/decreased to specified level
                        if (!isCharging(context))
                            onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;
                case BATTERY_LEVEL_ANY:
                    //TODO call callback of onTriggerListener any change condition
                    onTriggerListener.onTriggered(triggerDetails);
                    break;
            }
        }
    }
}