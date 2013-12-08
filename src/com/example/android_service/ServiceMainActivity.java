package com.example.android_service;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_service.LocalService.MyBinder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServiceMainActivity extends Activity {
	private final static String Action = "ServiceMainActivity";
	private View start, stop, test, sendbroadcast;
	private Context c = ServiceMainActivity.this;
	private TextView tv;
	private MyReceiver receiver = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_main);
		start = findViewById(R.id.button1);
		stop = findViewById(R.id.button2);
		tv = (TextView) findViewById(R.id.textView1);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, LocalService.class);

				startService(intent);
				bindService(intent, new ServiceConnection() {

					@Override
					public void onServiceDisconnected(ComponentName name) {

					}

					@Override
					public void onServiceConnected(ComponentName name,
							IBinder service) {

						Toast.makeText(getApplicationContext(),
								"onServiceConnected", 1).show();
						((MyBinder) service).show();
					}
				}, BIND_AUTO_CREATE);

			}
		});

		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(c, LocalService.class);

				stopService(intent);
				Toast.makeText(getApplicationContext(), "stop", 1).show();
			}
		});

		test = findViewById(R.id.button3);
		final RequestParams rp = new RequestParams();
		rp.put("lol", "java");
		rp.put("Jony", "我要吃饭！！");
		test.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AsyncHttpClient client = new AsyncHttpClient();

				client.get("http://192.168.1.108:8000", rp,
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, String arg1) {
								System.out.println(arg0 + "--->" + arg1);
								Toast.makeText(ServiceMainActivity.this,
										arg0 + "", 1).show();
							}

							@Override
							public void onFailure(Throwable t, String c) {
								System.out.println(c);
								Toast.makeText(ServiceMainActivity.this, c, 1)
										.show();
							}

						});
			}
		});
		receiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter(Action);
		registerReceiver(receiver, intentFilter);
		sendbroadcast = findViewById(R.id.button4);
		sendbroadcast.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Action);
				sendBroadcast(intent);
			}
		});
	}

	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
		//	tv.setText(System.currentTimeMillis() + "");
			tv.setTextColor(Color.BLACK);
			JSONObject jo = new JSONObject();
			try {
				jo.put("lol", "test");
				jo.put("geigui!", "hahah");
			} catch (JSONException e) {
				
				e.printStackTrace();
				 return ;
			}
			SharedPreferences sp  = ServiceMainActivity.this.getSharedPreferences("LOL",Context.MODE_PRIVATE);
			sp.edit().putString("L", jo.toString()).commit();
		//	tv.setText(jo.toString());
	//		tv.setText(sp.)  //  stops here
		}

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
