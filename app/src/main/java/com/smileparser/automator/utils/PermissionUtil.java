package com.smileparser.automator.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.smileparser.automator.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PermissionUtil {

    private static final int PERMISSION_CODE = 1386;
    private static setListener setListener;
    private ArrayList<String> permissionWeDontHave;
    private String[] toAskPermissions;
    private AppCompatActivity mActivity;
    private Fragment mFragment;

    public PermissionUtil(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    PermissionUtil(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + App.getInstance().getPackageName()));
        App.getInstance().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public PermissionUtil withListener(setListener listener) {
        setListener = listener;
        return this;
    }

    public void withPermission(String... permissions) {
        if (canAsk()) {
            if (isGranted(permissions)) {
                setListener.onAllGranted();
            } else {
                permissionWeDontHave = new ArrayList<>();
                permissionWeDontHave.addAll(Arrays.asList(permissions));
                if (needToAsk()) {
                    if (mActivity != null) {
                        ActivityCompat.requestPermissions(mActivity, toAskPermissions, PERMISSION_CODE);
                    } else {
                        mFragment.requestPermissions(toAskPermissions, PERMISSION_CODE);
                    }
                } else {
                    setListener.onAllGranted();
                }
            }
        }
    }

    private boolean canAsk() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean isGranted(final String... permissions) {
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGranted(final String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(App.getInstance(), permission);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean needToAsk() {
        ArrayList<String> neededPermissions = new ArrayList<>(permissionWeDontHave);

        for (int i = 0; i < permissionWeDontHave.size(); i++) {
            String permission = permissionWeDontHave.get(i);
            int checkRes;
            if (mActivity != null) {
                checkRes = ContextCompat.checkSelfPermission(mActivity, permission);
            } else {
                checkRes = ContextCompat.checkSelfPermission(Objects.requireNonNull(mFragment.getContext()), permission);
            }
            if (checkRes == PackageManager.PERMISSION_GRANTED) {
                neededPermissions.remove(permission);
            }
        }
        permissionWeDontHave = neededPermissions;

        toAskPermissions = new String[permissionWeDontHave.size()];

        for (int i = 0; i < permissionWeDontHave.size(); i++) {
            toAskPermissions[i] = permissionWeDontHave.get(i);
        }
        return permissionWeDontHave.size() != 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean getPermissionsStatus(final Activity activity, String permission) {
        return isGranted(permission) || activity.shouldShowRequestPermissionRationale(permission);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (isGranted(permissions)) {
                setListener.onAllGranted();
            } else {
                if (getRationalePermissions(permissions).size() > 0) {
                    setListener.onAskAgain(getRationalePermissions(permissions));
                } else {
                    setListener.onAllDenied();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private ArrayList<String> getRationalePermissions(String... permissions) {
        ArrayList<String> rationalPermission = new ArrayList<>();
        for (String permission : Arrays.asList(permissions)) {
            if (getPermissionsStatus(mActivity, permission)) {
                rationalPermission.add(permission);
            }
        }
        return rationalPermission;
    }

    public interface setListener {
        void onAllGranted();

        void onAllDenied();

        void onAskAgain(ArrayList<String> rationalePermissions);
    }
}
