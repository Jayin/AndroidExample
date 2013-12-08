package com.example.android_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.model.ShareManager;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.UMSsoHandler;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.media.UMImage;

public class UmengSDK extends Activity {
	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory.getUMSocialService(
			"com.umeng.share", RequestType.SOCIAL);
	private View share;

	private void toast(String c) {
		Toast.makeText(this, c, 500).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_umeng);
		share = findViewById(R.id.umeng_share);
		share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ShareManager sm = new ShareManager("我正在使用邑大校园助手#ETips#最新版2.0。新增校园资讯模块，个人便签,查空课室。对接学生子系统，一键导入课程表，轻松查成绩算绩点;对接邑大图书馆，找图书不再怠慢;对接校园服务，查电费，看邑大新闻！豌豆荚，360手机助手搜\"ETips\"即可下载！http://t.cn/zQpnBeA");
                sm.shareToSina(UmengSDK.this, new SnsPostListener() {
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onComplete(SHARE_MEDIA arg0, int arg1, SocializeEntity arg2) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
	}

	private void postshare() {
		// 设置分享内容
		mController
				.setShareContent("我正在使用邑大校园助手#ETips#最新版2.0。新增校园资讯模块，个人便签,查空课室。对接学生子系统，一键导入课程表，轻松查成绩算绩点;对接邑大图书馆，找图书不再怠慢;对接校园服务，查电费，看邑大新闻！点击下载：http://weibo.com/p/1004041085568");
		// 设置分享图片, 参数2为图片的url地址
		mController.setShareImage(new UMImage(UmengSDK.this,
				"http://www.umeng.com/images/pic/banner_module_social.png"));

		mController.setShareImage(null);
		mController.postShare(UmengSDK.this, SHARE_MEDIA.SINA,
				new SnsPostListener() {

					@Override
					public void onStart() {

					}

					@Override
					public void onComplete(SHARE_MEDIA arg0, int arg1,
							SocializeEntity arg2) {
						Toast.makeText(UmengSDK.this, "发布成功！", 1).show();

					}

				});
	}

	private void directShare() {

		mController.directShare(UmengSDK.this, SHARE_MEDIA.SINA,
				new SnsPostListener() {

					@Override
					public void onStart() {

					}

					@Override
					public void onComplete(SHARE_MEDIA platform, int eCode,
							SocializeEntity entity) {
						if (eCode == StatusCode.ST_CODE_SUCCESSED) {
							Toast.makeText(UmengSDK.this, "分享成功",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(UmengSDK.this, "分享失败",
									Toast.LENGTH_SHORT).show();
						}

					}
				});
	}
}
