package com.baidu.oauth.dao;
import java.util.Map;

public interface BaiduUserDao {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
