package com.example.android_service;

import java.util.Arrays;
import java.util.LinkedList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.custom.widget.PullAndLoadListView;
import com.custom.widget.PullAndLoadListView.OnLoadMoreListener;
import com.custom.widget.PullToRefreshListView.OnRefreshListener;
import com.utils.AndroidUtils;

public class PullAndLoadMoreListViewTest extends BaseUIActivity {
	// list with the data to show in the listview
	private LinkedList<String> mListItems;
	private PullAndLoadListView xlv;
	private ArrayAdapter<String> adapter;

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
		xlv = (PullAndLoadListView) findViewById(R.id.xlistview);

		mListItems = new LinkedList<String>();
	//	mListItems.addAll(Arrays.asList(mNames));
		// mListItems.addAll(Arrays.asList(mAnimals));

		adapter = new ArrayAdapter<String>(this, R.layout.item_lv, mListItems);
		xlv.setAdapter(adapter);
		xlv.setOnRefreshListener(new OnRefreshListener() {

			@Override
 			public void onRefresh() {
//				toast("onRefresh");

				new Refresh().execute();
				
			}
		});

		xlv.setOnLoadMoreListener(new OnLoadMoreListener() {

			public void onLoadMore() {

				new Load().execute();
			}
		});

		xlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				toast("you click:"+position);
				
			}
		});
		xlv.toRefresh();
		

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
			 	mListItems.addAll(Arrays.asList(mAnimals));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			toast("刷新完毕");
			//adapter.notifyDataSetChanged();
			
			xlv.onRefreshComplete();
			xlv.setCanRefresh(false,"没有更多！");
		}

	}

	class Load extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
				
				if (!AndroidUtils.isNetworkConnected(getContext()))
					throw new Exception("网络异常！");
				else{
					mListItems.addAll(Arrays.asList(mAnimals));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				toast("Load完毕");
				adapter.notifyDataSetChanged();
				xlv.onLoadMoreComplete();
			} else {
				toast("网络不给力啊");
				xlv.onLoadMoreComplete();
			}

		}

	}

}
