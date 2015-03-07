package com.junjunguo.sqlitedb.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 06/03/15.
 * <p/>
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sair.db";
    public static final int DB_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String NUMERIC_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_TEXTREPORT_TABLE = "CREATE TABLE " + DBtables.TextReport.TABLE_NAME + " (" +
            DBtables.TextReport.COLUMN_NUSER_ID + TEXT_TYPE + COMMA_SEP +
            DBtables.TextReport.COLUMN_REPORT + TEXT_TYPE + COMMA_SEP +
            DBtables.TextReport.COLUMN_DATETIME + NUMERIC_TYPE + COMMA_SEP +
            DBtables.TextReport.COLUMN_ISREPOETED + NUMERIC_TYPE + COMMA_SEP +
            DBtables.TextReport.COLUMN_LONGITUDE + REAL_TYPE + COMMA_SEP +
            DBtables.TextReport.COLUMN_LATITUDE + REAL_TYPE + COMMA_SEP +
            DBtables.TextReport.PRIMARY_KEY +
            " )";

    private static final String DELETE_TEXTREPORT_TABLE = "DROP TABLE IF EXISTS " + DBtables.TextReport.TABLE_NAME;

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEXTREPORT_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TEXTREPORT_TABLE);
        onCreate(db);
    }
}
