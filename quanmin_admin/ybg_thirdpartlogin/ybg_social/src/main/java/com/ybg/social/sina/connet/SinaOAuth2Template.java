/**
 * 
 */
package com.ybg.social.sina.connet;
import java.nio.charset.Charset;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.web.client.RestTemplate;

/** 完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * 
 * @author zhailiang */
public class SinaOAuth2Template extends OAuth2Template {
	
	public SinaOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
}
