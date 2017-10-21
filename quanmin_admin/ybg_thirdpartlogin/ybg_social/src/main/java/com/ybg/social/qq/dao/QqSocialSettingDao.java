package com.ybg.social.qq.dao;
import java.util.Map;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
public interface QqSocialSettingDao {
	
	/** 更新qq社交登陆设置
	 * 
	 * @param appid
	 *            应用ID
	 * @param value
	 *            应用密码
	 * @param url
	 *            回调地址（已作废） **/
	void updateSetting(String appid, String value, String url);
	
	/** 返回社交设置
	 * 
	 * @return Map<String, String> **/
	Map<String, String> getSetting();
}
