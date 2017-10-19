package com.ybg.social.weixin.api;
/** 微信API调用接口
 * 
 * @author zhailiang */
public interface Weixin {
	
	/*** 获取微信用户信息
	 * 
	 * @param openId
	 *            微信的ID
	 * @return WeixinUserInfo **/
	WeixinUserInfo getUserInfo(String openId);
}
