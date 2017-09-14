package com.ybg.mayun.oauth.controller;
import java.util.Properties;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.mayun.oauth.service.MayunUserService;

public class MayunConfig {
	
	private MayunConfig() {
	}
	
	public static final String	client_ID		= "client_ID";
	public static final String	client_SERCRET	= "client_SERCRET";
	public static final String	redirect_URI	= "redirect_URI";
	private static Properties	props			= new Properties();
	static {
		MayunUserService service = (MayunUserService) SpringContextUtils.getBean(MayunUserService.class);
		props.putAll(service.getSetting());
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
	
	public static void reflushProperties() {
		MayunUserService service = (MayunUserService) SpringContextUtils.getBean(MayunUserService.class);
		props.putAll(service.getSetting());
	}
}
