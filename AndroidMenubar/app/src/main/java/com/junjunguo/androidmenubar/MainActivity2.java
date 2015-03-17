package com.junjunguo.androidmenubar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }
    private int points = 0;
    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem score = menu.findItem(R.id.status_button);
        score.setTitle(String.valueOf(points));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_activity1:
                Toast.makeText(this, "item selected! move to Main activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.action_activity2:
                Toast.makeText(this, "item selected! move to activity 2", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity2.class));
                return true;
            case R.id.status_button:
                Toast.makeText(this, "status bar clicked!", Toast.LENGTH_SHORT).show();
                points++;
                invalidateOptionsMenu();
            default:
                Toast.makeText(this, "item selected! default", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }


}
