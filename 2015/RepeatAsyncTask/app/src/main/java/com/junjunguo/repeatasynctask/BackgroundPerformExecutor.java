package com.junjunguo.repeatasynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.Executor;

/**
 * This file is part of RepeatAsyncTask
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 09/03/15.
 */
public class BackgroundPerformExecutor implements Executor {
    private Context context;

    public BackgroundPerformExecutor(Context context) {
        this.context = context;
    }

    @Override public void execute(Runnable command) {
        if (isOnline()) {
            command.run();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
