package com.baidu.oauth.controllor;
/** 应用配置 **/
public class Oauth {
	
	/** 百度应用ID **/
	public static String getClientID() {
		return BaiduConfig.getValue("client_ID").trim();
	}
	
	/** 应用密钥 **/
	public static String getClient_SERCRET() {
		return BaiduConfig.getValue("client_SERCRET").trim();
	}
	
	/** 回调地址 **/
	public static String getCallBackUrl() {
		return BaiduConfig.getValue("redirect_URI").trim();
	}
}
