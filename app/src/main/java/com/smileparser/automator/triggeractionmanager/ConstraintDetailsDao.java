package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface ConstraintDetailsDao {

    @Insert
    long insert(ConstraintDetails constraintDetails);

    @Query("select * from ConstraintDetails where macroId = :macroId")
    List<ConstraintDetails> getConsraintDetailsListByMacro(long macroId);
}
