package com.junjunguo.sqlitedb.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.junjunguo.sqlitedb.model.TextReport;

import java.util.ArrayList;
import java.util.List;


/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 06/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class DAOtextReport {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public DAOtextReport(Context context) {
        dbHelper = new MySQLiteHelper(context);
        // Gets the data repository in write mode
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close any database object
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * insert a text report item to the textreport database table
     *
     * @param textReport
     */
    public void addReport(TextReport textReport) {
        // Create a new map of values, where column names are the keys
        ContentValues cv = new ContentValues();
        cv.put(MyTables.TextReport.COLUMN_NUSER_ID, textReport.getUserid());
        cv.put(MyTables.TextReport.COLUMN_REPORT, textReport.getReport());
        cv.put(MyTables.TextReport.COLUMN_ISREPOETED, textReport.isIsreported());
        cv.put(MyTables.TextReport.COLUMN_LONGITUDE, textReport.getLongitude());
        cv.put(MyTables.TextReport.COLUMN_LATITUDE, textReport.getLatitude());
        cv.put(MyTables.TextReport.COLUMN_DATETIME, MyTables.getSimpleDateFormat().format(textReport.getDatetime()));

        // Insert the new row, returning the primary key value of the new row
        database.insert(MyTables.TextReport.TABLE_NAME, null, cv);
    }

    public List<TextReport> getAllTextReports() {
        List<TextReport> textReports = new ArrayList<>();

        Cursor cursor =
                database.query(MyTables.TextReport.TABLE_NAME, MyTables.TextReport.ALL_COLUMNS, null, null, null, null,
                        null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TextReport textReport = cursorToTextReport(cursor);
            textReports.add(textReport);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return textReports;
    }

    private TextReport cursorToTextReport(Cursor cursor) {
        TextReport textReport = new TextReport();
        
        textReport.setUserid(cursor.getString(cursor.getColumnIndex(MyTables.TextReport.COLUMN_NUSER_ID)));
        textReport.setReport(cursor.getString(cursor.getColumnIndex(MyTables.TextReport.COLUMN_REPORT)));
        textReport.setDatetime(MyTables.getDateTime(cursor.getString(cursor.getColumnIndex(MyTables.TextReport.COLUMN_DATETIME))));
        textReport.setLongitude(cursor.getDouble(cursor.getColumnIndex(MyTables.TextReport.COLUMN_LONGITUDE)));
        textReport.setLatitude(cursor.getDouble(cursor.getColumnIndex(MyTables.TextReport.COLUMN_LATITUDE)));
        textReport.setIsreported(cursor.get);
        return textReport;
    }

}
