/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.baidu.api.utils.BaiduUtil;
import com.baidu.api.utils.HttpUtil;

/** 获取应用授权的OAuth客户端类
 * 
 * @author chenhetong(chenhetong@baidu.com) */
public class BaiduOAuth2Client {
	
	private final static String	AUTHORIZE		= "https://openapi.baidu.com/oauth/2.0/authorize";
	private final static String	TOKEN			= "https://openapi.baidu.com/oauth/2.0/token";
	private final static String	LOGOUT			= "https://openapi.baidu.com/connect/2.0/logout";
	private final static int	CONNECTTIMEOUT	= 5000;
	private final static int	READTIMEOUT		= 5000;
	private final static String	DEFAULTCHAESET	= "UTF-8";
	private String				clientId;
	private String				clientSecret;
	private String				redirectUri;
	
	/** 通过应用的基本信息构建OAuth客户端类
	 * 
	 * @param clientId
	 *            应用注册的id
	 * @param clientSecret
	 *            应用注册的密钥 */
	public BaiduOAuth2Client(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	/** 获得申请授权码Authorization_Code的URL
	 * 
	 * @return 申请获取Authorization Code的URL地址，默认返回scope为basic的权限 */
	public String getAuthorizeUrl() {
		return _getAuthorizationCode(this.clientId, this.redirectUri, null);
	}
	
	/** 获取用户授权码的url地址
	 * 
	 * @param params
	 *            <pre>
	 * redirect_uri : 应用回调地址
	 * response_type : code
	 * scope :通过空格分隔的请求权限，默认为basic super_msg
	 * display:授权信息展示页面类型，'page', 'popup', 'touch' or 'mobile'
	 * state : state 参数用于保持回调状态，防止csrf攻击
	 * </pre>
	 * @return 根据params参数设定返回获取用户授权码的url地址 */
	public String getAuthorizeUrl(Map<String, String> params) {
		return _getAuthorizationCode(this.clientId, this.redirectUri, params);
	}
	
	private String _getAuthorizationCode(String clientId, String redirectUri, Map<String, String> params) {
		String retStr = "";
		Map<String, String> basic = new HashMap<String, String>();
		basic.put("client_id", clientId);
		basic.put("response_type", "code");
		basic.put("redirect_uri", redirectUri);
		if (params != null && !params.isEmpty()) {
			basic.putAll(params);
		}
		String query = HttpUtil.buildQuery(basic, DEFAULTCHAESET);
		retStr = AUTHORIZE + "?" + query;
		return retStr;
	}
	
	/** 获得用户进行登出操作的地址
	 * 
	 * @param accessToken
	 *            当前用户的AccessToken信息
	 * @param next
	 *            用户登出后要跳转的url地址
	 * @return 返回登出操作的url地址 */
	public String getLoginOutUrl(String accessToken, String next) {
		Map<String, String> params = new HashMap<String, String>();
		if (accessToken == null || next == null) {
			return null;
		}
		params.put("access_token", accessToken);
		params.put("next", next);
		String retStr = LOGOUT + "?" + HttpUtil.buildQuery(params, DEFAULTCHAESET);
		return retStr;
	}
	
	/** 使用Authorization_code来获取Access Token。
	 * 
	 * @param code
	 *            授权码Authorization Code
	 * @return AccessToken对象的封装。
	 * @throws BaiduOAuthException
	 *             OAuthException异常类 */
	public BaiduOAuthToken getAccessTokenByAuthorizationCode(String code) throws BaiduOAuthException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		param.put("code", code);
		param.put("client_id", this.clientId);
		param.put("client_secret", this.clientSecret);
		param.put("redirect_uri", this.redirectUri);
		return makeOAuthAccessToken(TOKEN, param);
	}
	
	/** 使用应用公钥、密钥获取Access Token(只能用于访问与用户无关的Open API)
	 * 
	 * @param apiKey
	 *            应用的API Key
	 * @param apiSecret
	 *            应用的Secret Key
	 * @param scope
	 *            以空格分隔的权限列表，采用本方式获取Access Token时只能申请跟用户数据无关的数据访问权限。 关于权限的具体信息请参考<a href= "http://dev.baidu.com/wiki/connect/index.php?title=%E6%9D%83%E9%99%90%E5%88%97%E8%A1%A8" >权限列表</a>。
	 * @return AccessToken对象的封装
	 * @throws OAuthException异常类 */
	public BaiduOAuthToken getAccessTokenByClientCredentials(String scope) throws BaiduOAuthException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "client_credentials");
		param.put("client_id", this.clientId);
		param.put("client_secret", this.clientSecret);
		if (scope != null && !"".equals(scope.trim())) {
			param.put("scope", scope);
		}
		return makeOAuthAccessToken(TOKEN, param);
	}
	
	/** 使用Refresh Token来获取Access Token
	 * 
	 * @param refreshToken
	 *            用于涮新Access Token用的Refresh Token
	 * @param scope
	 *            非必须参数，以空格分隔的权限列表，若不传递此参数（null），代表请求的数据访问操作权限与上次获取Access Token时一致。 通过Refresh Token刷新Access Token时所要求的scope权限范围必须小于等于上次获取Access Token时授予的权限范围。 关于权限的具体信息请参考“<a href= "http://dev.baidu.com/wiki/connect/index.php?title=%E6%9D%83%E9%99%90%E5%88%97%E8%A1%A8" >权限列表</a>”
	 * @return AccessToken对象的封装信息。
	 * @throws BaiduOAuthException
	 *             OAuthException异常类 */
	public BaiduOAuthToken getAccessTokenByRefreshToken(String refreshToken, String scope) throws BaiduOAuthException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "refresh_token");
		param.put("client_id", this.clientId);
		param.put("client_secret", this.clientSecret);
		if (scope != null && !"".equals(scope)) {
			param.put("scope", scope);
		}
		param.put("refresh_token", refreshToken);
		return makeOAuthAccessToken(TOKEN, param);
	}
	
	/** 使用用户名和密码获得AccessToken对象
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param scope
	 *            请求的权限
	 * @return BaiduOAuthToken 对象，封装了token的具体信息
	 * @throws BaiduOAuthException */
	public BaiduOAuthToken getAccessTokenByPasswordCredentials(String username, String password, String scope) throws BaiduOAuthException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", this.clientId);
		params.put("client_secret", this.clientSecret);
		params.put("grant_type", "password");
		params.put("username", username);
		params.put("password", password);
		params.put("scope", scope);
		return makeOAuthAccessToken(TOKEN, params);
	}
	
	private BaiduOAuthToken makeOAuthAccessToken(String url, Map<String, String> params) throws BaiduOAuthException {
		BaiduOAuthToken accessToken = null;
		String jsonResult = null;
		try {
			jsonResult = HttpUtil.doPost(url, params, CONNECTTIMEOUT, READTIMEOUT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (jsonResult != null) {
			BaiduUtil.checkOAuthResponse(jsonResult);
			accessToken = new BaiduOAuthToken(jsonResult);
		}
		return accessToken;
	}
	
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}
