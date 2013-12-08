package com.model;

import android.content.Context;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.UMImage;
/**
 * 分享管理器
 * @author Jayin Ton
 *
 */
public class ShareManager {
	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory.getUMSocialService(
			"com.umeng.share", RequestType.SOCIAL);
	//我正在使用邑大校园助手#ETips#最新版2.0。新增校园资讯模块，个人便签,查空课室。对接学生子系统，一键导入课程表，轻松查成绩算绩点;对接邑大图书馆，找图书不再怠慢;对接校园服务，查电费，看邑大新闻！豌豆荚，360手机助手搜"ETips"即可下载！http://t.cn/zQpnBeA
    /**
     * 纯文字分享
     * @param content
     */
	public ShareManager(String content) {
		this(content,null);
	}
	/**
	 * 文字 +图片
	 * @param content
	 * @param umImage
	 */
	public ShareManager(String content,UMImage umImage){
		mController.setShareContent(content);
		mController.setShareImage(umImage);
	}
	/**
	 * 分享到新浪微博
	 * @param context
	 * @param listener
	 */
	public void shareToSina(Context context,SnsPostListener listener){
		mController.postShare(context, SHARE_MEDIA.SINA, listener);
	}
	
	public void shareToSinaDirectly(Context context,SnsPostListener listener){
		mController.directShare(context, SHARE_MEDIA.SINA, listener);
	}

}
