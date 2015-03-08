package com.junjunguo.localfilehandling.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.junjunguo.localfilehandling.model.TextReport;

import java.util.ArrayList;
import java.util.List;


/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 08/03/15.
 * <p/>
 */
public class DAOtextReport {
    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAOtextReport(Context context) {
        dbHelper = new DBhelper(context);
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
        ContentValues cv = new ContentValues();
        cv.put(DBtables.TextReportTB.COLUMN_NUSER_ID, textReport.getUserid());
        cv.put(DBtables.TextReportTB.COLUMN_REPORT, textReport.getReport());
        cv.put(DBtables.TextReportTB.COLUMN_ISREPOETED, textReport.isIsreported());
        cv.put(DBtables.TextReportTB.COLUMN_LONGITUDE, textReport.getLongitude());
        cv.put(DBtables.TextReportTB.COLUMN_LATITUDE, textReport.getLatitude());
        cv.put(DBtables.TextReportTB.COLUMN_DATETIME, System.currentTimeMillis());
        return database.insert(DBtables.TextReportTB.TABLE_NAME, null, cv);
    }

    /**
     * update given text reports isReported value
     *
     * @param textReport
     * @return the number of rows affected
     */
    public long updateIsReported(TextReport textReport) {
        ContentValues cv = new ContentValues();
        cv.put(DBtables.TextReportTB.COLUMN_ISREPOETED, textReport.isIsreported());
        String where = DBtables.TextReportTB.COLUMN_NUSER_ID + "=" + textReport.getUserid() + "AND" +
                DBtables.TextReportTB.COLUMN_REPORT + "=" + textReport.getReport() + "AND" +
                DBtables.TextReportTB.COLUMN_DATETIME + "=" +
                textReport.getDatetime().getTimeInMillis();
        return database.update(DBtables.TextReportTB.TABLE_NAME, cv, where, null);
    }

    /**
     * @return all text reports as a List
     */
    public List<TextReport> getAllTextReports() {
        List<TextReport> textReports = new ArrayList<>();
        Cursor cursor =
                database.query(DBtables.TextReportTB.TABLE_NAME, DBtables.TextReportTB.ALL_COLUMNS, null, null, null,
                        null, DBtables.TextReportTB.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TextReport textReport = cursorToTextReport(cursor);
            textReports.add(textReport);
            cursor.moveToNext();
        }
        cursor.close();
        return textReports;
    }


    /**
     * @return total row count of the table
     */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + DBtables.TextReportTB.TABLE_NAME;
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
        tr.setUserid(cursor.getString(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_NUSER_ID)));
        tr.setReport(cursor.getString(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_REPORT)));
        tr.setDatetime(cursor.getLong(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_DATETIME)));
        tr.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_LONGITUDE)));
        tr.setLatitude(cursor.getDouble(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_LATITUDE)));
        tr.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReportTB.COLUMN_ISREPOETED)) > 0);
        return tr;
    }
}
