package com.ybg.mayun.oauth.controller;
/** 码云应用配置 **/
public class Oauth {
	
	/** 码云应用ID **/
	public static String getClientID() {
		return MayunConfig.getValue("client_ID").trim();
	}
	
	/** 码云应用密钥 **/
	public static String getClient_SERCRET() {
		return MayunConfig.getValue("client_SERCRET").trim();
	}
	
	/** 码云回调地址 **/
	public static String getCallBackUrl() {
		return MayunConfig.getValue("redirect_URI").trim();
	}
}
