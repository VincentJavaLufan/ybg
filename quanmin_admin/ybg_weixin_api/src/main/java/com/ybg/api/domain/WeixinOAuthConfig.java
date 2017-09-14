package com.ybg.api.domain;
import java.util.Properties;
import com.ybg.api.service.WeixinApiService;
import com.ybg.base.util.SpringContextUtils;

public class WeixinOAuthConfig {
	
	public static final String	APPID	= "appId";
	public static final String	SECRET	= "secret";
	
	public WeixinOAuthConfig() {
	}
	
	private static Properties props = new Properties();
	static {
		WeixinApiService service = (WeixinApiService) SpringContextUtils.getBean(WeixinApiService.class);
		props.putAll(service.getSetting());
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
	
	public static void reflushProperties() {
		WeixinApiService service = (WeixinApiService) SpringContextUtils.getBean(WeixinApiService.class);
		props.putAll(service.getSetting());
	}
}
