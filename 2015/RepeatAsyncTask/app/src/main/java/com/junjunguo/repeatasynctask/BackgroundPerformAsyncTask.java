package com.junjunguo.repeatasynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

/**
 * This file is part of RepeatAsyncTask
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 09/03/15.
 * <p/>
 */
public class BackgroundPerformAsyncTask extends AsyncTask {
    private Context context;

    public BackgroundPerformAsyncTask(Context context) {
        this.context = context;
    }

    @Override protected Object doInBackground(Object[] params) {
        //run tasks her ...
        if (isOnline()) {
            Log.i("Async", "-------> Text from BackgroundPerformAsyncTask");

        }
        return null;
    }

    /**
     * @return true if internet available
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
