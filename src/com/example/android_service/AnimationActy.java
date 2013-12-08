package com.example.android_service;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimationActy extends Activity {
	private View btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.acty_animation);
	    btn = findViewById(R.id.button1);
	    final Animation animation= AnimationUtils.loadAnimation(this, R.anim.scale);
	    btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btn.setBackgroundResource(R.drawable.ic_item_tweet_like);
				btn.startAnimation(animation);
			}
		});
	
	}
}
