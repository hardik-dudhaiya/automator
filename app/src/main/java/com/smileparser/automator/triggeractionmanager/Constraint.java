package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mayur on 7/10/18.
 */

@Entity
public class Constraint {

    @PrimaryKey(autoGenerate = true)
    long id;

    long categoryId;

    String description;

    public Constraint(long categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}