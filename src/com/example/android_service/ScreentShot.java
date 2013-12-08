package com.example.android_service;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.model.ShareManager;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.UMImage;

public class ScreentShot extends Activity {
  private ImageView iv;
  private View take;
  private Activity acty= this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_screentshot);
		iv = (ImageView)findViewById(R.id.imageView1);
		take  = findViewById(R.id.button1);
		take.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Bitmap pic = take(acty);
				iv.setImageBitmap(pic);
				ShareManager sm  = new ShareManager("test",new UMImage(ScreentShot.this,pic ));
				sm.shareToSina(ScreentShot.this, new SnsPostListener() {
					
					@Override
					public void onStart() {	
					}
					@Override
					public void onComplete(SHARE_MEDIA arg0, int arg1, SocializeEntity arg2) {
						 
					}
				});
			}
		});
	}
	
	private Bitmap take(Activity acty){
		 // View是你需要截图的View     
	    View view = acty.getWindow().getDecorView();     
	    view.setDrawingCacheEnabled(true);     
	    view.buildDrawingCache();     
	    Bitmap b1 = view.getDrawingCache(); 
		
		return b1;
	}

}
