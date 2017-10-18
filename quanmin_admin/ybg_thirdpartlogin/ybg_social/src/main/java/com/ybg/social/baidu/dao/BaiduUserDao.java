package com.ybg.social.baidu.dao;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface BaiduUserDao {
	
	/** 返回百度社交登陆设置 **/
	Map<String, String> getSetting();
	
	/** 更新百度社交登陆设置 **/
	void updateSetting(String appid, String value, String url);
}
