package com.example.android_service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.receiver.AlarmReceiver;

public class AlarmManagerActy extends Activity {
	private View start, stop;
	private int reqcode = 1;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_alarmmanager);
		start = findViewById(R.id.button1);
		stop = findViewById(R.id.button2);
		tv =(TextView)findViewById(R.id.textView1);
		
		String s = "<p style=\"font-size:200%\">A paragraph</p><br><big>LOL</big><br><font size=\"100\" color=\"blue\">This is some text!</font>";
        String ss = "<strong><p style=\"font-size:400%\">计算机组成原理</p></strong>";
        String sss= "<p style=\"font-size:80%\">     马兰芳102</p> ";
		
		tv.setText(Html.fromHtml(ss+sss));
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("start.setOnClickListener");
				Intent intent = new Intent(AlarmManagerActy.this,
						AlarmReceiver.class);
				intent.setAction("Alarm");
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						AlarmManagerActy.this, reqcode, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				AlarmManager am = (AlarmManager) AlarmManagerActy.this
						.getSystemService(ALARM_SERVICE);
				am.setRepeating(AlarmManager.RTC_WAKEUP,
						System.currentTimeMillis(), 3 * 1000, pendingIntent);
			}
		});
		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
                  System.out.println("stop.setOnClickListener");
				Intent intent = new Intent(AlarmManagerActy.this,
						AlarmReceiver.class);
				intent.setAction("Alarm");
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						AlarmManagerActy.this, reqcode, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				AlarmManager am = (AlarmManager) AlarmManagerActy.this
						.getSystemService(ALARM_SERVICE);
				am.cancel(pendingIntent);
			}
		});
	}

}
