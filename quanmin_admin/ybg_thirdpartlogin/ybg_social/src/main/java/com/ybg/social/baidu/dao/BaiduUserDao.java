package com.ybg.social.baidu.dao;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface BaiduUserDao {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
