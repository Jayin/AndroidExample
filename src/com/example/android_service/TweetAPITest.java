package com.example.android_service;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TweetAPITest extends Activity {
	private View go;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_tweetapitest);
		go = findViewById(R.id.acty_tweetapitest_button1);
		tv = (TextView) findViewById(R.id.acty_tweetapitest_textView1);
		final Handler h = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				tv.setText((String) msg.obj);
				tv.setTextColor(Color.BLACK);
			}
		};
//api.regist("Jayin_Ton", "tonjayin@gmail.com",
		//"3112002722", "5555");
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						TweetAPI api = new TweetAPI();
						Message msg = h.obtainMessage();
			//			msg.obj = api.login("tonjayin@gmail.com", "5555");
						//msg.obj = api.reSetPsw("tonjayin@gmail.com", "5555", System.currentTimeMillis()+24*1000*60);
					//	msg.obj = api.getTopicList();
       // msg.obj= api.compose("5", "哈哈你大爷的！", "jayintton", System.currentTimeMillis()+"", "0");
				 	//	 msg.obj = api.getTopic(5+"", "1");
					//	 msg.obj = api.comment("5", "4","lol", System.currentTimeMillis()+"", "John", "0");
				     //  msg.obj = api.getComment("5", "9");
						msg.obj = JSONParse.get();
						h.sendMessage(msg);

					}
				}).start();

			}
		});

	}
}
