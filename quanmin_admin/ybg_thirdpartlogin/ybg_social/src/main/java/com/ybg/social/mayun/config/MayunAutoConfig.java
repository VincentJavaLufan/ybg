package com.ybg.social.mayun.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.ybg.core.properties.MayunProperties;
import com.ybg.mayun.oauth.service.MayunUserService;
import com.ybg.social.mayun.api.Mayun;
import com.ybg.social.mayun.connet.MayunConnectionFactory;

/** @author zhailiang */
@Configuration
public class MayunAutoConfig extends SocialAutoConfigurerAdapter {
	
	// @Autowired
	// private SecurityProperties securityProperties;
	@Autowired
	MayunUserService mayunuserService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<Mayun> createConnectionFactory() {
		Map<String, String> setting = mayunuserService.getSetting();
		MayunProperties baiduConfig = new MayunProperties();
		baiduConfig.setAppId(setting.get("client_ID"));
		baiduConfig.setAppSecret(setting.get("client_SERCRET"));
		String redirurl = setting.get("redirect_URI");
		return new MayunConnectionFactory(baiduConfig.getProviderId(), baiduConfig.getAppId(), baiduConfig.getAppSecret(), redirurl);
	}
}
