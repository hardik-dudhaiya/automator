package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface MacroDao {

    @Query("select * from macro where id = :id")
    Macro getMacroById(long id);

    @Insert
    long insert(Macro macro);

    @Update
    int update(Macro macro);

    @Query("select * from Macro")
    List<Macro> getAllMacros();
}
