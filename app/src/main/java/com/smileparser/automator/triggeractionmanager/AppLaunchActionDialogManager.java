package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.smileparser.automator.Activity.CreateActivity;
import com.smileparser.automator.database.DatabaseHelper;

import java.util.List;

/**
 * Created by mayur on 7/10/18.
 */

public final class AppLaunchActionDialogManager extends DialogManager {

    Context context;

    public AppLaunchActionDialogManager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void show() {
        showAllApps();
    }

    void showAllApps() {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setTitle("Select an App to be launched");

        final ArrayAdapter<AppInfo> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
        for (PackageInfo packageInfo : packageList) {
            if (!isSystemPackage(packageInfo)) {
                String appName = packageManager.getApplicationLabel(
                        packageInfo.applicationInfo).toString();
                AppInfo appInfo = new AppInfo(appName, packageInfo);
                arrayAdapter.add(appInfo);
            }
        }

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppInfo appInfo = arrayAdapter.getItem(which);
                addActionEvent(1, new EventValue(0, appInfo.getPackageInfo().packageName));
            }
        });
        builderSingle.show();
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }
}
