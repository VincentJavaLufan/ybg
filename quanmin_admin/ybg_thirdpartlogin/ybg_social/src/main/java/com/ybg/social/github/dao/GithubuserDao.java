package com.ybg.social.github.dao;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface GithubuserDao {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
