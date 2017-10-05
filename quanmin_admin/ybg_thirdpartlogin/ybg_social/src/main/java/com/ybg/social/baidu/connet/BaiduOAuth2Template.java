/**
 * 
 */
package com.ybg.social.baidu.connet;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * 
 * @author zhailiang */
public class BaiduOAuth2Template extends OAuth2Template {
	
//	private String				clientId;
//	private String				clientSecret;
//	private String				accessTokenUrl="https://openapi.baidu.com/oauth/2.0/token";
//	private static final String	REFRESH_TOKEN_URL	= "https://openapi.baidu.com/oauth/2.0/token";
	
	
	public BaiduOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.social.oauth2.OAuth2Template#exchangeForAccess(java.lang.String, java.lang.String, org.springframework.util.MultiValueMap)
	 */
//	@Override
//	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
//		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
//		
//		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
//		String accessToken = StringUtils.substringAfterLast(items[0], "=");
//		Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
//		String refreshToken = StringUtils.substringAfterLast(items[2], "=");
//		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
//	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
}
