package com.ybg.social.github.config;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.ybg.core.properties.BaiduProperties;
import com.ybg.core.properties.GithubProperties;
import com.ybg.social.baidu.api.Baidu;
import com.ybg.social.baidu.connet.BaiduConnectionFactory;
import com.ybg.social.github.api.GitHub;
import com.ybg.social.github.connect.GithubConnectionFactory;
import com.ybg.social.github.service.GithubuserService;

/** @author Deament
 * @date 2017/10/1 **/
@Configuration
public class GithubAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	GithubuserService githubuserService;
	
	@Override
	protected ConnectionFactory<GitHub> createConnectionFactory() {
		Map<String, String> setting = githubuserService.getSetting();
		GithubProperties baiduConfig = new GithubProperties();
		baiduConfig.setAppId(setting.get("client_ID"));
		baiduConfig.setAppSecret(setting.get("client_SERCRET"));
		return new GithubConnectionFactory(baiduConfig.getProviderId(), baiduConfig.getAppId(), baiduConfig.getAppSecret());
	}
}
