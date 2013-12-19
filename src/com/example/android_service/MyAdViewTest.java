package com.example.android_service;

import com.example.widget.ADView;

import android.app.Activity;
import android.os.Bundle;

public class MyAdViewTest extends Activity {
	ADView ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_myadview);
		ad = (ADView) findViewById(R.id.adview);
		String[] urls = new String[] {
				"http://www.umeng.com/images/pic/launch/banner-index.png",
				"http://www.umeng.com/images/pic/launch/icon3.png",
				"http://www.umeng.com/images/pic/push/pic_push_1.png" };
		// ad.setURL("http://developer.android.com/images/title-devbytes-kk.jpg");
		ad.setURL(urls);
		ad.display();
	}
}
