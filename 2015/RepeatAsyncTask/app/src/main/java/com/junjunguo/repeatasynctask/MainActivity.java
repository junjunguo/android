package com.junjunguo.repeatasynctask;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);
        editText = (EditText) findViewById(R.id.edit_text);
        startBackgroundPerform();
        startBackgroundPerformExecutor();
    }

    private Timer timerExecutor = new Timer();
    private TimerTask doAsynchronousTaskExecutor;

    public void startBackgroundPerformExecutor() {
        final Handler handler = new Handler();
        doAsynchronousTaskExecutor = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            BackgroundPerformExecutor performBackgroundTask =
                                    new BackgroundPerformExecutor(
                                            getApplicationContext());
                            performBackgroundTask.execute(new Runnable() {
                                @Override public void run() {
                                    Log.i("Executor Perform",
                                            "<~~~~~~~~~ Text from Background " +
                                                    "Perform");
                                    textView.setText(
                                            "Text from Background Perform " +
                                                    "Executor");
                                    textView.setBackgroundColor(Color.RED);
                                    editText.setText(
                                            "Text from Background Perform " +
                                                    "Executor");
                                    editText.setBackgroundColor(Color.MAGENTA);
                                    Toast.makeText(getApplicationContext(),
                                            "Text from Background Perform " +
                                                    "Executor",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timerExecutor.schedule(doAsynchronousTaskExecutor, 2000, 4000);
    }

    public void stopExecutorClicked(View view) {
        Toast.makeText(this, "stop Executor clicked !", Toast.LENGTH_SHORT)
                .show();
        doAsynchronousTaskExecutor.cancel();
        timerExecutor.cancel();
    }

    private Timer timerAsync;
    private TimerTask timerTaskAsync;

    public void startBackgroundPerform() {
        final Handler handler = new Handler();
        timerAsync = new Timer();
        timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        Log.i("Background Perform",
                                "-------> Text from Background Perform");
                        textView.setText("Text from Background Perform");
                        textView.setBackgroundColor(Color.YELLOW);
                        editText.setText("Text from Background Perform");
                        editText.setBackgroundColor(Color.CYAN);
                    }
                });
                //                handler.post(new Runnable() {
                //                    public void run() {
                //                        try {
                //                            BackgroundPerformAsyncTask
                // performBackgroundTask =
                //                                    new
                // BackgroundPerformAsyncTask(
                //
                // getApplicationContext());
                //                            performBackgroundTask.execute();
                //                        } catch (Exception e) {
                //                            e.printStackTrace();
                //                        }
                //                    }
                //                                });
            }
        };
        timerAsync.schedule(timerTaskAsync, 0, 4000);
    }

    public void stopAsyntaskClicked(View view) {
        Toast.makeText(this, "stop asyn task clicked !", Toast.LENGTH_SHORT)
                .show();
        timerAsync.cancel();
        timerTaskAsync.cancel();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        doAsynchronousTaskExecutor.cancel();
        timerExecutor.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
