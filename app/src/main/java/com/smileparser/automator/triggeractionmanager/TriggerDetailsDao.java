package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface TriggerDetailsDao {


    @Insert
    long insert(TriggerDetails triggerDetails);

    @Query("select * from TriggerDetails where macroId = :macroId")
    List<TriggerDetails> getTriggerDetailsByMacroId(long macroId);
}
