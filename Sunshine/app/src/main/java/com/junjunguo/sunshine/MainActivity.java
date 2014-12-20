package com.junjunguo.sunshine;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     * 1<p/>
     * a fragment is a modular container with activity
     * <p/>
     * fragment_main: res/layout/fragment_main.xml
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            List<String> alist = new ArrayList<>();
            alist.add("Today - Sunny - 88/63");
            alist.add("Tomorrow - Sunny - 88/63");
            alist.add("Weds - Sunny - 88/63");
            alist.add("Thurs - Sunny - 88/63");
            alist.add("Fri - Sunny - 88/63");
            alist.add("Sat - Sunny - 88/63");

            /*
             *  ArrayAdapter<String> : 
             *  Parameters:
             *      Context:
             *              contained global information about app environment*
             *      ID of list item layout
             *              *
             *      ID of text view
             *      list of data
             */
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    // the current context
                    getActivity(),
                    // id of list item layout
                    R.layout.list_item_forecast, 
                    // id of the textview to populate
                    R.id.list_item_forecast_textview, 
                    // data
                    alist);
            // get a reference to the ListView, and attach this adapter to listview
            ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(arrayAdapter);
            
                    
            return rootView;
        }
    }
}
