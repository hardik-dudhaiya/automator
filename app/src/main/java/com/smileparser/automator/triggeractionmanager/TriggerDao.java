package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface TriggerDao {

    @Query("select * from trigger where id = :id")
    Trigger getTriggerById(int id);

    @Query("select * from trigger")
    List<Trigger> getAll();

    @Insert
    long insert(Trigger trigger);
}
