/**
 * 
 */
package com.ybg.social.baidu.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.ybg.core.properties.BaiduProperties;
import com.ybg.social.baidu.api.Baidu;
import com.ybg.social.baidu.connet.BaiduConnectionFactory;
import com.ybg.social.baidu.service.BaiduSocialSettingService;

/** @author zhailiang */
@Configuration
public class BaiduAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	BaiduSocialSettingService baiduuserService;
	
	@Override
	protected ConnectionFactory<Baidu> createConnectionFactory() {
		Map<String, String> setting = baiduuserService.getSetting();
		BaiduProperties baiduConfig = new BaiduProperties();
		baiduConfig.setAppId(setting.get("client_ID"));
		baiduConfig.setAppSecret(setting.get("client_SERCRET"));
		return new BaiduConnectionFactory(baiduConfig.getProviderId(), baiduConfig.getAppId(), baiduConfig.getAppSecret());
	}
}
