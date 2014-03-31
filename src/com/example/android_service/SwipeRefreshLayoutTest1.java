package com.example.android_service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class SwipeRefreshLayoutTest1 extends Activity implements
		OnRefreshListener {
	SwipeRefreshLayout swipeRefresh;
	WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_swiprefreshlayout);
		swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiprefreshlayout);
		swipeRefresh.setOnRefreshListener(this);
		swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("http://www.baidu.com/");
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					swipeRefresh.setRefreshing(false);
				}
			}
		});

	}

	@Override
	public void onRefresh() {
		webview.loadUrl("http://www.baidu.com/");
	}
}
