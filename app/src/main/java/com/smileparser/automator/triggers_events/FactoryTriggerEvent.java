package com.smileparser.automator.triggers_events;

import android.content.Context;

import com.smileparser.automator.contracts.TriggerableContract;
import com.smileparser.automator.db_helper.TriggerModel;

public class FactoryTriggerEvent {

    public TriggerableContract getTrigger(Context context, TriggerModel triggerDetails) {
        if (triggerDetails.getTriggerId() == 1) {
            return new BatteryLevelTriggerEvent(context, triggerDetails);

        } /*else if (triggerDetails.getTriggerId() == 2) {
            return new BatterySaverTrigger(context, triggerDetails);
        } else if (triggerDetails.getTriggerId() == 3) {
            return new PowerButtonTrigger(context, triggerDetails);
        } else if (triggerDetails.getTriggerId() == 4) {
            return new PowerConnectionTrigger(context, triggerDetails);
        }*/
        return null;
    }
}