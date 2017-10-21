package com.ybg.social.github.dao;
import java.util.Map;

/** @author Deament
 * @date 2017/10/1 **/
public interface GithubSocialSettingDao {
	
	/** 返回社交设置
	 * 
	 * @return Map<String, String> **/
	Map<String, String> getSetting();
	
	/** 更新Github社交登陆设置
	 * 
	 * @param appid
	 *            应用ID
	 * @param value
	 *            应用密码
	 * @param url
	 *            回调地址（已作废） **/
	void updateSetting(String appid, String value, String url);
}
