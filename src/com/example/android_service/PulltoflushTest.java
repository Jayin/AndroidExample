package com.example.android_service;

import java.util.Arrays;

import android.annotation.SuppressLint;
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
import com.example.widget.MyPullToRefrushListView;

public class PulltoflushTest extends Activity {
	private MyPullToRefrushListView lv;

	private View foot;

	private String[] mStrings = { "Abbaye de Belloc",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler","Abbaye de Belloc",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler" };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_pulltoreflush);
		lv = (MyPullToRefrushListView) findViewById(R.id.pulltoreflush);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_lv);
		adapter.addAll(Arrays.asList(mStrings));
		lv.setAdapter(adapter);

		lv.setOnRefreshListener(new MyPullToRefrushListView.OnRefreshListener() {

			@Override
			public void onRefresh() {
				new Task().execute();
			}
		});

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
