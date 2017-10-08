package com.ybg.social.baidu.controllor;
import java.util.Map;
import java.util.Properties;
import com.ybg.base.util.ReplaceDomainUtil;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.social.baidu.service.BaiduUserService;

public class BaiduConfig {
	
	private BaiduConfig() {
	}
	
	private static Properties props = new Properties();
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
		BaiduUserService service = (BaiduUserService) SpringContextUtils.getBean(BaiduUserService.class);
		Map<String, String> map = service.getSetting();
		props.putAll(map);
	}
}
