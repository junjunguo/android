package com.junjunguo.simplesqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of Simple SQLite DB
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 05/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class ReportDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    public ReportDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public TextReport createTextReport(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        TextReport newTextReport = cursorToComment(cursor);
        cursor.close();
        return newTextReport;
    }

    public void deleteTextReport(TextReport textReport) {
        long id = textReport.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<TextReport> getAllTextReports() {
        List<TextReport> comments = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TextReport comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private TextReport cursorToComment(Cursor cursor) {
        TextReport textReport = new TextReport();
        textReport.setId(cursor.getLong(0));
        textReport.setTextreport(cursor.getString(1));
        return textReport;
    }
    
}
