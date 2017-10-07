package com.ybg.social.sina.api;
import java.util.Map;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.alibaba.fastjson.JSONObject;

public class SinaImpl extends AbstractOAuth2ApiBinding implements Sina {
	
	private static final String	URL_GET_OPENID		= "https://api.weibo.com/2/users/show.json";
	private static final String	URL_GET_USERINFO	= "https://api.weibo.com/2/users/show.json";
	private String				appId;
	private String				id;
	
	public SinaImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		this.id = new JSONObject().parseObject(result, Map.class).get("id").toString();
		System.out.println("22:SinaImpl");
	}
	
	@Override
	public SinaUserInfo getUserInfo(String uid) {
		String result = getRestTemplate().postForObject(URL_GET_USERINFO, null, String.class);
		System.out.println("28:" + result);
		SinaUserInfo bean = JSONObject.parseObject(result, SinaUserInfo.class);
		System.out.println(bean.getIdstr());
		return bean;
	}
}
