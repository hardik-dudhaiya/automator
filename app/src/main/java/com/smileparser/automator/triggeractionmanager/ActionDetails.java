package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mayur on 2/10/18.
 */

@Entity
public class ActionDetails {

    @PrimaryKey(autoGenerate = true)
    long id;

    long actionId;

    long macroId;

    @Embedded
    EventValue eventValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public long getMacroId() {
        return macroId;
    }

    public void setMacroId(long macroId) {
        this.macroId = macroId;
    }

    public EventValue getEventValue() {
        return eventValue;
    }

    public void setEventValue(EventValue eventValue) {
        this.eventValue = eventValue;
    }
}
