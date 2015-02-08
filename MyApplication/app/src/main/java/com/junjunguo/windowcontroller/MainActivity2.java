package com.junjunguo.windowcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        final Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRAMESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);

        // use on click listener
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //                You opened the new activity from another activity with startActivityForResult. In 
                // that case you can just call the finishActivity() function from your code and it'll take you back 
                // to the previous activity.
//                finishAffinity();
                finish();

                //Keep track of the activity stack. Whenever you start a new activity with an intent you can specify 
                // an intent flag like FLAG_ACTIVITY_REORDER_TO_FRONT or FLAG_ACTIVITY_PREVIOUS_IS_TOP.
//                intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
