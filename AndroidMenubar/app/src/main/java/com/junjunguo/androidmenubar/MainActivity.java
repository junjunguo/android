package com.junjunguo.androidmenubar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    //    public Button statusbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
                Toast.makeText(this, "change button color", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "item selected! default", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
