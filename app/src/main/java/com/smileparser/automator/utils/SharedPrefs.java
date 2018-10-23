package com.smileparser.automator.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.smileparser.automator.App;

/**
 * Created by mayur on 10/10/18.
 */

public class SharedPrefs {

    private static String SHARED_PREFERENCE = "automatorPrefs";

    private SharedPrefs() {

    }

    public static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCE, 0);
    }

    public static void SaveStringData(String Key, String Value) {
        getPreference(App.getInstance()).edit().putString(Key, Value).apply();
    }

    public static void SaveBooleanData(String Key, boolean Value) {
        getPreference(App.getInstance()).edit().putBoolean(Key, Value).apply();
    }

    public static void SaveIntData(String Key, int Value) {
        getPreference(App.getInstance()).edit().putInt(Key, Value).apply();
    }

    public static String getStringPref(String key) {
        return getPreference(App.getInstance()).getString(key, "");
    }

    public static boolean getBoolPref(String key) {
        return getPreference(App.getInstance()).getBoolean(key, false);
    }
}
