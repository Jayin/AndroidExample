package com.example.android_service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class LocalService extends Service {
	private int i = 0;
	MyBinder myBinder;
	@Override
	public IBinder onBind(Intent arg0) {
		myBinder  = new MyBinder();
		return myBinder;
	}

	@Override
	public void onCreate() {
		Log.i("debug", "BasicService--onCreate()::" + i++);
		System.out.println("*****onCreate*****");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("debug", "BasicService--onCreate()::" + i++);
		System.out.println("*****onStartCommand*****");
		Handler h = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(getApplicationContext(), "LOL", 1).show();
			}
		};
		h.sendEmptyMessage(1);
		return Service.START_CONTINUATION_MASK;
	}

	class MyBinder extends Binder {
		public void show() {
           System.out.println("111111");
		}
	}
}
