package com.junjunguo.sqlitedb.dao;

import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 06/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class MyTables {
    // To prevent someone from accidentally instantiating the contract class, give it an empty constructor.
    public MyTables() {}

    /**
     * @return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     */
    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Calendar getDateTime(String datetime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cal.setTime(sdf.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    /* Inner class that defines the table contents */
    public static abstract class TextReport implements BaseColumns {
        public static final String TABLE_NAME = "textreport";

        public static final String COLUMN_NUSER_ID = "userid";
        public static final String COLUMN_REPORT = "report";
        public static final String COLUMN_ISREPOETED = "isreported";
        public static final String COLUMN_DATETIME = "datetime";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longtitude";
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_NUSER_ID + "," + COLUMN_REPORT + "," +
                "," + COLUMN_LONGITUDE + "," + COLUMN_LATITUDE + ")";
        public static final String[] ALL_COLUMNS =
                {COLUMN_NUSER_ID, COLUMN_REPORT, COLUMN_ISREPOETED, COLUMN_DATETIME, COLUMN_LATITUDE, COLUMN_LONGITUDE};
    }
}
