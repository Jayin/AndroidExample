package com.example.android_service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.widget.MyRoundPic;

public class Bitmap1Canvas extends BaseUIActivity {
	private View mypic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.myroundpic);
		setContentView(new MyRoundPic(getContext(), b));
		 
		 
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {

	}

}
