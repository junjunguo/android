package com.junjunguo.resumedownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This downloadedFile is part of ResumeDownload
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on September 14, 2015.
 */
public class ResumableDownloader {
    /**
     * last modified time for file
     */
    private String lastModified;

    private int timeout;
    private File downloadedFile;
    private boolean startNewDownload;
    /**
     * total length of the file
     */
    private long fileLength = 0;

    // CONSTANT
    public static final int DOWNLOADING = 0;
    public static final int COMPLETE = 1;
    public static final int PAUSE = 2;
    public static final int ERROR = 3;
    public static final int BUFFER_SIZE = 8 * 1024;
    /**
     * downloading status: downloading; complete; pause; error;
     */
    private int status;
    private String[] statuses;

    public ResumableDownloader(String lastModified, int status) {
        this.lastModified = lastModified;
        timeout = 9000;
        startNewDownload = true;
        this.status = status;
        statuses = new String[]{"Downloading", "Complete", "Pause", "Error"};
    }

    /**
     * @param urlStr           downloadFile url
     * @param toFile           downloadedFile path
     * @param downloadListener downloadFile progress listener
     * @throws IOException
     */
    public void downloadFile(String urlStr, String toFile, DownloadListener downloadListener) throws IOException {
        prepareDownload(urlStr, toFile, downloadListener);
        HttpURLConnection connection = createConnection(urlStr, downloadListener);
        setStatus(DOWNLOADING);
        if (!startNewDownload) {connection.setRequestProperty("Range", "bytes=" + downloadedFile.length() + "-");}
        downloadListener.progressUpdate(new Message("ResponseCode: " + connection.getResponseCode() + "; file length:" +
                fileLength + "\nResponseMessage: " + connection.getResponseMessage()));
        InputStream in = new BufferedInputStream(connection.getInputStream(), BUFFER_SIZE);
        FileOutputStream writer;
        long progressLength = 0;
        if (!startNewDownload) {
            progressLength += downloadedFile.length();
            downloadListener.progressUpdate(new Message("resume download to: " + toFile));
            // append to exist downloadedFile
            writer = new FileOutputStream(toFile, true);
        } else {
            downloadListener.progressUpdate(new Message("new download to: " + toFile));
            writer = new FileOutputStream(toFile);
            // save remote last modified data to local
            lastModified = connection.getHeaderField("Last-Modified");
        }
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while (getStatus() == DOWNLOADING && (count = in.read(buffer)) != -1) {
                progressLength += count;
                writer.write(buffer, 0, count);
                // progress....
                downloadListener.progressUpdate(new Message((int) (progressLength * 100 / fileLength)));
                if (progressLength == fileLength) {
                    progressLength = 0;
                    setStatus(COMPLETE);
                }
            }
        } finally {
            writer.close();
            in.close();
            connection.disconnect();
        }
    }

    /**
     * rend a request to server & decide to start a new download or not
     *
     * @param urlStr           string url
     * @param toFile           to file path
     * @param downloadListener
     * @throws IOException
     */
    private void prepareDownload(String urlStr, String toFile, DownloadListener downloadListener) throws IOException {
        downloadListener.progressUpdate(new Message("prepare download ..........."));
        HttpURLConnection conn = createConnection(urlStr, downloadListener);
        downloadedFile = new File(toFile);
        String remoteLastModified = conn.getHeaderField("Last-Modified");
        fileLength = conn.getContentLength();

        startNewDownload = (!downloadedFile.exists() || downloadedFile.length() >= fileLength ||
                !remoteLastModified.equalsIgnoreCase(lastModified));

        conn.disconnect();
        downloadListener
                .progressUpdate(new Message("prepare finished .... start a new Download = " + startNewDownload));
    }

    /**
     * @param urlStr           url string
     * @param downloadListener
     * @return An URLConnection for HTTP
     * @throws IOException
     */
    private HttpURLConnection createConnection(String urlStr, DownloadListener downloadListener) throws IOException {
        downloadListener.progressUpdate(new Message("create new connection ...."));
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open connection to URL.
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setReadTimeout(timeout);
        conn.setConnectTimeout(timeout);
        return conn;
    }

    /**
     * @return status as a String
     */
    public String getStatusStr() {
        return statuses[getStatus()];
    }

    /**
     * @return status corresponding number
     */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastModified() {
        return lastModified;
    }

}
