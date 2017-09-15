package com.baidu.oauth.controllor;
import java.util.Map;
import java.util.Properties;
import com.baidu.oauth.service.BaiduUserService;
import com.ybg.base.util.ReplaceDomainUtil;
import com.ybg.base.util.SpringContextUtils;

public class BaiduConfig {
	
	private BaiduConfig() {
	}
	
	private static final String	redirect_URI	= "redirect_URI";
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
		BaiduUserService service = (BaiduUserService) SpringContextUtils.getBean(BaiduUserService.class);
		Map<String, String> map = service.getSetting();
		map.put("redirect_URI", ReplaceDomainUtil.replacedomain(map.get(redirect_URI)));
		props.putAll(map);
	}
}
