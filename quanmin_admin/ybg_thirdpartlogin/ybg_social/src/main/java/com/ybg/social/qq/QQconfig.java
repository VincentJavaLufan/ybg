package com.ybg.social.qq;
import java.util.Properties;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.social.qq.service.QQuserService;

public class QQconfig {
	
	private QQconfig() {
	}
	
	public static final String	client_ID		= "client_ID";
	public static final String	client_SERCRET	= "client_SERCRET";
	public static final String	redirect_URI	= "redirect_URI";
	private static Properties	props			= new Properties();
	static {
		reflushProperties();
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
	
	public static void reflushProperties() {
		QQuserService service = (QQuserService) SpringContextUtils.getBean(QQuserService.class);
		props.putAll(service.getSetting());
	}
}
