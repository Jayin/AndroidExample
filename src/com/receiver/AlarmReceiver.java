package com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	    System.out.println("onReceiver");
		if (intent.getAction().equals("Alarm"))  
			Toast.makeText(context, "闹钟来了！", 500).show();
		 

	}
 
}
