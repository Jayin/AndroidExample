package com.example.android_service;

import android.os.Bundle;

public class ActionbarTest2 extends BaseUIActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_actionbartest);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void initLayout() {
		
	}

	@Override
	protected void initData() {
		
	}
}
