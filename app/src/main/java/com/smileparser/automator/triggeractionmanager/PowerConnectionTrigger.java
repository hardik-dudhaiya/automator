package com.smileparser.automator.triggeractionmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by mayur on 8/10/18.
 */

public class PowerConnectionTrigger implements Triggerable {

    public static final int POWER_CONNECTED = 1;
    public static final int POWER_DISCONNECTED = 2;
    private final Context context;
    private final TriggerDetails triggerDetails;
    private OnTriggerListener onTriggerListener;

    public PowerConnectionTrigger(Context context, TriggerDetails triggerDetails) {
        this.context = context;
        this.triggerDetails = triggerDetails;
    }

    @Override
    public void registerEvent(OnTriggerListener onTriggerListener) {
        this.onTriggerListener = onTriggerListener;
        registerPowerConnection();
    }

    private void registerPowerConnection() {
        context.registerReceiver(new PowerConnectionReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    class PowerConnectionReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

            switch (triggerDetails.getEventValue().getOption()) {
                case POWER_CONNECTED:
                    if (plugged == BatteryManager.BATTERY_PLUGGED_AC ||
                            plugged == BatteryManager.BATTERY_PLUGGED_USB ||
                            plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                        onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;

                case POWER_DISCONNECTED:
                    if (plugged != BatteryManager.BATTERY_PLUGGED_AC &&
                            plugged != BatteryManager.BATTERY_PLUGGED_USB &&
                            plugged != BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                        onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;
            }
        }
    }
}
