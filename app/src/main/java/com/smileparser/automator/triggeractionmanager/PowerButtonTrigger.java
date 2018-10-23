package com.smileparser.automator.triggeractionmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;

import com.smileparser.automator.Activity.CreateActivity;

/**
 * Created by mayur on 8/10/18.
 */

public class PowerButtonTrigger implements Triggerable {

    public static final int THREE_TIMES = 3;
    public static final int FOUR_TIMES = 4;
    public static final int FIVE_TIMES = 5;
    private final Context context;
    private final TriggerDetails triggerDetails;
    private OnTriggerListener onTriggerListener;

    public PowerButtonTrigger(Context context, TriggerDetails triggerDetails) {
        this.context = context;
        this.triggerDetails = triggerDetails;
    }

    @Override
    public void registerEvent(OnTriggerListener onTriggerListener) {
        this.onTriggerListener = onTriggerListener;
        registerPowerButtonPress();
    }

    private void registerPowerButtonPress() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        context.registerReceiver(powerButtonPressReceiver, filter);
    }

    BroadcastReceiver powerButtonPressReceiver = new BroadcastReceiver() {

        int countPress = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            countPress++;
            if (countPress == triggerDetails.getEventValue().getOption()) {
                countPress = 0;
                onTriggerListener.onTriggered(triggerDetails);
            }
            Log.d("@@@###", "count: " + countPress);
        }
    };
}
