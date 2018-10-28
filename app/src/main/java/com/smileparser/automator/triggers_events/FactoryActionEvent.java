package com.smileparser.automator.triggers_events;

import android.content.Context;
import android.util.Log;

import com.smileparser.automator.action_events.BluetoothToggle;
import com.smileparser.automator.db_helper.ActionModel;
import com.smileparser.automator.triggeractionmanager.Actionable;

public class FactoryActionEvent {

    public Actionable getAction(Context context, ActionModel actionDetails) {
        Log.e("Android->", "getAction: " + actionDetails.getActionId());
        if (actionDetails.getActionId() == 1) {
            //return new LaunchAppAction(context, actionDetails);
        } else if (actionDetails.getActionId() == 3 && actionDetails.gettValue().getOption() == 3) {
            return new BluetoothToggle(context, actionDetails);
        }
        return null;
    }

}