package weibo4j.util;
import java.util.Properties;
import com.ybg.base.util.SpringContextUtils;
import cn.sina.service.WeiboUserService;

public class WeiboConfig {
	
	public WeiboConfig() {
	}
	
	private static Properties props = new Properties();
	static {
		WeiboUserService service = (WeiboUserService) SpringContextUtils.getBean(WeiboUserService.class);
		props.putAll(service.getSetting());
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}
