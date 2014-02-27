package com.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CoreService extends Service {
	private TestBinder mBinder = new TestBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		d("onCreate-->start to registerReceiver ");
		IntentFilter filter = new IntentFilter();
		filter.addAction("test1");
		filter.addAction("test2");
		registerReceiver(mRecevier, filter);
		d("onCreate-->finish  registerReceiver ");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		d("onStartCommand");
		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mRecevier != null) {
			unregisterReceiver(mRecevier);
		}
	}

	public void d(String content) {
		Log.i("debug", content);
	}

	private BroadcastReceiver mRecevier = new BroadcastReceiver() {
		public void onReceive(android.content.Context context, Intent intent) {
			d(intent.getAction());
		};
	};
    /** service binder*/
	public class TestBinder extends Binder {

		public String getInfo() {
			return "info";
		}
		
	}

}
