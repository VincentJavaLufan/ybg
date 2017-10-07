/**
 * 
 */
package com.ybg.social.baidu.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.baidu.oauth.service.BaiduUserService;

import com.ybg.core.properties.BaiduProperties;

import com.ybg.social.baidu.api.Baidu;
import com.ybg.social.baidu.connet.BaiduConnectionFactory;

/** @author zhailiang */
@Configuration
// @ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class BaiduAutoConfig extends SocialAutoConfigurerAdapter {
	
	// @Autowired
	// private SecurityProperties securityProperties;
	@Autowired
	BaiduUserService baiduuserService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<Baidu> createConnectionFactory() {
		Map<String, String> setting = baiduuserService.getSetting();
		BaiduProperties baiduConfig = new BaiduProperties();
		baiduConfig.setAppId(setting.get("client_ID"));
		baiduConfig.setAppSecret(setting.get("client_SERCRET"));
		return new BaiduConnectionFactory(baiduConfig.getProviderId(), baiduConfig.getAppId(), baiduConfig.getAppSecret());
	}
}
