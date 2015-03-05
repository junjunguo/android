package com.junjunguo.simplesqlitedb;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;


public class MainActivity extends ListActivity {

    private ReportDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new ReportDataSource(this);
        datasource.open();

        List<TextReport> values = datasource.getAllTextReports();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<TextReport> adapter = new ArrayAdapter<TextReport>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<TextReport> adapter = (ArrayAdapter<TextReport>) getListAdapter();
        TextReport textReport = null;
        switch (view.getId()) {
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                textReport = datasource.createTextReport(comments[nextInt]);
                adapter.add(textReport);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    textReport = (TextReport) getListAdapter().getItem(0);
                    datasource.deleteTextReport(textReport);
                    adapter.remove(textReport);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
