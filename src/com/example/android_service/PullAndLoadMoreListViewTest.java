package com.example.android_service;

import java.util.Arrays;
import java.util.LinkedList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.widget.MyPullToRefrushListView;
import com.example.widget.MyPullToRefrushListView.OnRefreshListener;

public class PullAndLoadMoreListViewTest extends BaseUIActivity {
	// list with the data to show in the listview
	private LinkedList<String> mListItems;
	private MyPullToRefrushListView xlv;

	// The data to be displayed in the ListView
	private String[] mNames = { "Fabian", "Carlos", "Alex", "Andrea", "Karla",
			"Freddy", "Lazaro", "Hector", "Carolina", "Edwin", "Jhon",
			"Edelmira", "Andres" };

	// The data to be displayed in the ListView
	private String[] mAnimals = { "Perro", "Gato", "Oveja", "Elefante", "Pez",
			"Nicuro", "Bocachico", "Chucha", "Curie", "Raton", "Aguila",
			"Leon", "Jirafa" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_pullandloadlv);
		xlv = (MyPullToRefrushListView) findViewById(R.id.xlistview);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mNames));
		mListItems.addAll(Arrays.asList(mAnimals));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_lv, mListItems);
		xlv.setAdapter(adapter);
        xlv.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				new Refresh().execute();
				
			}
		});
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {

	}

	class Refresh extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			toast("刷新完毕");
			xlv.onRefreshComplete();
		}

	}

}
