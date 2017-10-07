/**
 * 
 */
package com.ybg.social.mayun.connet;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybg.social.weixin.connect.WeixinAccessGrant;

/** 完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * 
 * @author zhailiang */
public class MayunOAuth2Template extends OAuth2Template {
	
	private String				clientId;
	private String				clientSecret;
	private String				accessTokenUrl;
	private static final String	REFRESH_TOKEN_URL	= "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	
	public MayunOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
	}
	
	// 码云的 Auth2 登陆 URL参数非正规， 所以要重写
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> parameters) {
		parameters = new LinkedMultiValueMap<String, String>();
		// parameters.add("client_id", clientId);
		// parameters.add("client_secret", clientSecret);
		// parameters.add("code", authorizationCode);
		// parameters.add("grant_type", "authorization_code");
		// parameters.add("redirect_uri", redirectUri);
		System.out.println(clientId + "," + clientSecret + "," + authorizationCode + "," + redirectUri);
		System.out.println(accessTokenUrl);
		accessTokenUrl += accessTokenUrl + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + authorizationCode + "&grant_type=authorization_code" + "&redirect_uri=" + redirectUri;
		return postForAccessGrant(accessTokenUrl, parameters);
	}
	
	@Override
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		
	String url=	"https://gitee.com/oauth/authorize?client_id="+clientId+"&redirect_uri="+"http://localhost/social/mayun"+"&response_type=code";
		
		return url;
	}
}
