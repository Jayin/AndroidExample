package com.example.android_service;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {

	@Override
	public void onCreate() {
      super.onCreate();
      // Create global configuration and initialize ImageLoader with this configuration
      ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).discCacheSize(50 * 1024 * 1024)
    	        .discCacheFileCount(100).memoryCacheSize(2 * 1024 * 1024).build();
      
      ImageLoader.getInstance().init(config);
      ImageLoader loader = ImageLoader.getInstance();
	}
}
