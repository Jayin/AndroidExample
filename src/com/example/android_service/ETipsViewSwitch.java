package com.example.android_service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ETipsViewSwitch extends Activity {
	private ViewFlipper flipper;
	private View pre, next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_etipsswitch);
		flipper = (ViewFlipper) findViewById(R.id.viewflippler);
		pre = findViewById(R.id.button1);
		next = findViewById(R.id.button2);
		flipper.removeAllViews();
		flipper.addView(_inflat(R.drawable.pic1));
		flipper.addView(_inflat(R.drawable.pic2));
		flipper.addView(_inflat(R.drawable.pic3));
		flipper.addView(_inflat(R.drawable.pic4));
		flipper.addView(_inflat(R.drawable.pic5));
		flipper.addView(_inflat(R.drawable.pic6));

		flipper.setInAnimation(this, R.anim.main_tweet_in);
		flipper.setOutAnimation(this, R.anim.main_tweet_out);

		pre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				flipper.showPrevious();

			}
		});
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				flipper.showNext();

			}
		});
		
	

	}

	public View _inflat(int picID) {
		View v = LayoutInflater.from(this).inflate(R.layout.item_image, null);
		((ImageView) v.findViewById(R.id.image1)).setImageResource(picID);
		return v;
	}

	@Override
	protected void onResume() {

		super.onResume();
		flipper.startFlipping();
		flipper.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				flipper.showNext();
				
			}
		}, 1000);

	}
	
	@Override
	protected void onPause() {
		 
		super.onPause();
		if(flipper.isShown())flipper.stopFlipping();
	}

}
