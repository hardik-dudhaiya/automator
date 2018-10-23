package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

/**
 * Created by mayur on 3/10/18.
 */

public class TriggerFactory {

    public Triggerable getTrigger(Context context, TriggerDetails triggerDetails) {
        if (triggerDetails.getTriggerId() == 1) {
            return new BatteryLevelTrigger(context, triggerDetails);
        } else if (triggerDetails.getTriggerId() == 2) {
            return new BatterySaverTrigger(context, triggerDetails);
        } else if (triggerDetails.getTriggerId() == 3) {
            return new PowerButtonTrigger(context, triggerDetails);
        } else if (triggerDetails.getTriggerId() == 4) {
            return new PowerConnectionTrigger(context, triggerDetails);
        }

        return null;
    }
}
