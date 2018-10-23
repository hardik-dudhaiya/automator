package com.smileparser.automator.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
}
