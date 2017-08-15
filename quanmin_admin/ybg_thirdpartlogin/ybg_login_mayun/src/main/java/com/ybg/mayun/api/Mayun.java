package com.ybg.mayun.api;
import javax.servlet.http.HttpServletRequest;

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
	
	/** 获取登陆地址 **/
	public String getLoginURL() {
		return LoggedInUser_URL + "?client_id=" + getClientId() + "&redirect_uri=" + getRedirectUri() + "&response_type=code";
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
