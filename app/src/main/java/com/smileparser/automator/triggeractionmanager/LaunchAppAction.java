package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by mayur on 2/10/18.
 */

public class LaunchAppAction implements Actionable {

    private final Context context;
    private final ActionDetails actionDetails;

    public LaunchAppAction(Context context, ActionDetails actionDetails) {
        this.context = context;
        this.actionDetails = actionDetails;
    }

    @Override
    public void execute() {
        try {
//            Intent i = context.getPackageManager().getLaunchIntentForPackage("com.twidroid.SendTweet");
//            Intent i = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
            Intent i = context.getPackageManager().getLaunchIntentForPackage(actionDetails.getEventValue().getValue());
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
