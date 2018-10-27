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
public class ConstraintDetailsDao_Impl implements ConstraintDetailsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfConstraintDetails;

  public ConstraintDetailsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConstraintDetails = new EntityInsertionAdapter<ConstraintDetails>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ConstraintDetails`(`id`,`constraintId`,`macroId`,`option`,`value`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ConstraintDetails value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getConstraintId());
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
  public long insert(ConstraintDetails constraintDetails) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfConstraintDetails.insertAndReturnId(constraintDetails);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ConstraintDetails> getConsraintDetailsListByMacro(long macroId) {
    final String _sql = "select * from ConstraintDetails where macroId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, macroId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfConstraintId = _cursor.getColumnIndexOrThrow("constraintId");
      final int _cursorIndexOfMacroId = _cursor.getColumnIndexOrThrow("macroId");
      final int _cursorIndexOfOption = _cursor.getColumnIndexOrThrow("option");
      final int _cursorIndexOfValue = _cursor.getColumnIndexOrThrow("value");
      final List<ConstraintDetails> _result = new ArrayList<ConstraintDetails>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ConstraintDetails _item;
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
        _item = new ConstraintDetails();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpConstraintId;
        _tmpConstraintId = _cursor.getLong(_cursorIndexOfConstraintId);
        _item.setConstraintId(_tmpConstraintId);
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
