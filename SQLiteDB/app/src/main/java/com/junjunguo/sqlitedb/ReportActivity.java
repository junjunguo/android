package com.junjunguo.sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.junjunguo.sqlitedb.dao.DAOtextReport;
import com.junjunguo.sqlitedb.model.TextReport;


public class ReportActivity extends ActionBarActivity {
    private EditText textReport;
    private DAOtextReport daOtextReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        textReport = (EditText) findViewById(R.id.report_edit_text);
        daOtextReport = new DAOtextReport(this);
    }

    /**
     * user click send report button
     *
     * @param view
     */
    public void textReportOnClicked(View view) {
        String report = textReport.getText().toString();
        long result = daOtextReport.addReport(new TextReport("junjunguo.com", report, 63.4187362, 10.4387621));

        if (result >= 0) {
            Toast.makeText(getApplicationContext(), "succsefully add report to row " + result, Toast.LENGTH_SHORT)
                    .show();
            textReport.getText().clear();
        } else {
            Toast.makeText(getApplicationContext(), "not succeed ! please try again !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_view:
                daOtextReport.close();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
