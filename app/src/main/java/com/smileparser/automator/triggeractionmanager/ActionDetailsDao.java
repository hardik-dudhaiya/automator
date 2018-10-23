package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface ActionDetailsDao {


    @Insert
    long insert(ActionDetails actionDetails);

    @Query("select * from ActionDetails where macroId = :macroId")
    List<ActionDetails> getActionDetailsListByMacro(long macroId);
}
