package com.ybg.social.sina.api;
import java.util.Map;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.http.HttpUtil;
//如果跳转到了  /signin页面 则表示 此类有问题。。真的是这个类
public class SinaImpl extends AbstractOAuth2ApiBinding implements Sina {
	//根据 TOKEN 换取 用户ID 的地址。
	private static final String	URL_GET_OPENID		= "https://api.weibo.com/oauth2/get_token_info";
	//根据用户ID 请求 用户详细信息的地址
	private static final String	URL_GET_USERINFO	= "https://api.weibo.com/2/users/show.json";
	private String				appId;
	private String				id;
	private String  accessToken;
	public SinaImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		System.out.println("16:SinaImpl");
		this.appId = appId;
		this.accessToken=accessToken;
		String result = getRestTemplate().postForObject(URL_GET_OPENID,null, String.class);
		System.out.println(result+":20SinaImpl");
		this.id = new JSONObject().parseObject(result, Map.class).get("uid").toString();
		System.out.println("22:SinaImpl"+id);
	}
	
	@Override
	public SinaUserInfo getUserInfo() {
		System.out.println("28:" + URL_GET_USERINFO+"?access_token="+accessToken+"&uid="+this.id);
		String result = HttpUtil.get(URL_GET_USERINFO+"?access_token="+accessToken+"&uid="+this.id);
		System.out.println("28:" + result);
		SinaUserInfo bean = JSONObject.parseObject(result, SinaUserInfo.class);
		
		
		return bean;
	}
}
