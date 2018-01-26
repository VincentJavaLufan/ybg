package com.ybg.social.sina.api;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.http.HttpUtil;


/** @author https://gitee.com/YYDeament/88ybg
 * @date 2017/10/1 / 如果跳转到了 /signin页面 则表示 此类有问题。。真的是这个类 **/
public class SinaImpl extends AbstractOAuth2ApiBinding implements Sina {
	
	/** 根据 TOKEN 换取 用户ID 的地址。 */
	private static final String	URL_GET_OPENID		= "https://api.weibo.com/oauth2/get_token_info";
	/** 根据用户ID 请求 用户详细信息的地址 */
	private static final String	URL_GET_USERINFO	= "https://api.weibo.com/2/users/show.json";
	private String				appId;
	private String				id;
	private String				accessToken;
	
	public SinaImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		this.accessToken = accessToken;
		String result = getRestTemplate().postForObject(URL_GET_OPENID, null, String.class);
		String uid = StringUtils.substringBetween(result, "\"uid\":\"", "\"");
		setId(id);
	}
	
	@Override
	public SinaUserInfo getUserInfo() {
		String result = HttpUtil.get(URL_GET_USERINFO + "?access_token=" + accessToken + "&uid=" + this.id);
		SinaUserInfo bean = JSONObject.parseObject(result, SinaUserInfo.class);
		return bean;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
