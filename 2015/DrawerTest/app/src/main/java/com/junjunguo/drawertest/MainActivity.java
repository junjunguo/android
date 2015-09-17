package com.junjunguo.drawertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout inclusionViewGroup = (DrawerLayout) findViewById(R.id.custom_map_view_layout);

        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_map_content, null);
        inclusionViewGroup.addView(inflate);
//        inclusionViewGroup.getParent().bringChildToFront(inclusionViewGroup);

        setDrawer(inclusionViewGroup);

    }

    private void setDrawer(final DrawerLayout drawer) {
        final String[] data = {"one", "two", "three"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        //        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.custom_map_view_layout);
        final ListView navList = (ListView) findViewById(R.id.left_drawer);
        navList.setAdapter(adapter);

        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override public void onDrawerOpened(View drawerView) {

                    }

                    @Override public void onDrawerClosed(View drawerView) {

                    }

                    @Override public void onDrawerStateChanged(int newState) {

                    }
                });
                drawer.closeDrawer(navList);
            }
        });

    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
