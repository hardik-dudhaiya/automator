package com.smileparser.automator.triggeractionmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/**
 * Created by mayur on 7/10/18.
 */

public class BatterySaverTrigger implements Triggerable {

    public static final int BATTERY_SAVER_ENABLED = 1;
    public static final int BATTERY_SAVER_DISABLED = 2;
    Context context;
    TriggerDetails triggerDetails;
    private OnTriggerListener onTriggerListener;

    public BatterySaverTrigger(Context context, TriggerDetails triggerDetails) {
        this.context = context;
        this.triggerDetails = triggerDetails;
    }

    @Override
    public void registerEvent(OnTriggerListener onTriggerListener) {
        this.onTriggerListener = onTriggerListener;
        registerBatterySaver();
    }

    private void registerBatterySaver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED);
        context.registerReceiver(powerSaverChangeReceiver, filter);
    }

    BroadcastReceiver powerSaverChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            switch (triggerDetails.getEventValue().getOption()) {
                case BATTERY_SAVER_ENABLED:
                    if (pm.isPowerSaveMode()) {
                        onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;

                case BATTERY_SAVER_DISABLED:
                    if (!pm.isPowerSaveMode()) {
                        onTriggerListener.onTriggered(triggerDetails);
                    }
                    break;
            }
        }
    };
}
