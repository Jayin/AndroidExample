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
//		String[] urls = new String[] {
//				"http://lh3.ggpht.com/_yJtfXORDDe4/Sg-GN-1Mo4I/AAAAAAAAJQk/SVsfr1Iy7_I/s400/IMGP4048.jpg",
//				"http://img.szhome.com/images/sznews/2012/11/20121102144231234.JPG",
//				"http://www.carnews.com/Files/Editor_Files/image/Lee/minor5.jpg.pagespeed.ce.XG7AxB1en9.jpg" };
		// ad.setURL("http://developer.android.com/images/title-devbytes-kk.jpg");
		ad.setURL(urls);
		ad.display();
	}
	 
	
}
