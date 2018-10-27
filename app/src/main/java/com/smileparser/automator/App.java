package com.smileparser.automator;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.smileparser.automator.db_helper.MyDatabaseHelper;

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @SuppressLint("StaticFieldLeak")
    private static App mInstance;

    public static synchronized MyDatabaseHelper getDataBaseHelper() {
        return new MyDatabaseHelper(mContext);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        Stetho.initializeWithDefaults(this);
    }
}