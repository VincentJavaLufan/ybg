package cn.sina.dao;
import java.util.Map;

public interface WeiboUserDao {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url */
	void updateSetting(String appid, String value, String url);
}
