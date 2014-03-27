package com.example.android_service;


import java.lang.reflect.Field;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class ActionBarTest extends BaseUIActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_actionbartest);
		try {  
		    ViewConfiguration config = ViewConfiguration.get(this);                                
		    Field menuKeyField = ViewConfiguration.class.getDeclaredField("HasPermanentMenuKey");  
		    if(menuKeyField != null) {  
		        menuKeyField.setAccessible(true);  
		        menuKeyField.setBoolean(config, false);  
		    }  
		} catch (Exception ex) {  
		    // Ignore  
		}  
		initData();
		initLayout();
	}

	@Override
	protected void initLayout() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_actionbar, menu);
		Spinner sp = (Spinner) MenuItemCompat.getActionView(menu
				.findItem(R.id.d2));
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(mSpinnerAdapter);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		toast("You selected:" + item.getItemId());
		if (item.getItemId() == android.R.id.home) {
			closeActivity();
		} else {
			openActivity(ActionBarTest.class);
		}

		return true;
	}

	@Override
	protected void initData() {
		ActionBar actionbar = getActionBar();
		actionbar.setTitle("Main");
		actionbar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_bg_tweet));
	    
	}
}
