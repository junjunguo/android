package com.junjunguo.sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.junjunguo.sqlitedb.dao.DAOtextReport;
import com.junjunguo.sqlitedb.model.TextReport;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private DAOtextReport daOtextReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override protected void onResume() {
        super.onResume();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getList());
        ListView listView = (ListView) findViewById(R.id.my_listview);
        listView.setAdapter(adapter);
        addItemClickListener(listView);
    }

    /**
     * get all data entries from database as a string list
     *
     * @return String [] list
     */
    public String[] getList() {
        daOtextReport = new DAOtextReport(getApplicationContext());
        List<TextReport> alist = daOtextReport.getAllTextReports();
        String[] list = new String[alist.size()];
        for (int i = 0; i < alist.size(); i++) {
            list[i] = alist.get(i).toString();
        }
        daOtextReport.close();
        return list;
    }

    /**
     * Toast the clicked item
     *
     * @param listView
     */
    private void addItemClickListener(final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position: " + position + " item: " + itemValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
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
        switch (item.getItemId()) {
            case R.id.menu_write:
                startActivity(new Intent(this, ReportActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
