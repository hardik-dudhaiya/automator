package com.smileparser.automator.utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.smileparser.automator.App;

/**
 * Created by mayur on 7/10/18.
 */

public class Util {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static Resources getResource() {
        return App.getInstance().getResources();
    }

}
