package com.ybg.api.service;
import java.util.Map;

public interface WeixinApiService {
	
	/** 获取微信设置 **/
	Map<String, String> getSetting();
	
	/** 根据CODE 获取OPEN_ID 没有或报错 就返回null
	 * 
	 * @param code
	 *            微信端传回的参数
	 * @return openid **/
	String getOpenidByCode(String code);
	
	/** 获取accesstoken 有效期两小时 要缓存 **/
	String getAccessToken();
}
