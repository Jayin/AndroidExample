package com.example.android_service;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.os.Bundle;
import android.widget.ImageView;

public class PhotoViewTest extends BaseUIActivity {
   private ImageView iv;
   private PhotoViewAttacher attacther;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_photoviewtest);
		iv = (ImageView)_getView(R.id.imageView1);
		attacther = new PhotoViewAttacher(iv);
		
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {
 
	}

}
