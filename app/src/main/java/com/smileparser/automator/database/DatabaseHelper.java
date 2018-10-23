package com.smileparser.automator.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.smileparser.automator.triggeractionmanager.Action;
import com.smileparser.automator.triggeractionmanager.ActionDao;
import com.smileparser.automator.triggeractionmanager.ActionDetails;
import com.smileparser.automator.triggeractionmanager.ActionDetailsDao;
import com.smileparser.automator.triggeractionmanager.Category;
import com.smileparser.automator.triggeractionmanager.CategoryDao;
import com.smileparser.automator.triggeractionmanager.Constraint;
import com.smileparser.automator.triggeractionmanager.ConstraintDao;
import com.smileparser.automator.triggeractionmanager.ConstraintDetails;
import com.smileparser.automator.triggeractionmanager.ConstraintDetailsDao;
import com.smileparser.automator.triggeractionmanager.Macro;
import com.smileparser.automator.triggeractionmanager.MacroDao;
import com.smileparser.automator.triggeractionmanager.Trigger;
import com.smileparser.automator.triggeractionmanager.TriggerDao;
import com.smileparser.automator.triggeractionmanager.TriggerDetails;
import com.smileparser.automator.triggeractionmanager.TriggerDetailsDao;

import java.util.concurrent.Executors;

/**
 * Created by mayur on 30/9/18.
 */

@Database(entities = {Category.class, Trigger.class, Action.class, Macro.class, TriggerDetails.class,
        ActionDetails.class, Constraint.class, ConstraintDetails.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getAppDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                INSTANCE = buildDatabase(context);
            }
        }
        return INSTANCE;
    }

    private static DatabaseHelper buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, "automator_database")
                .fallbackToDestructiveMigration()
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                DatabaseHelper dbhelper = getAppDatabase(context);
                                Category category = new Category("Battery/Power");
                                long categoryId = dbhelper.getCategoryDao().insert(category);

                                Trigger trigger = new Trigger(categoryId, "Battery level increase/decrease/any");
                                dbhelper.getTriggerDao().insert(trigger);

                                trigger = new Trigger(categoryId, "Battery saver enabled/disabled");
                                dbhelper.getTriggerDao().insert(trigger);

                                trigger = new Trigger(categoryId, "Power button press 3/4/5 times");
                                dbhelper.getTriggerDao().insert(trigger);

                                trigger = new Trigger(categoryId, "Power connected/disconnected");
                                dbhelper.getTriggerDao().insert(trigger);

                                Action action = new Action(categoryId, "Launch application");
                                dbhelper.getActionDao().insert(action);

                                Constraint constraint = new Constraint(categoryId, "Battery level less than, greater than, equal to");
                                dbhelper.getConstraintDao().insert(constraint);

                                constraint = new Constraint(categoryId, "Battery saver is enabled/disabled");
                                dbhelper.getConstraintDao().insert(constraint);

                                constraint = new Constraint(categoryId, "Power connected/disconnected");
                                dbhelper.getConstraintDao().insert(constraint);
                            }
                        });
                    }
                })
                .build();
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {

//        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                Category category = new Category("Battery/Power");
//                long categoryId = getCategoryDao().insert(category);
//
//                Trigger trigger = new Trigger(categoryId, "Battery level increase/decrease/any");
//                getTriggerDao().insert(trigger);
//
//                Action action = new Action(categoryId, "Launch application");
//                getActionDao().insert(action);
//            }
//        });
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract CategoryDao getCategoryDao();

    public abstract TriggerDao getTriggerDao();

    public abstract ActionDao getActionDao();

    public abstract ConstraintDao getConstraintDao();

    public abstract MacroDao getMacroDao();

    public abstract TriggerDetailsDao getTriggerDetailsDao();

    public abstract ActionDetailsDao getActionDetailsDao();

    public abstract ConstraintDetailsDao getConstraintDetailsDao();

//    private static final String database_name = "automator.db";
//    private static final int database_version = 1;
//    private SQLiteDatabase sqliteDatabase;
//
//    public DatabaseHelper(Context context) {
//        super(context, database_name, null, database_version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        createTables(sqLiteDatabase);
//    }
//
//    private void createTables(SQLiteDatabase sqliteDatabase) {
//        String createTableCategory = "create table category(id integer primary key autoincrement, name text)";
//        sqliteDatabase.execSQL(createTableCategory);
//
////        String createTableSubCategory = "create table subCategory(id integer primary key autoincrement, " +
////                "name text, categoryId integer)";
////        sqLiteDatabase.execSQL(createTableSubCategory);
//
//        String createTableTriggerMaster = "create table triggerMaster(id integer primary key autoincrement, " +
//                "description text, categoryId integer)";
//        sqliteDatabase.execSQL(createTableTriggerMaster);
//
//        String createTableActionMaster = "create table actionMaster(id integer primary key autoincrement, " +
//                "description text, categoryId integer)";
//        sqliteDatabase.execSQL(createTableActionMaster);
//
//        String createTableConstraintMaster = "create table constraintMaster(id integer primary key autoincrement, " +
//                "description text, categoryId integer)";
//        sqliteDatabase.execSQL(createTableConstraintMaster);
//
//        String createTableMacro = "create table macro(id integer primary key autoincrement, description text)";
//        sqliteDatabase.execSQL(createTableMacro);
//
//        String createTableTrigger = "create table trigger(id integer primary key autoincrement, description text)";
//        sqliteDatabase.execSQL(createTableTrigger);
//
//        String createTableAction = "create table action(id integer primary key autoincrement, description text)";
//        sqliteDatabase.execSQL(createTableAction);
//
//        String createTableConstraint = "create table constraint(id integer primary key autoincrement, description text)";
//        sqliteDatabase.execSQL(createTableConstraint);
//
//        ContentValues cv = new ContentValues();
//        cv.put("name", "Battery/Power");
//        sqliteDatabase.insert("category", null, cv);
//
//        cv = new ContentValues();
//        cv.put("name", "Applications");
//        sqliteDatabase.insert("category", null, cv);
//
//        cv = new ContentValues();
//        cv.put("description", "Battery level increase/decrease/any");
//        sqliteDatabase.insert("triggerMaster", null, cv);
//
//        cv = new ContentValues();
//        cv.put("description", "Launch app");
//        sqliteDatabase.insert("triggerMaster", null, cv);
//    }
//
//    public void addMacro() {
//        sqliteDatabase = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put();
//
//    }
//
//    public void getTrigger(int id) {
//        sqliteDatabase = getReadableDatabase();
//        Cursor cursor = sqliteDatabase.query("trigger", null, "id = ?", new String[]{"1"}, null, null, null, null);
//        cursor.moveToFirst();
//
//    }
//
//    public void addTrigger() {
//        sqliteDatabase = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("");
//    }
//
//    public void addAction() {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
}
