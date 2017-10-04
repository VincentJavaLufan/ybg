package com.ybg.social.baidu.api;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaiduImpl extends AbstractOAuth2ApiBinding implements Baidu {
	
	private static final String	URL_GET_OPENID		= "https://graph.qq.com/oauth2.0/me?access_token=%s";
	private static final String	URL_GET_USERINFO	= "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	private String				appId;
	private String				uid;
	private ObjectMapper		objectMapper		= new ObjectMapper();
	
	public BaiduImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		this.uid = StringUtils.substringBetween(result, "\"uid\":\"", "\"}");
	}
	
	@Override
	public BaiduUserInfo getUserInfo(String uid) {
		// TODO Auto-generated method stub
		return null;
	}
}
