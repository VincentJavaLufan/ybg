package com.ybg.social.github.api;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.alibaba.fastjson.JSONObject;

/** @author Deament
 * @date 2017/10/1 **/
public class GithubImpl extends AbstractOAuth2ApiBinding implements GitHub {
	
	private static final String	URL_GET_OPENID		= "https://api.github.com/user?access_token=%s";// ?client_id={client_id}&redirect_uri={redirect_uri}&state={state}
	private static final String	URL_GET_USERINFO	= "https://api.github.com/user";
	private String				appId;
	private String				id;
	private GithubUserInfo		userinfo;
	
	public GithubImpl(String accessToken, String appId, String secret) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		// String url = URL_GET_OPENID + "?client_id=" + appId + "&client_secret" + secret + "&redirect_url=" + SystemConstant.getSystemdomain() + "/social/github";
		String result = getRestTemplate().getForObject(url, String.class);
		this.id = StringUtils.substringBetween(result, "\"id\":\"", "\"}");
		userinfo = JSONObject.parseObject(result, GithubUserInfo.class);
	}
	
	@Override
	public GithubUserInfo getUserInfo() {
		// String result = getRestTemplate().postForObject(URL_GET_USERINFO, null, String.class);
		// System.out.println(result);
		// try {
		// GithubUserInfo bean = JSONObject.parseObject(result, GithubUserInfo.class);
		// return bean;
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new RuntimeException("获取用户信息失败", e);
		// }
		return userinfo;
	}
}
