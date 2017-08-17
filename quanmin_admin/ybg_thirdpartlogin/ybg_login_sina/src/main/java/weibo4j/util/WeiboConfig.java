package weibo4j.util;
import java.util.Map;
import java.util.Properties;
import com.ybg.base.util.ReplaceDomainUtil;
import com.ybg.base.util.SpringContextUtils;
import cn.sina.service.WeiboUserService;

public class WeiboConfig {
	
	public static final String redirect_URI="redirect_URI";
	public WeiboConfig() {
	}
	
	private static Properties props = new Properties();
	static {
		WeiboUserService service = (WeiboUserService) SpringContextUtils.getBean(WeiboUserService.class);
		Map<String, String> map=	service.getSetting();
		map.put("redirect_URI", ReplaceDomainUtil.replacedomain(map.get(redirect_URI)) );
		props.putAll(map);
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}
