package com.smileparser.automator.triggeractionmanager;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
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
public class MacroDao_Impl implements MacroDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMacro;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMacro;

  public MacroDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMacro = new EntityInsertionAdapter<Macro>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Macro`(`id`,`isActive`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Macro value) {
        stmt.bindLong(1, value.getId());
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(2, _tmp);
      }
    };
    this.__updateAdapterOfMacro = new EntityDeletionOrUpdateAdapter<Macro>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Macro` SET `id` = ?,`isActive` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Macro value) {
        stmt.bindLong(1, value.getId());
        final int _tmp;
        _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        stmt.bindLong(3, value.getId());
      }
    };
  }

  @Override
  public long insert(Macro macro) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMacro.insertAndReturnId(macro);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(Macro macro) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfMacro.handle(macro);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Macro getMacroById(long id) {
    final String _sql = "select * from macro where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
      final Macro _result;
      if(_cursor.moveToFirst()) {
        _result = new Macro();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final boolean _tmpIsActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsActive);
        _tmpIsActive = _tmp != 0;
        _result.setActive(_tmpIsActive);
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
  public List<Macro> getAllMacros() {
    final String _sql = "select * from Macro";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
      final List<Macro> _result = new ArrayList<Macro>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Macro _item;
        _item = new Macro();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpIsActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsActive);
        _tmpIsActive = _tmp != 0;
        _item.setActive(_tmpIsActive);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
