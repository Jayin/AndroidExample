package com.example.android_service;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.widget.BaseNotification;

public class MyNotification extends Activity {
	private View send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_mynotification);
		send = findViewById(R.id.button1);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final BaseNotification n = new BaseNotification(MyNotification.this);
				n.setContentText("发送中...");
				n.setNotificationID(1);
				n.setTicker("发送中...");
				n.setVibrate(new long[0]);
				n.show();
				n.cancle();
				new Thread(new Runnable() {
 
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						n.setContentText("发送成功...");
						n.setNotificationID(1);
						n.setTicker("发送成功...");
						n.setVibrate(new long[0]);
						n.show();
						n.cancle();
					}
				}).start();

			}
		});
	}

}
