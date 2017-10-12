package com.ybg.social.github.dao;
import java.util.Map;

public interface GithubuserDao {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
