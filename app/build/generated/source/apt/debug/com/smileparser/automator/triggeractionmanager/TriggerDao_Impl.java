package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TriggerDao_Impl implements TriggerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTrigger;

  public TriggerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrigger = new EntityInsertionAdapter<Trigger>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Trigger`(`id`,`categoryId`,`description`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trigger value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getCategoryId());
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
      }
    };
  }

  @Override
  public long insert(Trigger trigger) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTrigger.insertAndReturnId(trigger);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Trigger getTriggerById(int id) {
    final String _sql = "select * from trigger where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCategoryId = _cursor.getColumnIndexOrThrow("categoryId");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final Trigger _result;
      if(_cursor.moveToFirst()) {
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result = new Trigger(_tmpCategoryId,_tmpDescription);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Trigger> getAll() {
    final String _sql = "select * from trigger";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCategoryId = _cursor.getColumnIndexOrThrow("categoryId");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final List<Trigger> _result = new ArrayList<Trigger>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Trigger _item;
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item = new Trigger(_tmpCategoryId,_tmpDescription);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
