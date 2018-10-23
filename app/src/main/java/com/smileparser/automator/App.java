package com.smileparser.automator;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
