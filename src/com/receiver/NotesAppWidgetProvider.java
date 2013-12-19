package com.receiver;

import com.example.android_service.ImgLoaderTest;
import com.example.android_service.MyWebView;
import com.example.android_service.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class NotesAppWidgetProvider extends AppWidgetProvider {

	 
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		 
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) { 	 
		PendingIntent startNotes = PendingIntent.getActivity(context, 0, new Intent(context,MyWebView.class), 0);
		PendingIntent editNotes = PendingIntent.getActivity(context, 1 , new Intent(context,ImgLoaderTest.class), 0);
		for(int i=0;i<appWidgetIds.length;i++){
			Log.i("debug", "onUpdata --");
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_notes);
			remoteViews.setOnClickPendingIntent(R.id.widget_notes_content, startNotes);
			remoteViews.setOnClickPendingIntent(R.id.widget_notes_btn_addNote, editNotes);
	 
			
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
