package com.smileparser.automator.triggeractionmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by mayur on 30/9/18.
 */

public class AirplaneModeAction implements Triggerable {

    public static final int AIRPLANE_MODE_ON = 1;
    public static final int AIRPLANE_MODE_OFF = 2;
    public static final int AIRPLANE_MODE_TOGGLE = 3;
    private final Context context;

    private final TriggerDetails triggerDetails;
    private OnTriggerListener onTriggerListener;

    public AirplaneModeAction(Context context, TriggerDetails triggerDetails) {
        this.context = context;
        this.triggerDetails = triggerDetails;
    }

    public static boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int charging = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return (charging == BatteryManager.BATTERY_STATUS_CHARGING || charging == BatteryManager.BATTERY_STATUS_FULL) ? true : false;
    }

    @Override
    public void registerEvent(OnTriggerListener onTriggerListener) {
        this.onTriggerListener = onTriggerListener;
        registerBattery();
    }

    void registerBattery() {
        context.registerReceiver(new PowerConnectionReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public class PowerConnectionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryPercentage = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            /*switch (triggerDetails.getEventValue().option) {
                case BATTERY_LEVEL_INCREASING:
                    if (batteryPercentage == Integer.parseInt(triggerDetails.getEventValue().value)) {
                        //TODO
                        //call callback of onTriggerListener because charging increased/decreased to specified level
                        if (isCharging(context))
                            onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;

                case AIRPLANE_MODE_OFF:
                    if (batteryPercentage == Integer.parseInt(triggerDetails.getEventValue().value)) {
                        //TODO
                        //call callback of onTriggerListener because charging increased/decreased to specified level
                        if (!isCharging(context))
                            onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;

                case AIRPLANE_MODE_TOGGLE:
                    //TODO
                    //call callback of onTriggerListener any change condition
                    onTriggerListener.onTriggered(triggerDetails);
                    break;
            }*/
        }
    }
}
