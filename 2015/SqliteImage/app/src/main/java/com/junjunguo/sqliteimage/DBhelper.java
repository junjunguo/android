package com.junjunguo.sqliteimage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This file is part of SqliteImage
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 22, 2015.
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sqliteimage.db";
    public static final int DB_VERSION = 1;

    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE = " TEXT";
    public static final String NUMERIC_TYPE = " NUMERIC";

    public static final String TABLE_NAME = "image";

    public static final String COLUMN_PATH = "path";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_TITLE + "," + COLUMN_DATETIME + ")";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_PATH + TEXT_TYPE + COMMA_SEP +
            COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
            COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            COLUMN_DATETIME + NUMERIC_TYPE + COMMA_SEP +
            PRIMARY_KEY +
            " )";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
