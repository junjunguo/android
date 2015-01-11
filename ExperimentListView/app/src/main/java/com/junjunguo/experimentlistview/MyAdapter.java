package com.junjunguo.experimentlistview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GuoJunjun on 11.01.15.
 */
public class MyAdapter extends ArrayAdapter {
    private final String TAG_LOG =
            MainActivity.class.getSimpleName().toString() + ": " + MyAdapter.class.getSimpleName().toString();

    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout2, values);
        Log.i(TAG_LOG, "my adapter called");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        //        return super.getView(position, convertView, parent);
        Log.i(TAG_LOG, "call getView ");
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View theView = layoutInflater.inflate(R.layout.row_layout2, parent, false);

        String tvShow = getItem(position).toString();

        TextView textView = (TextView) theView.findViewById(R.id.textView1);
        textView.setText(tvShow);
        ImageView theImageView = (ImageView) theView.findViewById(R.id.image_view1);
        theImageView.setImageResource(R.drawable.dot);
        if (position % 2 == 0) {
            theView.setBackgroundColor(Color.argb(10, 0, 0, 255));
        } else {
            theView.setBackgroundColor(Color.argb(10, 0, 255, 0));
        }
        return theView;
    }


}
