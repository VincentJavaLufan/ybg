package com.qq.dao;
import java.util.Map;

public interface QQuserDao {
	
	void updateSetting(String appid, String value, String url);
	
	Map<String, String> getSetting();
}
