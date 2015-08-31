package com.junjunguo.digtalclock;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MyDigitalClock extends AppCompatActivity {
    private Button startButton;
    private Button pauseButton;

    private TextView timerFirst;
    private TextView timerSecond;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_clock);
        TextClock clock = (TextClock) findViewById(R.id.digital_clock);
        clock.setTextColor(getResources().getColor(R.color.my_primary_dark));
        clock.setFormat24Hour("hh:mm:ss");
        init();
    }

    private void init() {

        timerFirst = (TextView) findViewById(R.id.my_timer);
        timerSecond = (TextView) findViewById(R.id.my_timer_second);

        startButton = (Button) findViewById(R.id.startbtn);

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });

        pauseButton = (Button) findViewById(R.id.pausebtn);

        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int hours = mins / 60;
            mins = mins % 60;

            int milliseconds = (int) (updatedTime % 1000);

            timerFirst
                    .setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));


            timerSecond.setText("" + String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" +
                    String.format("%02d", secs));

            customHandler.postDelayed(this, 0);
        }

    };


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_digital_clock, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
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
