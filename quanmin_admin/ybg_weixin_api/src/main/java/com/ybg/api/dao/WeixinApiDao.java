package com.ybg.api.dao;
import java.util.Map;

public interface WeixinApiDao {
	
	Map<String, String> getSetting();

	void updateSetting(String appid, String value);
}
