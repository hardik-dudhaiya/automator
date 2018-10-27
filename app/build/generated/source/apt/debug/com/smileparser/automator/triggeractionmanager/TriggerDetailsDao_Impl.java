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
public class TriggerDetailsDao_Impl implements TriggerDetailsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTriggerDetails;

  public TriggerDetailsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTriggerDetails = new EntityInsertionAdapter<TriggerDetails>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TriggerDetails`(`id`,`triggerId`,`macroId`,`option`,`value`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TriggerDetails value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getTriggerId());
        stmt.bindLong(3, value.getMacroId());
        final EventValue _tmpEventValue = value.getEventValue();
        if(_tmpEventValue != null) {
          stmt.bindLong(4, _tmpEventValue.getOption());
          if (_tmpEventValue.getValue() == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindString(5, _tmpEventValue.getValue());
          }
        } else {
          stmt.bindNull(4);
          stmt.bindNull(5);
        }
      }
    };
  }

  @Override
  public long insert(TriggerDetails triggerDetails) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTriggerDetails.insertAndReturnId(triggerDetails);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TriggerDetails> getTriggerDetailsByMacroId(long macroId) {
    final String _sql = "select * from TriggerDetails where macroId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, macroId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTriggerId = _cursor.getColumnIndexOrThrow("triggerId");
      final int _cursorIndexOfMacroId = _cursor.getColumnIndexOrThrow("macroId");
      final int _cursorIndexOfOption = _cursor.getColumnIndexOrThrow("option");
      final int _cursorIndexOfValue = _cursor.getColumnIndexOrThrow("value");
      final List<TriggerDetails> _result = new ArrayList<TriggerDetails>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TriggerDetails _item;
        final EventValue _tmpEventValue;
        if (! (_cursor.isNull(_cursorIndexOfOption) && _cursor.isNull(_cursorIndexOfValue))) {
          final int _tmpOption;
          _tmpOption = _cursor.getInt(_cursorIndexOfOption);
          final String _tmpValue;
          _tmpValue = _cursor.getString(_cursorIndexOfValue);
          _tmpEventValue = new EventValue(_tmpOption,_tmpValue);
        }  else  {
          _tmpEventValue = null;
        }
        _item = new TriggerDetails();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpTriggerId;
        _tmpTriggerId = _cursor.getLong(_cursorIndexOfTriggerId);
        _item.setTriggerId(_tmpTriggerId);
        final long _tmpMacroId;
        _tmpMacroId = _cursor.getLong(_cursorIndexOfMacroId);
        _item.setMacroId(_tmpMacroId);
        _item.setEventValue(_tmpEventValue);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
