/**
 * 
 */
package com.ybg.social.baidu.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.baidu.oauth.service.BaiduUserService;
import com.qq.service.QQuserService;
import com.ybg.core.properties.BaiduProperties;
import com.ybg.core.properties.QQProperties;
import com.ybg.social.baidu.connet.BaiduConnectionFactory;
import com.ybg.social.qq.connet.QQConnectionFactory;

/** @author zhailiang */
@Configuration
// @ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class BaiduAutoConfig extends SocialAutoConfigurerAdapter {
	
	// @Autowired
	// private SecurityProperties securityProperties;
	@Autowired
	BaiduUserService qQuserService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		Map<String, String> setting = qQuserService.getSetting();
		BaiduProperties baiduConfig = new BaiduProperties();
		baiduConfig.setAppId(setting.get("client_ID"));
		baiduConfig.setAppSecret(setting.get("client_SERCRET"));
		baiduConfig.setProviderId("/baidu_do/login.do");
		return new BaiduConnectionFactory(baiduConfig.getProviderId(), baiduConfig.getAppId(), baiduConfig.getAppSecret());
	}
}
