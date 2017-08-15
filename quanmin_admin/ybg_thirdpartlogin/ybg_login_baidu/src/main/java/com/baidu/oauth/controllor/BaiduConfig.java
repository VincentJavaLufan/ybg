package com.baidu.oauth.controllor;
import java.util.Properties;
import com.baidu.oauth.service.BaiduUserService;
import com.ybg.base.util.SpringContextUtils;

public class BaiduConfig {
	
	private BaiduConfig() {
	}
	
	private static Properties props = new Properties();
	static {
		BaiduUserService service = (BaiduUserService) SpringContextUtils.getBean(BaiduUserService.class);
		props.putAll(service.getSetting());
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}
