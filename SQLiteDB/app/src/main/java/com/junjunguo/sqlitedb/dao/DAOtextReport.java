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
 */
public class DAOtextReport {
    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAOtextReport(Context context) {
        dbHelper = new DBhelper(context);
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
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addReport(TextReport textReport) {
        // Create a new map of values, where column names are the keys
        ContentValues cv = new ContentValues();
        cv.put(DBtables.TextReport.COLUMN_NUSER_ID, textReport.getUserid());
        cv.put(DBtables.TextReport.COLUMN_REPORT, textReport.getReport());
        cv.put(DBtables.TextReport.COLUMN_ISREPOETED, textReport.isIsreported());
        cv.put(DBtables.TextReport.COLUMN_LONGITUDE, textReport.getLongitude());
        cv.put(DBtables.TextReport.COLUMN_LATITUDE, textReport.getLatitude());
        cv.put(DBtables.TextReport.COLUMN_DATETIME, System.currentTimeMillis());

        // Insert the new row, returning the primary key value of the new row
        return database.insert(DBtables.TextReport.TABLE_NAME, null, cv);
    }

    /**
     * update given text reports isReported value
     *
     * @param textReport
     * @return the number of rows affected
     */
    public long updateIsReported(TextReport textReport) {
        ContentValues cv = new ContentValues();
        cv.put(DBtables.TextReport.COLUMN_ISREPOETED, textReport.isIsreported());

        String where = DBtables.TextReport.COLUMN_NUSER_ID + "=" + textReport.getUserid() + "AND" +
                DBtables.TextReport.COLUMN_REPORT + "=" + textReport.getReport() + "AND" +
                DBtables.TextReport.COLUMN_DATETIME + "=" +
                textReport.getDatetime().getTimeInMillis();
        return database.update(DBtables.TextReport.TABLE_NAME, cv, where, null);
    }

    /**
     * @return all text reports as a List
     */
    public List<TextReport> getAllTextReports() {
        List<TextReport> textReports = new ArrayList<>();

        Cursor cursor =
                database.query(DBtables.TextReport.TABLE_NAME, DBtables.TextReport.ALL_COLUMNS, null, null, null, null,
                        DBtables.TextReport.COLUMN_DATETIME + " DESC");

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


    /**
     * @return total row count of the table
     */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + DBtables.TextReport.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /**
     * *
     *
     * @param cursor the cursor row
     * @return a TextReport
     */
    private TextReport cursorToTextReport(Cursor cursor) {
        TextReport tr = new TextReport();

        tr.setUserid(cursor.getString(cursor.getColumnIndex(DBtables.TextReport.COLUMN_NUSER_ID)));
        tr.setReport(cursor.getString(cursor.getColumnIndex(DBtables.TextReport.COLUMN_REPORT)));
        tr.setDatetime(cursor.getLong(cursor.getColumnIndex(DBtables.TextReport.COLUMN_DATETIME)));
        tr.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBtables.TextReport.COLUMN_LONGITUDE)));
        tr.setLatitude(cursor.getDouble(cursor.getColumnIndex(DBtables.TextReport.COLUMN_LATITUDE)));
        // here we convert int to boolean
        tr.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return tr;
    }
}
