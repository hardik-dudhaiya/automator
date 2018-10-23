package com.smileparser.automator.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.smileparser.automator.triggeractionmanager.ActionDao;
import com.smileparser.automator.triggeractionmanager.ActionDao_Impl;
import com.smileparser.automator.triggeractionmanager.ActionDetailsDao;
import com.smileparser.automator.triggeractionmanager.ActionDetailsDao_Impl;
import com.smileparser.automator.triggeractionmanager.CategoryDao;
import com.smileparser.automator.triggeractionmanager.CategoryDao_Impl;
import com.smileparser.automator.triggeractionmanager.ConstraintDao;
import com.smileparser.automator.triggeractionmanager.ConstraintDao_Impl;
import com.smileparser.automator.triggeractionmanager.ConstraintDetailsDao;
import com.smileparser.automator.triggeractionmanager.ConstraintDetailsDao_Impl;
import com.smileparser.automator.triggeractionmanager.MacroDao;
import com.smileparser.automator.triggeractionmanager.MacroDao_Impl;
import com.smileparser.automator.triggeractionmanager.TriggerDao;
import com.smileparser.automator.triggeractionmanager.TriggerDao_Impl;
import com.smileparser.automator.triggeractionmanager.TriggerDetailsDao;
import com.smileparser.automator.triggeractionmanager.TriggerDetailsDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class DatabaseHelper_Impl extends DatabaseHelper {
  private volatile CategoryDao _categoryDao;

  private volatile TriggerDao _triggerDao;

  private volatile ActionDao _actionDao;

  private volatile ConstraintDao _constraintDao;

  private volatile MacroDao _macroDao;

  private volatile TriggerDetailsDao _triggerDetailsDao;

  private volatile ActionDetailsDao _actionDetailsDao;

  private volatile ConstraintDetailsDao _constraintDetailsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Category` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Trigger` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `categoryId` INTEGER NOT NULL, `description` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Action` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `categoryId` INTEGER NOT NULL, `description` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Macro` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `isActive` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TriggerDetails` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `triggerId` INTEGER NOT NULL, `macroId` INTEGER NOT NULL, `option` INTEGER, `value` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ActionDetails` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `actionId` INTEGER NOT NULL, `macroId` INTEGER NOT NULL, `option` INTEGER, `value` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Constraint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `categoryId` INTEGER NOT NULL, `description` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ConstraintDetails` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `constraintId` INTEGER NOT NULL, `macroId` INTEGER NOT NULL, `option` INTEGER, `value` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"3333c6f5a19682bbfddacef0e75d202c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Category`");
        _db.execSQL("DROP TABLE IF EXISTS `Trigger`");
        _db.execSQL("DROP TABLE IF EXISTS `Action`");
        _db.execSQL("DROP TABLE IF EXISTS `Macro`");
        _db.execSQL("DROP TABLE IF EXISTS `TriggerDetails`");
        _db.execSQL("DROP TABLE IF EXISTS `ActionDetails`");
        _db.execSQL("DROP TABLE IF EXISTS `Constraint`");
        _db.execSQL("DROP TABLE IF EXISTS `ConstraintDetails`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCategory = new HashMap<String, TableInfo.Column>(2);
        _columnsCategory.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCategory.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategory = new TableInfo("Category", _columnsCategory, _foreignKeysCategory, _indicesCategory);
        final TableInfo _existingCategory = TableInfo.read(_db, "Category");
        if (! _infoCategory.equals(_existingCategory)) {
          throw new IllegalStateException("Migration didn't properly handle Category(com.smileparser.automator.triggeractionmanager.Category).\n"
                  + " Expected:\n" + _infoCategory + "\n"
                  + " Found:\n" + _existingCategory);
        }
        final HashMap<String, TableInfo.Column> _columnsTrigger = new HashMap<String, TableInfo.Column>(3);
        _columnsTrigger.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsTrigger.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0));
        _columnsTrigger.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrigger = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrigger = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrigger = new TableInfo("Trigger", _columnsTrigger, _foreignKeysTrigger, _indicesTrigger);
        final TableInfo _existingTrigger = TableInfo.read(_db, "Trigger");
        if (! _infoTrigger.equals(_existingTrigger)) {
          throw new IllegalStateException("Migration didn't properly handle Trigger(com.smileparser.automator.triggeractionmanager.Trigger).\n"
                  + " Expected:\n" + _infoTrigger + "\n"
                  + " Found:\n" + _existingTrigger);
        }
        final HashMap<String, TableInfo.Column> _columnsAction = new HashMap<String, TableInfo.Column>(3);
        _columnsAction.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAction.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0));
        _columnsAction.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAction = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAction = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAction = new TableInfo("Action", _columnsAction, _foreignKeysAction, _indicesAction);
        final TableInfo _existingAction = TableInfo.read(_db, "Action");
        if (! _infoAction.equals(_existingAction)) {
          throw new IllegalStateException("Migration didn't properly handle Action(com.smileparser.automator.triggeractionmanager.Action).\n"
                  + " Expected:\n" + _infoAction + "\n"
                  + " Found:\n" + _existingAction);
        }
        final HashMap<String, TableInfo.Column> _columnsMacro = new HashMap<String, TableInfo.Column>(2);
        _columnsMacro.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMacro.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMacro = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMacro = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMacro = new TableInfo("Macro", _columnsMacro, _foreignKeysMacro, _indicesMacro);
        final TableInfo _existingMacro = TableInfo.read(_db, "Macro");
        if (! _infoMacro.equals(_existingMacro)) {
          throw new IllegalStateException("Migration didn't properly handle Macro(com.smileparser.automator.triggeractionmanager.Macro).\n"
                  + " Expected:\n" + _infoMacro + "\n"
                  + " Found:\n" + _existingMacro);
        }
        final HashMap<String, TableInfo.Column> _columnsTriggerDetails = new HashMap<String, TableInfo.Column>(5);
        _columnsTriggerDetails.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsTriggerDetails.put("triggerId", new TableInfo.Column("triggerId", "INTEGER", true, 0));
        _columnsTriggerDetails.put("macroId", new TableInfo.Column("macroId", "INTEGER", true, 0));
        _columnsTriggerDetails.put("option", new TableInfo.Column("option", "INTEGER", false, 0));
        _columnsTriggerDetails.put("value", new TableInfo.Column("value", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTriggerDetails = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTriggerDetails = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTriggerDetails = new TableInfo("TriggerDetails", _columnsTriggerDetails, _foreignKeysTriggerDetails, _indicesTriggerDetails);
        final TableInfo _existingTriggerDetails = TableInfo.read(_db, "TriggerDetails");
        if (! _infoTriggerDetails.equals(_existingTriggerDetails)) {
          throw new IllegalStateException("Migration didn't properly handle TriggerDetails(com.smileparser.automator.triggeractionmanager.TriggerDetails).\n"
                  + " Expected:\n" + _infoTriggerDetails + "\n"
                  + " Found:\n" + _existingTriggerDetails);
        }
        final HashMap<String, TableInfo.Column> _columnsActionDetails = new HashMap<String, TableInfo.Column>(5);
        _columnsActionDetails.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsActionDetails.put("actionId", new TableInfo.Column("actionId", "INTEGER", true, 0));
        _columnsActionDetails.put("macroId", new TableInfo.Column("macroId", "INTEGER", true, 0));
        _columnsActionDetails.put("option", new TableInfo.Column("option", "INTEGER", false, 0));
        _columnsActionDetails.put("value", new TableInfo.Column("value", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActionDetails = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActionDetails = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActionDetails = new TableInfo("ActionDetails", _columnsActionDetails, _foreignKeysActionDetails, _indicesActionDetails);
        final TableInfo _existingActionDetails = TableInfo.read(_db, "ActionDetails");
        if (! _infoActionDetails.equals(_existingActionDetails)) {
          throw new IllegalStateException("Migration didn't properly handle ActionDetails(com.smileparser.automator.triggeractionmanager.ActionDetails).\n"
                  + " Expected:\n" + _infoActionDetails + "\n"
                  + " Found:\n" + _existingActionDetails);
        }
        final HashMap<String, TableInfo.Column> _columnsConstraint = new HashMap<String, TableInfo.Column>(3);
        _columnsConstraint.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsConstraint.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0));
        _columnsConstraint.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConstraint = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConstraint = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConstraint = new TableInfo("Constraint", _columnsConstraint, _foreignKeysConstraint, _indicesConstraint);
        final TableInfo _existingConstraint = TableInfo.read(_db, "Constraint");
        if (! _infoConstraint.equals(_existingConstraint)) {
          throw new IllegalStateException("Migration didn't properly handle Constraint(com.smileparser.automator.triggeractionmanager.Constraint).\n"
                  + " Expected:\n" + _infoConstraint + "\n"
                  + " Found:\n" + _existingConstraint);
        }
        final HashMap<String, TableInfo.Column> _columnsConstraintDetails = new HashMap<String, TableInfo.Column>(5);
        _columnsConstraintDetails.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsConstraintDetails.put("constraintId", new TableInfo.Column("constraintId", "INTEGER", true, 0));
        _columnsConstraintDetails.put("macroId", new TableInfo.Column("macroId", "INTEGER", true, 0));
        _columnsConstraintDetails.put("option", new TableInfo.Column("option", "INTEGER", false, 0));
        _columnsConstraintDetails.put("value", new TableInfo.Column("value", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConstraintDetails = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConstraintDetails = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConstraintDetails = new TableInfo("ConstraintDetails", _columnsConstraintDetails, _foreignKeysConstraintDetails, _indicesConstraintDetails);
        final TableInfo _existingConstraintDetails = TableInfo.read(_db, "ConstraintDetails");
        if (! _infoConstraintDetails.equals(_existingConstraintDetails)) {
          throw new IllegalStateException("Migration didn't properly handle ConstraintDetails(com.smileparser.automator.triggeractionmanager.ConstraintDetails).\n"
                  + " Expected:\n" + _infoConstraintDetails + "\n"
                  + " Found:\n" + _existingConstraintDetails);
        }
      }
    }, "3333c6f5a19682bbfddacef0e75d202c", "a93c7982cebc03968affca21a220a1f3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Category","Trigger","Action","Macro","TriggerDetails","ActionDetails","Constraint","ConstraintDetails");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Category`");
      _db.execSQL("DELETE FROM `Trigger`");
      _db.execSQL("DELETE FROM `Action`");
      _db.execSQL("DELETE FROM `Macro`");
      _db.execSQL("DELETE FROM `TriggerDetails`");
      _db.execSQL("DELETE FROM `ActionDetails`");
      _db.execSQL("DELETE FROM `Constraint`");
      _db.execSQL("DELETE FROM `ConstraintDetails`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CategoryDao getCategoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public TriggerDao getTriggerDao() {
    if (_triggerDao != null) {
      return _triggerDao;
    } else {
      synchronized(this) {
        if(_triggerDao == null) {
          _triggerDao = new TriggerDao_Impl(this);
        }
        return _triggerDao;
      }
    }
  }

  @Override
  public ActionDao getActionDao() {
    if (_actionDao != null) {
      return _actionDao;
    } else {
      synchronized(this) {
        if(_actionDao == null) {
          _actionDao = new ActionDao_Impl(this);
        }
        return _actionDao;
      }
    }
  }

  @Override
  public ConstraintDao getConstraintDao() {
    if (_constraintDao != null) {
      return _constraintDao;
    } else {
      synchronized(this) {
        if(_constraintDao == null) {
          _constraintDao = new ConstraintDao_Impl(this);
        }
        return _constraintDao;
      }
    }
  }

  @Override
  public MacroDao getMacroDao() {
    if (_macroDao != null) {
      return _macroDao;
    } else {
      synchronized(this) {
        if(_macroDao == null) {
          _macroDao = new MacroDao_Impl(this);
        }
        return _macroDao;
      }
    }
  }

  @Override
  public TriggerDetailsDao getTriggerDetailsDao() {
    if (_triggerDetailsDao != null) {
      return _triggerDetailsDao;
    } else {
      synchronized(this) {
        if(_triggerDetailsDao == null) {
          _triggerDetailsDao = new TriggerDetailsDao_Impl(this);
        }
        return _triggerDetailsDao;
      }
    }
  }

  @Override
  public ActionDetailsDao getActionDetailsDao() {
    if (_actionDetailsDao != null) {
      return _actionDetailsDao;
    } else {
      synchronized(this) {
        if(_actionDetailsDao == null) {
          _actionDetailsDao = new ActionDetailsDao_Impl(this);
        }
        return _actionDetailsDao;
      }
    }
  }

  @Override
  public ConstraintDetailsDao getConstraintDetailsDao() {
    if (_constraintDetailsDao != null) {
      return _constraintDetailsDao;
    } else {
      synchronized(this) {
        if(_constraintDetailsDao == null) {
          _constraintDetailsDao = new ConstraintDetailsDao_Impl(this);
        }
        return _constraintDetailsDao;
      }
    }
  }
}
