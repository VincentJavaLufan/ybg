package com.baidu.oauth.service;
import java.util.Map;

public interface BaiduUserService {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url */
	void updateSetting(String appid, String value, String url);
}
