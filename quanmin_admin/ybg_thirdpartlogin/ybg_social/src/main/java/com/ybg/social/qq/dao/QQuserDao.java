package com.ybg.social.qq.dao;
import java.util.Map;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
public interface QQuserDao {
	
	void updateSetting(String appid, String value, String url);
	
	Map<String, String> getSetting();
}
