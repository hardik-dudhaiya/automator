package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mayur on 2/10/18.
 */

@Entity
public class Macro {

    @PrimaryKey(autoGenerate = true)
    long id;


    boolean isActive;

    @Ignore
    TriggerDetails triggerDetails;
    @Ignore
    ActionDetails actionDetails;
    @Ignore
    ConstraintDetails constraintDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public TriggerDetails getTriggerDetails() {
        return triggerDetails;
    }

    public void setTriggerDetails(TriggerDetails triggerDetails) {
        this.triggerDetails = triggerDetails;
    }

    public ActionDetails getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(ActionDetails actionDetails) {
        this.actionDetails = actionDetails;
    }

    public ConstraintDetails getConstraintDetails() {
        return constraintDetails;
    }

    public void setConstraintDetails(ConstraintDetails constraintDetails) {
        this.constraintDetails = constraintDetails;
    }
}
