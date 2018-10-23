package com.smileparser.automator.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class PrimaryTextView extends android.support.v7.widget.AppCompatTextView {


    public PrimaryTextView(Context context) {
        super(context);
        setCustomFont(context);
    }

    public PrimaryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public PrimaryTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);
    }

    public void setCustomFont(Context context) {
        setTypeface( Typeface.createFromAsset(context.getAssets(), "fonts/BebasNeue.ttf"));
    }
}
