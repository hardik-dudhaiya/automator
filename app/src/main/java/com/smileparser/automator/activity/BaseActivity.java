package com.smileparser.automator.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smileparser.automator.utils.PermissionUtil;

public abstract class BaseActivity extends AppCompatActivity {
    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionUtil = new PermissionUtil(this);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}