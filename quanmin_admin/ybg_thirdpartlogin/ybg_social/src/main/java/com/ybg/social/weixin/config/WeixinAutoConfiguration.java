/**
 * 
 */
package com.ybg.social.weixin.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;
import com.ybg.api.service.WeixinApiService;
import com.ybg.core.properties.WeixinProperties;
import com.ybg.social.ImoocConnectView;
import com.ybg.social.weixin.api.Weixin;
import com.ybg.social.weixin.connect.WeixinConnectionFactory;

/** 微信登录配置
 * 
 * @author zhailiang */
@Configuration
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {
	
	@Autowired
	WeixinApiService apiService;
	
	@Override
	protected ConnectionFactory<Weixin> createConnectionFactory() {
		WeixinProperties weixinConfig = new WeixinProperties();
		Map<String, String> setting = apiService.getSetting();
		weixinConfig.setAppId(setting.get("appId"));
		weixinConfig.setAppSecret(setting.get("secret"));
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(), weixinConfig.getAppSecret());
	}
	
	@Bean(name = { "connect/weixinConnect", "connect/weixinConnected" })
	// @ConditionalOnMissingBean(name = "weixinConnectedView")
	public View weixinConnectedView() {
		return new ImoocConnectView();
	}
}
