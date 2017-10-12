package com.ybg.social.github.service;
import java.util.Map;

public interface GithubuserService {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
