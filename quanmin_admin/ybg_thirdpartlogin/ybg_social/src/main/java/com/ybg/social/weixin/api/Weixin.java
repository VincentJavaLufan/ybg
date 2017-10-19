/**
 * 
 */
package com.ybg.social.weixin.api;
/** 微信API调用接口
 * 
 * @author zhailiang */
public interface Weixin {
	
	/*** 获取微信用户信息
	 * 
	 * @return WeixinUserInfo **/
	WeixinUserInfo getUserInfo(String openId);
}
