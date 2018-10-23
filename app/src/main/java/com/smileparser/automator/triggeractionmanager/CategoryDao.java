package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

/**
 * Created by mayur on 2/10/18.
 */

@Dao
public interface CategoryDao {

    @Insert
    long insert(Category category);
}
