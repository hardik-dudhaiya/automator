package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface ConstraintDao {

    @Query("select * from action where id = :id")
    Constraint getConstraintById(int id);

    @Insert
    long insert(Constraint constraint);
}
