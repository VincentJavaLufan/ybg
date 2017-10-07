package com.ybg.social.mayun.api;
import java.util.Map;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.alibaba.fastjson.JSONObject;


public class MayunImpl extends AbstractOAuth2ApiBinding implements Mayun {
	
	private static final String	URL_GET_OPENID		= "http://gitee.com/api/v5/user";
	private static final String	URL_GET_USERINFO	= "http://gitee.com/api/v5/user";
	private String				appId;
	private String				id;
	@Override
	public MayunUserInfo getUserInfo(String uid) {
		System.out.println("16MayunImpl");
		String result = getRestTemplate().getForObject(URL_GET_USERINFO, null, String.class);
		System.out.println(result);
		try {
			MayunUserInfo bean = JSONObject.parseObject(result, MayunUserInfo.class);
			System.out.println(bean);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取用户信息失败", e);
		}
	}
	
	public MayunImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		System.out.println("32MayunImpl");
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println("34"+result);
		this.id = new JSONObject().parseObject(result, Map.class).get("id").toString();
	}
}
