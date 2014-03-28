package com.example.android_service;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fragments.Fragment1;

public class DrawerLayoutActivity extends FragmentActivity {
    private FragmentManager manager = getSupportFragmentManager();
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	public static final String[] TITLES = { "Henry IV (1)", "Henry V",
			"Henry VIII", "Richard II", "Richard III", "Merchant of Venice",
			"Othello", "King Lear" };

	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_drawerlayout);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);

		initData();
		initLayout();
		Fragment f = new Fragment1();
		Bundle b = new Bundle();
		b.putString("content", "Main Page");
		f.setArguments(b);
		manager.beginTransaction().replace(R.id.frame_container, f).commit();
		}

	protected void initLayout() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawer = (ListView) findViewById(R.id.left_drawer);

		// mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
		// GravityCompat.START);

		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerToggle.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				mDrawerToggle.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				mDrawerToggle.onDrawerStateChanged(newState);
			}

		});
		mDrawer.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, TITLES));
		mDrawer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fragment f = new Fragment1();
				Bundle b = new Bundle();
				b.putString("content", "position-->"+position);
				f.setArguments(b);
				manager.beginTransaction().replace(R.id.frame_container, f).commit();
				 mDrawerLayout.closeDrawer(mDrawer);
			}
		});
		

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close);
		getActionBar().setDisplayUseLogoEnabled(true);
		getActionBar().setTitle("Main");
		getActionBar().setIcon(getResources().getDrawable(R.drawable.ic_launcher));
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up action should open or close the drawer.
		 * mDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_drawlayout, menu);
		return true;
	}
	protected void initData() {

	}

}
