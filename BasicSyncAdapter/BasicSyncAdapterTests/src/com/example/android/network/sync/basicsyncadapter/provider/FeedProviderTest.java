package com.example.android.network.sync.basicsyncadapter.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

public class FeedProviderTest extends ProviderTestCase2<FeedProvider>  {
    public FeedProviderTest() {
        super(FeedProvider.class, FeedContract.CONTENT_AUTHORITY);
    }

    public void testEntryContentUriIsSane() {
        assertEquals(Uri.parse("content://com.example.android.network.sync.basicsyncadapter/entries"),
                FeedContract.Entry.CONTENT_URI);
    }

    public void testCreateAndRetrieve() {
        // Create
        ContentValues newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_TITLE, "MyTitle");
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://example.com");
        newValues.put(FeedContract.Entry.COLUMN_NAME_ENTRY_ID, "MyEntryID");
        Uri newUri = getMockContentResolver().insert(
                FeedContract.Entry.CONTENT_URI,
                newValues);

        // Retrieve
        String[] projection = {
                FeedContract.Entry.COLUMN_NAME_TITLE,      // 0
                FeedContract.Entry.COLUMN_NAME_LINK,       // 1
                FeedContract.Entry.COLUMN_NAME_ENTRY_ID};  // 2
        Cursor c = getMockContentResolver().query(newUri, projection, null, null, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("MyTitle", c.getString(0));
        assertEquals("http://example.com", c.getString(1));
        assertEquals("MyEntryID", c.getString(2));
    }

    public void testCreateAndQuery() {
        // Create
        ContentValues newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_TITLE, "Alpha-MyTitle");
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://alpha.example.com");
        newValues.put(FeedContract.Entry.COLUMN_NAME_ENTRY_ID, "Alpha-MyEntryID");
        getMockContentResolver().insert(
                FeedContract.Entry.CONTENT_URI,
                newValues);

        newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_TITLE, "Beta-MyTitle");
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://beta.example.com");
        newValues.put(FeedContract.Entry.COLUMN_NAME_ENTRY_ID, "Beta-MyEntryID");
        getMockContentResolver().insert(
                FeedContract.Entry.CONTENT_URI,
                newValues);

        // Retrieve
        String[] projection = {
                FeedContract.Entry.COLUMN_NAME_TITLE,      // 0
                FeedContract.Entry.COLUMN_NAME_LINK,       // 1
                FeedContract.Entry.COLUMN_NAME_ENTRY_ID};  // 2
        String where = FeedContract.Entry.COLUMN_NAME_TITLE + " LIKE ?";
        Cursor c = getMockContentResolver().query(FeedContract.Entry.CONTENT_URI, projection,
                where, new String[] {"Alpha%"}, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("Alpha-MyTitle", c.getString(0));
        assertEquals("http://alpha.example.com", c.getString(1));
        assertEquals("Alpha-MyEntryID", c.getString(2));
    }

    public void testUpdate() {
        // Create
        ContentValues newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_TITLE, "Alpha-MyTitle");
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://alpha.example.com");
        newValues.put(FeedContract.Entry.COLUMN_NAME_ENTRY_ID, "Alpha-MyEntryID");
        Uri alpha = getMockContentResolver().insert(
                FeedContract.Entry.CONTENT_URI,
                newValues);

        newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_TITLE, "Beta-MyTitle");
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://beta.example.com");
        newValues.put(FeedContract.Entry.COLUMN_NAME_ENTRY_ID, "Beta-MyEntryID");
        Uri beta = getMockContentResolver().insert(
                FeedContract.Entry.CONTENT_URI,
                newValues);

        // Update
        newValues = new ContentValues();
        newValues.put(FeedContract.Entry.COLUMN_NAME_LINK, "http://replaced.example.com");
        getMockContentResolver().update(alpha, newValues, null, null);

        // Retrieve
        String[] projection = {
                FeedContract.Entry.COLUMN_NAME_TITLE,      // 0
                FeedContract.Entry.COLUMN_NAME_LINK,       // 1
                FeedContract.Entry.COLUMN_NAME_ENTRY_ID};  // 2
        // Check that alpha was updated
        Cursor c = getMockContentResolver().query(alpha, projection, null, null, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("Alpha-MyTitle", c.getString(0));
        assertEquals("http://replaced.example.com", c.getString(1));
        assertEquals("Alpha-MyEntryID", c.getString(2));

        // ...and that beta was not
        c = getMockContentResolver().query(beta, projection, null, null, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("Beta-MyTitle", c.getString(0));
        assertEquals("http://beta.example.com", c.getString(1));
        assertEquals("Beta-MyEntryID", c.getString(2));
    }

}
