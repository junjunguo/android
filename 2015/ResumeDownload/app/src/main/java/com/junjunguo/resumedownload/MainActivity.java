package com.junjunguo.resumedownload;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class MainActivity extends Activity {
    private TextView percentage, message;
    private ProgressBar progressBar;
    private EditText url;
    private Button start, pause;
    private AsyncTask asyncTask;
    private String urlStr = "http://folk.ntnu.no/junjung/pocketmaps/maps/europe_denmark.ghz";
    //    private String urlStr = "https://farm4.staticflickr.com/3859/14684791333_84e17ac79a_o_d.jpg";
    private String filename;
    private String fileExtension;
    // constant
    public final String PREFS_NAME = "MyResumableDownloadPrefsFile";
    public final String PREFS_KEY_PROGRESS = "Progress";
    public final String PREFS_KEY_LASTMODIFIED = "LastModified";

    private File fileDir;
    private ResumableDownloader mDownloader;

    private boolean asytaskFinished = true;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "resumedownload");

        initView();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        mDownloader = new ResumableDownloader(settings.getString(PREFS_KEY_LASTMODIFIED, ""), mDownloader.PAUSE);
        int progress = settings.getInt(PREFS_KEY_PROGRESS, 0);
        progressBar.setProgress(progress);
        percentage.setText(String.format("%1$" + 3 + "s", progress) + "%");
    }

    private void initView() {
        percentage = (TextView) findViewById(R.id.progress_percentage);
        message = (TextView) findViewById(R.id.message);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        url = (EditText) findViewById(R.id.download_url);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);

        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setIndeterminate(false);

        url.setText(urlStr);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message.setText("status: " + mDownloader.getStatusStr());
                if (asytaskFinished && mDownloader.getStatus() != mDownloader.DOWNLOADING) {
                    initUrl();
                    startDownload();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message.setText("is cancelled: " + asyncTask.isCancelled());
                if (mDownloader.getStatus() == mDownloader.DOWNLOADING) {
                    mDownloader.setStatus(mDownloader.PAUSE);
                    asyncTask.cancel(true);
                    message.setText("paused & asyncTask is cancelled: " + asyncTask.isCancelled());
                }
            }
        });
    }

    private void initUrl() {
        urlStr = url.getText().toString();
        String file = urlStr.substring(urlStr.lastIndexOf("/") + 1);
        fileExtension = file.substring(file.lastIndexOf("."));
        filename = file.substring(0, file.lastIndexOf("."));
        message.setText("file extension: " + fileExtension + "; file name: " + filename);
    }

    private void startDownload() {
        asytaskFinished = false;
        asyncTask = new AsyncTask<URL, Integer, ResumableDownloader>() {
            protected ResumableDownloader doInBackground(URL... params) {
                try {
                    if (!fileDir.exists()) { fileDir.mkdirs();}
                    mDownloader.downloadFile(urlStr,
                            (new File(fileDir.getAbsolutePath(), filename + fileExtension)).getAbsolutePath(),
                            new DownloadListener() {
                                public void progressUpdate(Integer value) {
                                    publishProgress(value);
                                }
                            });
                } catch (FileNotFoundException e) {
                    log("File not found !");
                } catch (IOException e) {
                    e.printStackTrace();
                    mDownloader.setStatus(mDownloader.ERROR);
                }
                return mDownloader;
            }

            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                percentage.setText(String.format("%1$" + 3 + "s", values[0]) + "%");
                progressBar.setProgress(values[0]);
            }

            protected void onPostExecute(ResumableDownloader o) {
                super.onPostExecute(o);
                message.setText("Async task finished.");
                asytaskFinished = true;
            }

            protected void onCancelled() {
                super.onCancelled();
                message.setText("async Task is cancelled: " + asyncTask.isCancelled());
                asytaskFinished = true;
            }
        }.execute();
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_KEY_LASTMODIFIED, mDownloader.getLastModified());
        editor.putInt(PREFS_KEY_PROGRESS, progressBar.getProgress());
        editor.commit();
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "----" + s);
    }
}
