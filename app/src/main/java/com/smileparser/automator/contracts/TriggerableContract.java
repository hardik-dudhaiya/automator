package com.smileparser.automator.contracts;

import com.smileparser.automator.db_helper.TriggerModel;

public interface TriggerableContract {

    void registerEvent(onTriggerFiredListener onTriggerListener);

    interface onTriggerFiredListener {

        void onTriggered(TriggerModel triggerDetails);
    }
}