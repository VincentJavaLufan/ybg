package com.ybg.mayun.api;
import javax.servlet.http.HttpServletRequest;
import com.ybg.base.util.ReplaceDomainUtil;

public class Mayun {
	
	private String				clientId;
	private String				clientSecret;
	private String				redirectUri;
	private HttpServletRequest	request;
	public static final String	LoggedInUser_URL	= "https://git.oschina.net/oauth/authorize";
	
	public Mayun(String clientId, String clientSecret, String redirectUri) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
	}
	
	/** 获取登陆地址 会强制替换系统域名 请在systemconstant.properties 中设置正式域名**/
	public String getLoginURL() {
		return LoggedInUser_URL + "?client_id=" + getClientId() + "&redirect_uri=" + ReplaceDomainUtil.replacedomain(getRedirectUri())  + "&response_type=code";
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	public String getRedirectUri() {
		return redirectUri;
	}
	
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
