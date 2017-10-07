/**
 * 
 */
package com.ybg.social.qq.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.qq.service.QQuserService;
import com.ybg.core.properties.QQProperties;
import com.ybg.social.qq.api.QQ;
import com.ybg.social.qq.connet.QQConnectionFactory;

/** @author zhailiang */
@Configuration
// @ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	// @Autowired
	// private SecurityProperties securityProperties;
	@Autowired
	QQuserService qQuserService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<QQ> createConnectionFactory() {
		Map<String, String> setting = qQuserService.getSetting();
		QQProperties qqConfig = new QQProperties();
		qqConfig.setAppId(setting.get("client_ID"));
		qqConfig.setAppSecret(setting.get("client_SERCRET"));
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}
}
