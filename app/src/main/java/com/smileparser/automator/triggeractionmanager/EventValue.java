package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Entity;

/**
 * Created by mayur on 7/10/18.
 */

@Entity
public class EventValue {
    int option;
    String value;

    public EventValue(int option, String value) {
        this.option = option;
        this.value = value;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
