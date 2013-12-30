package com.example.android_service;

import java.util.Date;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AsyncHttpClientTest extends BaseUIActivity {
	private View go;
	private TextView tv;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_asynchttpclienttest);
		go = findViewById(R.id.button1);
		//tv = (TextView) _getView(R.id.textView1);
		iv = (ImageView)_getView(R.id.image);
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AsyncHttpClient client = new AsyncHttpClient();
			
			 
				client.setCookieStore(null);
				client.get(getContext(),
						"http://nodejs.org/images/logo-light.png", null,
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, Header[] header,
									byte[] res) {
                                   Bitmap bitmap = BitmapFactory.decodeByteArray(res, 0,res.length);
                                   iv.setImageBitmap(bitmap);
                                   
							}

						});
				
				 

			}
		});

	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	@Override
	protected void initLayout() {
		 
	}

	@Override
	protected void initData() {
		 
	}

}
