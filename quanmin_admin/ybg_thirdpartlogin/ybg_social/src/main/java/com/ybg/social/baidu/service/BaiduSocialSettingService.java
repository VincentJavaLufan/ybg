package com.ybg.social.baidu.service;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface BaiduSocialSettingService {
	
	/** 返回百度社交登陆设置
	 * 
	 * @return Map<String, String> **/
	Map<String, String> getSetting();
	
	/** 更新百度社交登陆设置
	 * 
	 * @param appid
	 * @param value
	 * @param url
	 */
	void updateSetting(String appid, String value, String url);
}
