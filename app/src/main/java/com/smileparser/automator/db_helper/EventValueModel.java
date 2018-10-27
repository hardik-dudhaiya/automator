package com.smileparser.automator.db_helper;

public class EventValueModel {
    private int option;
    private String value;

    public EventValueModel(int option, String value) {
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