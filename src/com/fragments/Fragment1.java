package com.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_service.R;

public class Fragment1 extends Fragment {
	private TextView tv;
	private String content;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Bundle b = getArguments();
		content = b.getString("content") == null ? "welcome!" : b.getString("content");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frgm_fragement1, container, false);
		tv = (TextView) v.findViewById(R.id.textView1);
		return v;

	}

	@Override
	public void onResume() {
		super.onResume();
		tv.setText(content);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
