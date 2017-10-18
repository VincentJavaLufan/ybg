package com.ybg.social.sina.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.ybg.core.properties.SinaProperties;
import com.ybg.social.sina.api.Sina;
import com.ybg.social.sina.connet.SinaConnectionFactory;
import com.ybg.social.sina.service.WeiboUserService;

/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 **/
@Configuration
public class SinaAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	WeiboUserService weibouserService;
	
	@Override
	protected ConnectionFactory<Sina> createConnectionFactory() {
		Map<String, String> setting = weibouserService.getSetting();
		SinaProperties sinaConfig = new SinaProperties();
		sinaConfig.setAppId(setting.get("client_ID"));
		sinaConfig.setAppSecret(setting.get("client_SERCRET"));
		return new SinaConnectionFactory(sinaConfig.getProviderId(), sinaConfig.getAppId(), sinaConfig.getAppSecret());
	}
}
