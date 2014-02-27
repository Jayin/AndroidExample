package com.acty;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.android_service.BaseUIActivity;
import com.example.android_service.R;
import com.services.CoreService;
import com.services.CoreService.TestBinder;

public class CoreServiceTest extends BaseUIActivity implements OnClickListener {
	private TestBinder binder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_coreservice_test);
		initData();
		initLayout();
	}

	@Override
	protected void initLayout() {
		_getView(R.id.button1).setOnClickListener(this);
		_getView(R.id.button2).setOnClickListener(this);
		_getView(R.id.button3).setOnClickListener(this);
		_getView(R.id.button4).setOnClickListener(this);
	}

	@Override
	protected void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			sendBroadcast(new Intent("test1"));
			toast("send broadcast-->test1");
			break;
		case R.id.button2:
			sendBroadcast(new Intent("test2"));
			toast("send broadcast-->test2");
			break;
		case R.id.button3:
			sendBroadcast(new Intent("test3"));
			toast("send broadcast-->test3");
			break;
		case R.id.button4:
			Intent service = new Intent(getContext(), CoreService.class);
//			startService(service);
			bindService(service, new ServiceConnection() {
				
				@Override
				public void onServiceDisconnected(ComponentName name) {
					
				}
				
				@Override
				public void onServiceConnected(ComponentName name, IBinder service) {
					binder = (TestBinder)service;
					toast(binder.getInfo());
					
				}
			}, Service.BIND_AUTO_CREATE);
			break;
		default:
			break;
		}
	}

}
