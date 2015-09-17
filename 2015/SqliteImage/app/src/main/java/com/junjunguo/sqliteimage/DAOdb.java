package com.junjunguo.sqliteimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of SqliteImage
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 22, 2015.
 */
public class DAOdb {

    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAOdb(Context context) {
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
     * insert a text report item to the location database table
     *
     * @param image
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addImage(MyImage image) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COLUMN_PATH, image.getPath());
        cv.put(DBhelper.COLUMN_TITLE, image.getTitle());
        cv.put(DBhelper.COLUMN_DESCRIPTION, image.getDescription());
        cv.put(DBhelper.COLUMN_DATETIME, System.currentTimeMillis());
        return database.insert(DBhelper.TABLE_NAME, null, cv);
    }

    /**
     * delete the given image from database
     *
     * @param image
     */
    public void deleteImage(MyImage image) {
        String whereClause = DBhelper.COLUMN_TITLE + "=? AND " + DBhelper.COLUMN_DATETIME +
                "=?";
        String[] whereArgs = new String[]{image.getTitle(), String.valueOf(image.getDatetimeLong())};
        database.delete(DBhelper.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * @return all image as a List
     */
    public List<MyImage> getImages() {
        List<MyImage> MyImages = new ArrayList<>();
        Cursor cursor =
                database.query(DBhelper.TABLE_NAME, null, null, null, null, null, DBhelper.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MyImage myImage = cursorToMyImage(cursor);
            MyImages.add(myImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }

    /**
     * read the cursor row and convert the row to a MyImage object
     *
     * @param cursor
     * @return MyImage object
     */
    private MyImage cursorToMyImage(Cursor cursor) {
        MyImage image = new MyImage();
        image.setPath(cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_PATH)));
        image.setTitle(cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_TITLE)));
        image.setDatetime(cursor.getLong(cursor.getColumnIndex(DBhelper.COLUMN_DATETIME)));
        image.setDescription(cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_DESCRIPTION)));
        return image;
    }

    // save as image  bitmap

    /**
     *
     * @param image
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addImage(LoImage image) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COLUMN_IMG, image.getByteArray());
        cv.put(DBhelper.COLUMN_TITLE, image.getTitle());
        cv.put(DBhelper.COLUMN_DESCRIPTION, image.getDescription());
        cv.put(DBhelper.COLUMN_DATETIME, System.currentTimeMillis());
        return database.insert(DBhelper.TABLE_NAME, null, cv);
    }


    /**
     * @return all bitmap images as a List
     */
    public List<LoImage> getBitmapImages() {
        List<LoImage> loImages = new ArrayList<>();
        Cursor cursor =
                database.query(DBhelper.TABLE_NAME, null, null, null, null, null, DBhelper.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LoImage loImage = cursorToLoImage(cursor);
            loImages.add(loImage);
            cursor.moveToNext();
        }
        cursor.close();
        return loImages;
    }

    /**
     * read the cursor row and convert the row to a LoImage object (image is saved as bitmap)
     *
     * @param cursor
     * @return LoImage object
     */
    private LoImage cursorToLoImage(Cursor cursor) {
        LoImage image = new LoImage();
        image.setImg(cursor.getBlob(cursor.getColumnIndex(DBhelper.COLUMN_IMG)));
        image.setTitle(cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_TITLE)));
        image.setDatetime(cursor.getLong(cursor.getColumnIndex(DBhelper.COLUMN_DATETIME)));
        image.setDescription(cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_DESCRIPTION)));
        return image;
    }
}
