package com.example.android_service;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android_service.PullToRefreshListView.OnRefreshListener;

public class PulltoflushTest extends Activity {
	private PullToRefreshListView lv;

	private View foot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_pulltoreflush);
		lv = (PullToRefreshListView) findViewById(R.id.pulltoreflush);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_lv);
//		for (int i = 1; i < 100; i++) {
//			adapter.add(i + "");
//
//		}
		lv.setAdapter(adapter);
		lv.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

				new Task().execute();
			}
		});
		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
		View view = LayoutInflater.from(this).inflate(R.layout.foot, null);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(PulltoflushTest.this, "your sister!", 1).show();

			}

		});
		view.findViewById(R.id.foot_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						lv.clickRefresh();
//                       
//						for (int i = 100; i < 200; i++) {
//							adapter.add(i + "");
//						}
//						adapter.notifyDataSetChanged();
					}
				});
 	  	lv.addFooterView(view, null, true);
 	    
		
//		lv.setOnScrollListener(new OnScrollListener() {
//			
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				lv.onScrollStateChanged(view, scrollState);
//				//无数据
//				if(lv.getAdapter().getCount() ==0)return;
//				boolean isScrollEnd = false;
//				try{
//					 if(view.getPositionForView(view)==lv.getLastVisiblePosition()){
//						 isScrollEnd = true; 
//					 }
//				}catch(Exception e){
//					isScrollEnd = false;
//				}
//				
//			}
//			
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem,
//					int visibleItemCount, int totalItemCount) {
//				 
//				
//			}
//		});
	  	
	}

	class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(PulltoflushTest.this, "test!!", 1).show();

			lv.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

}
