package com.smileparser.automator.triggeractionmanager;

import android.content.Context;

/**
 * Created by mayur on 3/10/18.
 */

public class ActionFactory {

    public Actionable getAction(Context context, ActionDetails actionDetails) {
        if (actionDetails.getActionId() == 1) {
            return new LaunchAppAction(context, actionDetails);
        }
        return null;
    }

}
