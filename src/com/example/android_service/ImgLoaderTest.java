package com.example.android_service;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.utils.L;

public class ImgLoaderTest extends BaseUIActivity {
	List<ImageView> list = new ArrayList<ImageView>();
	int[] imgID = { R.id.ImageView01, R.id.ImageView02, R.id.ImageView03,
			R.id.ImageView04, R.id.ImageView05, R.id.ImageView06,
			R.id.ImageView07, R.id.ImageView08, R.id.ImageView09,
			R.id.ImageView10, R.id.ImageView11, R.id.ImageView12,
			R.id.ImageView13, R.id.ImageView14, R.id.ImageView15,
			R.id.ImageView16, R.id.ImageView17, R.id.ImageView18,
			R.id.ImageView19 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_imgloader);

		for (int i = 0; i < 19; i++) {
			ImageView iv = (ImageView) _getView(imgID[i]);
			list.add(iv);
		}
		toast(StorageUtils.getCacheDirectory(getContext()).getAbsolutePath());
	}

	@Override
	protected void onResume() {

		super.onResume();

		ImageLoader imageLoader = ImageLoader.getInstance();
		DiscCacheAware dca = imageLoader.getDiscCache();

		// DisplayImageOptions options = new DisplayImageOptions.Builder().d
		for (int i = 0; i < 19; i++) {

			imageLoader.displayImage("http://nodejs.org/images/logo-light.png",
					list.get(i));
			L.i(StorageUtils.getCacheDirectory(getContext()).getAbsolutePath());

		}
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {

	}

}
