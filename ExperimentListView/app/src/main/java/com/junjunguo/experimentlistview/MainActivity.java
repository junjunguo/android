package com.junjunguo.experimentlistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private String TAG_LOG = MainActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG_LOG, "onCreate called !");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] favoriteTVShows =
                {"Pushing Daisies", "Better Off Ted", "Twin Peaks", "Freaks and Geeks", "Orphan Black", "Walking dead",
                        "Breaking Bad", "Life on Mars", "The house of cards", "The Game of Thrones"};

        //                final ListAdapter theAdapter = new ArrayAdapter<String>(this, 
        // android.R.layout.simple_list_item_1,
        //        favoriteTVShows);
        // use customer layout : row_layout 
        //        final ListAdapter theAdapter = new ArrayAdapter<>(this, R.layout.row_layout, favoriteTVShows);
        final ListAdapter theAdapter = new MyAdapter(this, favoriteTVShows);

        ListView theListView = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tvShowPicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();
                Log.i(TAG_LOG, "on item click listener");
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG_LOG, "onStart called");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG_LOG, "onResume called");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG_LOG, "onPause called");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG_LOG, "onStop called");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG_LOG, "onDestroy called");
    }
}
