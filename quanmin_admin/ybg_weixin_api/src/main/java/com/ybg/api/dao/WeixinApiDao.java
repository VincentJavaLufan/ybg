package com.ybg.api.dao;
import java.util.Map;
/**
 * @author Deament
 * @date 2017年10月1日
 * **/
public interface WeixinApiDao {
	
	Map<String, String> getSetting();

	void updateSetting(String appid, String value);
}
