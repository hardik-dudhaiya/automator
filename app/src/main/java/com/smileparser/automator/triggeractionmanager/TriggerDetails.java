package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mayur on 2/10/18.
 */

@Entity
public class TriggerDetails {

    @PrimaryKey(autoGenerate = true)
    long id;

    long triggerId;

    long macroId;

    @Embedded
    EventValue eventValue;//required for trigger ex. battery level

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(long triggerId) {
        this.triggerId = triggerId;
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
