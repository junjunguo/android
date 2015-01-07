package com.junjunguo.experimenta;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate(Bundle) initialize activity

        super.onCreate(savedInstanceState);

        //call setContenView(int) with a layout resource defining UI
        setContentView(R.layout.activity_main);
    }

    public void onBtnTouch(View v) {
        // the the ids are in R.id here R.id.textView
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Hello, this is a new world !");
    }

    public void onBtnTouchS(View v) {
        TextView tvs = (TextView) findViewById(R.id.textView2);
        tvs.setText("hello, this is a small button !");
    }

    public void onImgBtnTouch() {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setBackgroundColor(Color.rgb(0, 0, 255));

    }
}
