package cn.sina.service;
import java.util.Map;

public interface WeiboUserService {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url */
	void updateSetting(String appid, String value, String url);
}
