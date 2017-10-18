package com.ybg.social.baidu.service;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface BaiduUserService {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url
	 */
	void updateSetting(String appid, String value, String url);
}
