package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public class ConstraintDao_Impl implements ConstraintDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfConstraint;

  public ConstraintDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConstraint = new EntityInsertionAdapter<Constraint>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Constraint`(`id`,`categoryId`,`description`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Constraint value) {
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
  public long insert(Constraint constraint) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfConstraint.insertAndReturnId(constraint);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Constraint getConstraintById(int id) {
    final String _sql = "select * from action where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCategoryId = _cursor.getColumnIndexOrThrow("categoryId");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final Constraint _result;
      if(_cursor.moveToFirst()) {
        final long _tmpCategoryId;
        _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result = new Constraint(_tmpCategoryId,_tmpDescription);
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
}
