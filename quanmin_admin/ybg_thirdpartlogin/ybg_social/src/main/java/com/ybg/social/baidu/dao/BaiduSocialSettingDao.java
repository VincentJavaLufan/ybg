package com.ybg.social.baidu.dao;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface BaiduSocialSettingDao {
	
	/** 返回百度社交登陆设置
	 * 
	 * @return Map<String, String> **/
	Map<String, String> getSetting();
	
	/** 更新百度社交登陆设置
	 * @param appid 应用ID
	 * @param value 应用密码
	 * @param url 回调地址（已作废）
	 *  **/
	void updateSetting(String appid, String value, String url);
}
