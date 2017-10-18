package com.ybg.social.github.service;
import java.util.Map;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
public interface GithubuserService {
	
	Map<String, String> getSetting();
	
	void updateSetting(String appid, String value, String url);
}
