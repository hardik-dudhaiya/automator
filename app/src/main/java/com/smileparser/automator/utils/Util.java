package com.smileparser.automator.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mayur on 7/10/18.
 */

public class Util {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
