package com.smileparser.automator.triggeractionmanager;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by mayur on 6/10/18.
 */

public class AppInfo {

    String appName;
    PackageInfo packageInfo;

    public AppInfo(String appName, PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    @Override
    public String toString() {
        return appName;
    }
}
