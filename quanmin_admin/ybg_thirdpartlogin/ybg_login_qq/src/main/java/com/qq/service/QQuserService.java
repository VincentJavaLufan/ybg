package com.qq.service;
import java.util.Map;

public interface QQuserService {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url */
	void updateSetting(String appid, String value, String url);
}
