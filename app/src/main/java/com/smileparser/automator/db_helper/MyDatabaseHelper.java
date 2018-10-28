package com.smileparser.automator.db_helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

public class MyDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "macro_android.db";
    private static final int DATABASE_VERSION = 2;

    /*private Dao<CategoryModel, Integer> simpleDao = null;
    private Dao<SubCategoryModel, Integer> subDao = null;
    private Dao<ActionModel, Integer> actionDao = null;
    private Dao<ConstraintModel, Integer> constraintDao = null;
    private Dao<EventValueModel, Integer> eventDao = null;
    private Dao<MacroModel, Integer> macroDao = null;
    private Dao<TriggerModel, Integer> triggerDao = null;*/

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            try {
                /*Names Of Category*/
                TableUtils.createTableIfNotExists(connectionSource, TriggerLabelsModel.class);
                TableUtils.createTableIfNotExists(connectionSource, ActionsLabelsModel.class);
                TableUtils.createTableIfNotExists(connectionSource, ConstraintLabelsModel.class);
                /*name of Category end*/

                /*Names of Sub Category*/
                TableUtils.createTableIfNotExists(connectionSource, SubTriggerLabelsModel.class);
                TableUtils.createTableIfNotExists(connectionSource, SubConstraintLabelsModel.class);
                TableUtils.createTableIfNotExists(connectionSource, SubActionsLabelsModel.class);
                /*Names of Sub Category end*/

                TableUtils.createTableIfNotExists(connectionSource, ActionModel.class);
                TableUtils.createTableIfNotExists(connectionSource, ConstraintModel.class);
                TableUtils.createTableIfNotExists(connectionSource, TriggerModel.class);
                //TableUtils.createTableIfNotExists(connectionSource, EventValueModel.class);
                TableUtils.createTableIfNotExists(connectionSource, MacroModel.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            Log.e(MyDatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(MyDatabaseHelper.class.getName(), "onUpgrade");
            /*Category name start*/
            TableUtils.dropTable(connectionSource, TriggerLabelsModel.class, true);
            TableUtils.dropTable(connectionSource, ActionsLabelsModel.class, true);
            TableUtils.dropTable(connectionSource, ConstraintLabelsModel.class, true);
            /*Category name end*/

            /*Names of Sub Category */
            TableUtils.dropTable(connectionSource, SubTriggerLabelsModel.class, true);
            TableUtils.dropTable(connectionSource, SubConstraintLabelsModel.class, true);
            TableUtils.dropTable(connectionSource, SubActionsLabelsModel.class, true);
            /*Names of Sub Category End */

            TableUtils.dropTable(connectionSource, ActionModel.class, true);
            TableUtils.dropTable(connectionSource, ConstraintModel.class, true);
            TableUtils.dropTable(connectionSource, TriggerModel.class, true);
            //TableUtils.dropTable(connectionSource, EventValueModel.class, true);
            TableUtils.dropTable(connectionSource, MacroModel.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(MyDatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /*public Dao<CategoryModel, Integer> getCategoryDao() throws SQLException {
        if (simpleDao == null) {
            try {
                simpleDao = getDao(CategoryModel.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return simpleDao;
    }

    public Dao<SubCategoryModel, Integer> getSubcategoryDao() throws SQLException {
        if (subDao == null) {
            try {
                subDao = getDao(SubCategoryModel.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return subDao;
    }*/

    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        // lookup the dao, possibly invoking the cached database config
        Dao<T, ?> dao = DaoManager.lookupDao(connectionSource, clazz);
        if (dao == null) {
            // try to use our new reflection magic
            DatabaseTableConfig<T> tableConfig = null;
            try {
                tableConfig = DatabaseTableConfigUtil.fromClass(connectionSource, clazz);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            if (tableConfig == null) {
                /*
                  TODO: we have to do this to get to see if they are using the deprecated annotations like
                  {@link DatabaseFieldSimple}.
                 */
                try {
                    dao = DaoManager.createDao(connectionSource, clazz);
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dao = DaoManager.createDao(connectionSource, tableConfig);
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        @SuppressWarnings("unchecked")
        D castDao = (D) dao;
        return castDao;
    }

    @Override
    public void close() {
        super.close();
    }
}