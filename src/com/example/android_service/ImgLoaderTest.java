package com.example.android_service;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ImgLoaderTest extends BaseUIActivity {
	List<ImageView> list = new ArrayList<ImageView>();
    int[] imgID ={R.id.ImageView01,R.id.ImageView02,R.id.ImageView03,R.id.ImageView04,R.id.ImageView05,R.id.ImageView06,R.id.ImageView07,R.id.ImageView08,R.id.ImageView09};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_imgloader);
		
	    for(int i=0;i<9;i++){
	    	ImageView iv =(ImageView) _getView(imgID[i]);
	    	list.add(iv);
	    }
	    toast(StorageUtils.getCacheDirectory(getContext()).getAbsolutePath());
	}
	@Override
	protected void onResume() {
		 
		super.onResume();
		
		ImageLoader imageLoader = 	 ImageLoader.getInstance();
		
	    for(int i=0;i<9;i++){
	    	imageLoader.displayImage("http://nodejs.org/images/logo-light.png", list.get(i));	 
	    }
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {

	}

}
