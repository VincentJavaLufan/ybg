package com.ybg.social.sina.service;
import java.util.Map;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
public interface WeiboUserService {
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url
	 */
	void updateSetting(String appid, String value, String url);
}
