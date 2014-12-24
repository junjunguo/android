// /*
//  * Copyright (C) 2014 The Android Open Source Project
//  *
//  * Licensed under the Apache License, Version 2.0 (the "License");
//  * you may not use this file except in compliance with the License.
//  * You may obtain a copy of the License at
//  *
//  *      http://www.apache.org/licenses/LICENSE-2.0
//  *
//  * Unless required by applicable law or agreed to in writing, software
//  * distributed under the License is distributed on an "AS IS" BASIS,
//  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  * See the License for the specific language governing permissions and
//  * limitations under the License.
//  */
// package com.example.android.sunshine.app.data;

// import android.content.ContentProvider;
// import android.content.ContentUris;
// import android.content.ContentValues;
// import android.content.UriMatcher;
// import android.database.Cursor;
// import android.database.sqlite.SQLiteDatabase;
// import android.database.sqlite.SQLiteQueryBuilder;
// import android.net.Uri;

// public class WeatherProvider extends ContentProvider {

//     // The URI Matcher used by this content provider.
//     private static final UriMatcher sUriMatcher = buildUriMatcher();
//     private WeatherDbHelper mOpenHelper;

//     private static final int WEATHER = 100;
//     private static final int WEATHER_WITH_LOCATION = 101;
//     private static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
//     private static final int LOCATION = 300;
//     private static final int LOCATION_ID = 301;

//     private static UriMatcher buildUriMatcher() {
//         // I know what you're thinking.  Why create a UriMatcher when you can use regular
//         // expressions instead?  Because you're not crazy, that's why.

//         // All paths added to the UriMatcher have a corresponding code to return when a match is
//         // found.  The code passed into the constructor represents the code to return for the root
//         // URI.  It's common to use NO_MATCH as the code for this case.
//         final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//         final String authority = WeatherContract.CONTENT_AUTHORITY;

//         // For each type of URI you want to add, create a corresponding code.
//         matcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
//         matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
//         matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/*", WEATHER_WITH_LOCATION_AND_DATE);

//         matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
//         matcher.addURI(authority, WeatherContract.PATH_LOCATION + "/#", LOCATION_ID);

//         return matcher;
//     }

//     private static final SQLiteQueryBuilder sWeatherByLocationSettingQueryBuilder;

//     static{
//         sWeatherByLocationSettingQueryBuilder = new SQLiteQueryBuilder();
//         sWeatherByLocationSettingQueryBuilder.setTables(
//                 WeatherContract.WeatherEntry.TABLE_NAME + " INNER JOIN " +
//                         WeatherContract.LocationEntry.TABLE_NAME +
//                         " ON " + WeatherContract.WeatherEntry.TABLE_NAME +
//                         "." + WeatherContract.WeatherEntry.COLUMN_LOC_KEY +
//                         " = " + WeatherContract.LocationEntry.TABLE_NAME +
//                         "." + WeatherContract.LocationEntry._ID);
//     }

//     private static final String sLocationSettingSelection =
//             WeatherContract.LocationEntry.TABLE_NAME+
//                     "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? ";
//     private static final String sLocationSettingWithStartDateSelection =
//             WeatherContract.LocationEntry.TABLE_NAME+
//                     "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? AND " +
//                     WeatherContract.WeatherEntry.COLUMN_DATETEXT + " >= ? ";

//     private static final String sLocationSettingAndDaySelection =
//             WeatherContract.LocationEntry.TABLE_NAME +
//                     "." + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ? AND " +
//                     WeatherContract.WeatherEntry.COLUMN_DATETEXT + " = ? ";

//     private Cursor getWeatherByLocationSetting(Uri uri, String[] projection, String sortOrder) {
//         String locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri);
//         String startDate = WeatherContract.WeatherEntry.getStartDateFromUri(uri);

//         String[] selectionArgs;
//         String selection;

//         if (startDate == null) {
//             selection = sLocationSettingSelection;
//             selectionArgs = new String[]{locationSetting};
//         } else {
//             selectionArgs = new String[]{locationSetting, startDate};
//             selection = sLocationSettingWithStartDateSelection;
//         }

//         return sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
//                 projection,
//                 selection,
//                 selectionArgs,
//                 null,
//                 null,
//                 sortOrder
//         );
//     }

//     private Cursor getWeatherByLocationSettingAndDate(
//             Uri uri, String[] projection, String sortOrder) {
//         String locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri);
//         String date = WeatherContract.WeatherEntry.getDateFromUri(uri);

//         return sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
//                 projection,
//                 sLocationSettingAndDaySelection,
//                 new String[]{locationSetting, date},
//                 null,
//                 null,
//                 sortOrder
//         );
//     }

//     @Override
//     public boolean onCreate() {
//         mOpenHelper = new WeatherDbHelper(getContext());
//         return true;
//     }

//     @Override
//     public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
//                         String sortOrder) {
//         // Here's the switch statement that, given a URI, will determine what kind of request it is,
//         // and query the database accordingly.
//         Cursor retCursor;
//         switch (sUriMatcher.match(uri)) {
//             // "weather/*/*"
//             case WEATHER_WITH_LOCATION_AND_DATE:
//             {
//                 retCursor = getWeatherByLocationSettingAndDate(uri, projection, sortOrder);
//                 break;
//             }
//             // "weather/*"
//             case WEATHER_WITH_LOCATION: {
//                 retCursor = getWeatherByLocationSetting(uri, projection, sortOrder);
//                 break;
//             }
//             // "weather"
//             case WEATHER: {
//                 retCursor = mOpenHelper.getReadableDatabase().query(
//                         WeatherContract.WeatherEntry.TABLE_NAME,
//                         projection,
//                         selection,
//                         selectionArgs,
//                         null,
//                         null,
//                         sortOrder
//                 );
//                 break;
//             }

//             /**
//              * TODO YOUR CODE BELOW HERE FOR QUIZ
//              * QUIZ - 4b - Implement Location_ID queries
//              * https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/e-1675098551/m-1675098552
//              **/

//             default:
//                 throw new UnsupportedOperationException("Unknown uri: " + uri);
//         }
//         retCursor.setNotificationUri(getContext().getContentResolver(), uri);
//         return retCursor;
//     }

//     @Override
//     public String getType(Uri uri) {

//         // Use the Uri Matcher to determine what kind of URI this is.
//         final int match = sUriMatcher.match(uri);

//         switch (match) {
//             case WEATHER_WITH_LOCATION_AND_DATE:
//                 return WeatherContract.WeatherEntry.CONTENT_ITEM_TYPE;
//             case WEATHER_WITH_LOCATION:
//                 return WeatherContract.WeatherEntry.CONTENT_TYPE;
//             case WEATHER:
//                 return WeatherContract.WeatherEntry.CONTENT_TYPE;

//             /**
//              * TODO YOUR CODE BELOW HERE FOR QUIZ
//              * QUIZ - 4b - Coding the Content Provider : getType
//              * https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/e-1675098546/m-1675098547
//              **/

//             default:
//                 throw new UnsupportedOperationException("Unknown uri: " + uri);
//         }
//     }

//     @Override
//     public Uri insert(Uri uri, ContentValues values) {
//         final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
//         final int match = sUriMatcher.match(uri);
//         Uri returnUri;

//         switch (match) {
//             case WEATHER: {
//                 long _id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, values);
//                 if ( _id > 0 )
//                     returnUri = WeatherContract.WeatherEntry.buildWeatherUri(_id);
//                 else
//                     throw new android.database.SQLException("Failed to insert row into " + uri);
//                 break;
//             }
//             case LOCATION: {
//                 long _id = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, values);
//                 if ( _id > 0 )
//                     returnUri = WeatherContract.LocationEntry.buildLocationUri(_id);
//                 else
//                     throw new android.database.SQLException("Failed to insert row into " + uri);
//                 break;
//             }
//             default:
//                 throw new UnsupportedOperationException("Unknown uri: " + uri);
//         }
//         getContext().getContentResolver().notifyChange(uri, null);
//         return returnUri;
//     }

//     @Override
//     public int delete(Uri uri, String selection, String[] selectionArgs) {
//         final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
//         final int match = sUriMatcher.match(uri);
//         int rowsDeleted;
//         switch (match) {
//             case WEATHER:
//                 rowsDeleted = db.delete(
//                         WeatherContract.WeatherEntry.TABLE_NAME, selection, selectionArgs);
//                 break;
//             case LOCATION:
//                 rowsDeleted = db.delete(
//                         WeatherContract.LocationEntry.TABLE_NAME, selection, selectionArgs);
//                 break;
//             default:
//                 throw new UnsupportedOperationException("Unknown uri: " + uri);
//         }
//         // Because a null deletes all rows
//         if (selection == null || rowsDeleted != 0) {
//             getContext().getContentResolver().notifyChange(uri, null);
//         }
//         return rowsDeleted;
//     }

//     @Override
//     public int update(
//             Uri uri, ContentValues values, String selection, String[] selectionArgs) {
//         /**
//          * TODO YOUR CODE BELOW HERE FOR QUIZ
//          * QUIZ - 4b - Updating and Deleting
//          * https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/e-1675098563/m-1675098564
//          **/
//         return 0;
//     }

//     @Override
//     public int bulkInsert(Uri uri, ContentValues[] values) {
//         final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
//         final int match = sUriMatcher.match(uri);
//         switch (match) {
//             case WEATHER:
//                 db.beginTransaction();
//                 int returnCount = 0;
//                 try {
//                     for (ContentValues value : values) {
//                         long _id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, value);
//                         if (_id != -1) {
//                             returnCount++;
//                         }
//                     }
//                     db.setTransactionSuccessful();
//                 } finally {
//                     db.endTransaction();
//                 }
//                 getContext().getContentResolver().notifyChange(uri, null);
//                 return returnCount;
//             default:
//                 return super.bulkInsert(uri, values);
//         }
//     }
// }
