package com.example.android_service;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyWebView extends BaseUIActivity implements OnClickListener {
	private WebView webview;
	private View btn_back, btn_webBack, btn_forward, btn_home, btn_refresh;
	private ProgressBar loading;
	private int scale = 39;
	private TextView tv_title;
	private String homeUrl = "http://www.36kr.com";
	private String currentUrl = "http://www.36kr.com";
 
    private boolean isLoading = false;
	private int steps;
	private List<String> history = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_mywebview);
		initData();
		initLayout();
		steps = 0;
		history.add(homeUrl);
		webview.loadUrl(currentUrl);

	}

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	@Override
	protected void initLayout() {
		webview = (WebView) findViewById(R.id.webView1);

		btn_back = _getView(R.id.back);
		btn_webBack = _getView(R.id.web_back);
		btn_forward = _getView(R.id.web_forward);
		btn_home = _getView(R.id.web_home);
		btn_refresh = _getView(R.id.web_refresh);
		tv_title = (TextView) _getView(R.id.web_title);
		loading = (ProgressBar) _getView(R.id.web_loading);

		btn_back.setOnClickListener(this);
		btn_webBack.setOnClickListener(this);
		btn_forward.setOnClickListener(this);
		btn_home.setOnClickListener(this);
		btn_refresh.setOnClickListener(this);

		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setAllowFileAccess(true);
		webSettings.setLoadsImagesAutomatically(true); // 自动加载图片
		webSettings.setBuiltInZoomControls(true);
		webSettings.setUseWideViewPort(true);

		webview.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
		webview.setWebViewClient(new MyWebViewClient());
		webview.setInitialScale(57);

	}

	@Override
	protected void initData() {

	}

	final class MyWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			//
			int size = history.size() - 1;
			for (int i = size; i > steps; i--) {
				history.remove(i);
			}
			history.add(url);
			steps = history.size() - 1;
			Log.i("debug", "shouldOverrideUrlLoading  stops=" + steps
					+ " size=" + history.size());
			btn_forward
			.setBackgroundResource(R.drawable.ic_btn_web_forward_unclick);
			isLoading = true;
			view.loadUrl(url);
			
			return true;
		}

		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			Log.d("debug", "onPageStarted");
		
			loading.setVisibility(View.VISIBLE);
			btn_refresh.setVisibility(View.GONE);
		}

		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			// Log.d("debug", "onPageFinished " + url);
			// view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
			// +
			// "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

			// Log.d("debug", "onPageFinished ");
			Log.i("debug", "onPageFinished  stops=" + steps + " size="
					+ history.size());

			if (steps > 0) {
				btn_webBack.setBackgroundResource(R.drawable.ic_btn_web_back);

			} else {
				btn_webBack
						.setBackgroundResource(R.drawable.ic_btn_web_back_unclick);
			}
			loading.setVisibility(View.GONE);
			btn_refresh.setVisibility(View.VISIBLE);
			isLoading = false; 
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			toast("页面加载失败！");

			loading.setVisibility(View.GONE);
			btn_refresh.setVisibility(View.VISIBLE);
		}

		@Override
		public void onScaleChanged(WebView view, float oldScale, float newScale) {
			super.onScaleChanged(view, oldScale, newScale);
			// Log.i("debug", oldScale + "->" + newScale);
		}

	}

	class MyWebChromeClient extends WebChromeClient {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {

		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			// tv_title.setText(title);
		}

	}

	final class InJavaScriptLocalObj {
		public void showSource(String html) {
			Log.i("debug", html);
		}
	}

	 
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			closeActivity();
			break;
		case R.id.web_back:
			if(isLoading)return;
			if (steps > 0) {
				// 非首页
				steps--;
				//webview.loadUrl(history.get(steps));
				
				webview.goBack();
				if (steps == 0) { // back to home page
					btn_webBack
							.setBackgroundResource(R.drawable.ic_btn_web_back_unclick);
				} else {
					btn_webBack
							.setBackgroundResource(R.drawable.ic_btn_web_back);
				}
				if (steps < history.size() - 1)
					btn_forward
							.setBackgroundResource(R.drawable.ic_btn_web_forward);
				else {
					btn_forward
							.setBackgroundResource(R.drawable.ic_btn_web_forward_unclick);
				}
			}

			break;
		case R.id.web_forward:
			if(isLoading)return;
			if (steps < history.size() - 1) {
                 //非末页
				steps++;
				// webview.goBackOrForward(steps);
				webview.loadUrl(history.get(steps));
				if (steps == history.size() - 1) {
					btn_forward
							.setBackgroundResource(R.drawable.ic_btn_web_forward_unclick);
				} else {
					btn_forward
							.setBackgroundResource(R.drawable.ic_btn_web_forward);
				}
			}
			break;

		case R.id.web_home:
			if(isLoading)return;
			history = null;
			history = new ArrayList<String>();
			history.add(homeUrl);
			steps = 0;
			btn_webBack
					.setBackgroundResource(R.drawable.ic_btn_web_back_unclick);
			btn_forward
					.setBackgroundResource(R.drawable.ic_btn_web_forward_unclick);
			webview.loadUrl(homeUrl);
			break;
		case R.id.web_refresh:
			if(isLoading)return;
			webview.reload();
		default:
			break;
		}
	}
}
