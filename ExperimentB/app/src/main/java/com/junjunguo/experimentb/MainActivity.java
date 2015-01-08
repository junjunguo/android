package com.junjunguo.experimentb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "onCreate called !");

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("text view ! ");
        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(LOG_TAG, editText.getText().toString());
                Log.i(LOG_TAG, "s: " + s + " start: " + start + " before: " + before + " count: " + count);
                textView.setText(editText.getText().toString());
            }

            @Override public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Log.i(LOG_TAG,editText.getText().toString().toUpperCase() );
                textView.setText(editText.getText().toString().toUpperCase());
                textView.setTextColor(Color.rgb(0, 0, 255));
//                textView.setTextSize(R.dimen.text_size);
                textView.setBackgroundColor(Color.argb(111,111,111,111));
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart called!");
    }

    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume called !");
    }

    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause called !");
    }

    protected void onStop() {
        super.onStart();
        Log.i(LOG_TAG, "onStop called !");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy called !");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.i(LOG_TAG, "onCreateOptionsMenu called!");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG, "onOptionsItemSelected called !");
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
