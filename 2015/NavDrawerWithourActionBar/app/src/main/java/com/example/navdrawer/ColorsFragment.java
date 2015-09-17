package com.example.navdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class ColorsFragment extends Fragment{
	
	private int[] colors;
	private int position;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootview=inflater.inflate(R.layout.fragment_layout, container,false);
		position=getArguments().getInt("Position");
		Toast.makeText(getActivity(), position+"", 4).show();
		RelativeLayout layout=(RelativeLayout)rootview.findViewById(R.id.layout);
		TextView textView=(TextView)rootview.findViewById(R.id.textview);
		colors=getActivity().getResources().getIntArray(R.array.colors);
		textView.setText(getResources().getStringArray(R.array.colors_array)[position]);
		layout.setBackgroundColor(colors[position]);
	return rootview;
	}
}
