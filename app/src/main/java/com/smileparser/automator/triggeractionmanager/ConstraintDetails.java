package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mayur on 2/10/18.
 */

@Entity
public class ConstraintDetails {

    @PrimaryKey(autoGenerate = true)
    int id;

    long constraintId;

    long macroId;

    @Embedded
    EventValue eventValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(long constraintId) {
        this.constraintId = constraintId;
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
