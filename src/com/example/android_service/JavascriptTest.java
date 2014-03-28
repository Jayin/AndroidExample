package com.example.android_service;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class JavascriptTest extends BaseUIActivity {
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_javascript);
		initData();
		initLayout();

	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {
		webview  =(WebView) _getView(R.id.webView1);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.addJavascriptInterface(new JsObject(), "android");
		webview.loadUrl("file:///android_asset/test.html");
	}

	class JsObject {
		@JavascriptInterface
		public void getHtml(String content) {
			Log.i("debug", "getHtml-->" + content);
		}

		@JavascriptInterface
		public void show(String content) {
			toast("toast-->" + content);
		}
	}
}
