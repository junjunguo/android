package com.example.android.network.sync.basicsyncadapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.RemoteException;
import android.test.ServiceTestCase;

import com.example.android.network.sync.basicsyncadapter.provider.FeedContract;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class SyncAdapterTest extends ServiceTestCase<SyncService> {
    public SyncAdapterTest() {
        super(SyncService.class);
    }

    public void testIncomingFeedParsed()
            throws IOException, XmlPullParserException, RemoteException,
            OperationApplicationException, ParseException {
        String sampleFeed = "<?xml version=\"1.0\"?>\n" +
                "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
                " \n" +
                "  <title>Sample Blog</title>\n" +
                "  <link href=\"http://example.com/\"/>\n" +
                "  <link type=\"application/atom+xml\" rel=\"self\" href=\"http://example.xom/feed.xml\"/>\n" +
                "  <updated>2013-05-16T16:53:23-07:00</updated>\n" +
                "  <id>http://example.com/</id>\n" +
                "  <author>\n" +
                "    <name>Rick Deckard</name>\n" +
                "    <email>deckard@example.com</email>\n" +
                "  </author>\n" +
                "\n" +
                "  <entry>\n" +
                "    <id>http://example.com/2012/10/20/test-post</id>\n" +
                "    <link type=\"text/html\" rel=\"alternate\" href=\"http://example.com/2012/10/20/test-post.html\"/>\n" +
                "    <title>Test Post #1</title>\n" +
                "    <published>2012-10-20T00:00:00-07:00</published>\n" +
                "    <updated>2012-10-20T00:00:00-07:00</updated>\n" +
                "    <author>\n" +
                "      <name>Rick Deckard</name>\n" +
                "      <uri>http://example.com/</uri>\n" +
                "    </author>\n" +
                "    <summary>This is a sample summary.</summary>\n" +
                "    <content type=\"html\">Here's some <em>sample</em> content.</content>\n" +
                "  </entry>\n" +
                "</feed>\n";
        InputStream stream = new ByteArrayInputStream(sampleFeed.getBytes());
        SyncAdapter adapter = new SyncAdapter(getContext(), false);
        adapter.updateLocalFeedData(stream, new SyncResult());

        Context ctx = getContext();
        assert ctx != null;
        ContentResolver cr = ctx.getContentResolver();
        final String[] projection = {FeedContract.Entry.COLUMN_NAME_ENTRY_ID,
                FeedContract.Entry.COLUMN_NAME_TITLE,
                FeedContract.Entry.COLUMN_NAME_LINK};
        Cursor c = cr.query(FeedContract.Entry.CONTENT_URI, projection, null, null, null);
        assert c != null;
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals("http://example.com/2012/10/20/test-post", c.getString(0));
        assertEquals("Test Post #1", c.getString(1));
        assertEquals("http://example.com/2012/10/20/test-post.html", c.getString(2));
    }
}
