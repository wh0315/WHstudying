package com.caifulif.whstudying.utils;

import android.app.Activity;
import android.content.Intent;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * 友盟分享工具�?
 * 
 * @author tangxian
 * 
 */
public class UmengShareUtils {
	private Activity mActivity;
	/** 友盟分享服务 */
	private final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
	/**QQ app id*/
	private static final String QQAppId = "1105705951";
	/**QQ app key*/
	private static final String QQAppKey = "543842364d5242493258776b39704e64";
	// 注意：在微信授权的时候，必须传�?appSecret
	// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里�?��替换成你注册的AppID
	private final String WXAppId = "wx9609d36ef7e2e8a6";
	private final String WXAppSecret = "a61dce7a0d08efa96f03d3d510c63e4d";
	/**要分享的内容*/
	private String content;
	/**分享的url*/
	private String url ;
	/**分享的标题*/
	private String title;
	private String imgurl ;
	public UmengShareUtils(Activity mActivity, String content, String url, String title,String imgurl) {
		this.mActivity = mActivity;
		this.content = content;
		this.url = url;
		this.title = title;
		this.imgurl = imgurl ;
		configPlatforms();
		setShareContent();
		//设置分享面板显示的内容
		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
				SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
				SHARE_MEDIA.QZONE);
	}

	/**调用分享面板*/
	public void share(){
		mController.openShare(mActivity, false);
	}
	
	/**
	 * 如需使用sso�?��在onActivity中调用此方法
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public void authSSO(int requestCode, int resultCode, Intent data){
		/** 使用SSO授权必须添加如下代码 */
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
	private void configPlatforms() {
		// 添加QQ、QZone平台
		addQQQZonePlatform();
		// 添加微信、微信朋友圈平台
		addWXPlatform();
	}

	
	/**
	 * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型�?即单纯的文字、图片�?音乐、视�? 参数说明 : title, summary,
	 *       image url中必须至少设置一�? targetUrl必须设置,网页地址必须�?http://"�?�� . title :
	 *       要分享标�?summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写�?��] targetUrl
	 *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	private void addQQQZonePlatform() {
		
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(mActivity, QQAppId,
				QQAppKey);
		qqSsoHandler.setTitle(content);
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(mActivity, QQAppId,
				QQAppKey);

		qZoneSsoHandler.addToSocialSDK();
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	private void addWXPlatform() {
		
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(mActivity, WXAppId, WXAppSecret);
		wxHandler.addToSocialSDK();
		// 支持微信朋友�?
		UMWXHandler wxCircleHandler = new UMWXHandler(mActivity, WXAppId,WXAppSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	private void setShareContent() {
		//分享图片
//		UMImage urlImage = new UMImage(mActivity, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.ic_launcher));
		UMImage urlImage = new UMImage(mActivity,imgurl);
		//设置微信分享内容
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent(content);
		weixinContent.setTitle(title);
		weixinContent.setTargetUrl(url);
		 weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent(content);
		circleMedia.setTitle(title);
		circleMedia.setTargetUrl(url);
		circleMedia.setShareMedia(urlImage);
		mController.setShareMedia(circleMedia);

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setShareContent(content);
		qzone.setTitle(title);
		qzone.setTargetUrl(url);
		qzone.setShareMedia(urlImage);
		mController.setShareMedia(qzone);

		//设置QQ分享内容
		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setTitle(title);
		qqShareContent.setShareContent(content);
		qqShareContent.setTargetUrl(url);
		qqShareContent.setShareMedia(urlImage);
		mController.setShareMedia(qqShareContent);
	}
}
