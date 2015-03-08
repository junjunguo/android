package com.junjunguo.localfilehandling.localdb;

import android.provider.BaseColumns;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 08/03/15.
 * <p/>
 */
public class DBtables {
    // To prevent someone from accidentally instantiating the contract class, give it an empty constructor.
    public DBtables() {}

    /* Inner class that defines the table contents */
    public static abstract class TextReportTB implements BaseColumns {
        public static final String TABLE_NAME = "textreport";

        public static final String COLUMN_NUSER_ID = "userid";
        public static final String COLUMN_REPORT = "report";
        public static final String COLUMN_ISREPOETED = "isreported";
        public static final String COLUMN_DATETIME = "datetime";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longtitude";
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_NUSER_ID + "," + COLUMN_DATETIME  + ")";
        public static final String[] ALL_COLUMNS =
                {COLUMN_NUSER_ID, COLUMN_REPORT, COLUMN_ISREPOETED, COLUMN_DATETIME, COLUMN_LATITUDE, COLUMN_LONGITUDE};
    }

    public static abstract class LocationTB implements BaseColumns {
        public static final String TABLE_NAME = "locationreport";

        public static final String COLUMN_NUSER_ID = "userid";
        public static final String COLUMN_ISREPOETED = "isreported";
        public static final String COLUMN_DATETIME = "datetime";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longtitude";
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_NUSER_ID + "," + COLUMN_DATETIME  + ")";
        public static final String[] ALL_COLUMNS =
                {COLUMN_NUSER_ID, COLUMN_ISREPOETED, COLUMN_DATETIME, COLUMN_LATITUDE, COLUMN_LONGITUDE};
    }
}
