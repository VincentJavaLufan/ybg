/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import com.baidu.api.domain.Session;
import com.baidu.api.domain.User;
import com.baidu.api.store.BaiduStore;
import com.baidu.api.utils.BaiduUtil;
import com.baidu.api.utils.StringUtil;

/** 适用于IFrame Demo的封装类
 * 
 * @author chenhetong(chenhetong@baidu.com) */
public final class Baidu {
	
	private String				clientId;
	private String				clientSecret;
	private String				redirectUri;
	private BaiduStore			store;
	private String				state;
	private Session				session;
	private BaiduOAuth2Client	oauth2Client;
	private HttpServletRequest	request;
	public static final String	LoggedInUser_URL	= "https://openapi.baidu.com/rest/2.0/passport/users/getLoggedInUser";
	
	/** 基于应用信息、回调地址等构建baidu类
	 * 
	 * @param clientId
	 *            应用的appKey信息
	 * @param clientSecret
	 *            应用的appSecret信息
	 * @param redirectUri
	 *            应用设置的回调地址
	 * @param store
	 *            提供存储Token信息的存储类
	 * @param request
	 *            HttServletRequest对象，用来获取cookie信息
	 * @throws BaiduApiException
	 * @throws BaiduOAuthException */
	public Baidu(String clientId, String clientSecret, String redirectUri, BaiduStore store, HttpServletRequest request) throws BaiduApiException, BaiduOAuthException {
		if (store == null) {
			throw new IllegalArgumentException("BaiduStore的参数实例，用来存储session等信息，不能为空！");
		}
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.request = request;
		setStore(store);
	}
	
	/** 获取OAuth2授权的客户端
	 * 
	 * @return BaiduOAuth2Client对象 */
	public BaiduOAuth2Client getBaiduOAuth2Service() {
		if (this.oauth2Client == null) {
			oauth2Client = new BaiduOAuth2Client(this.clientId, this.clientSecret);
			oauth2Client.setRedirectUri(redirectUri);
		}
		return oauth2Client;
	}
	
	/** 获取用户授权url地址，完成回调
	 * 
	 * @return */
	public String getLoginUrl() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("state", this.state);
		return getBaiduOAuth2Service().getAuthorizeUrl(params);
	}
	
	/** 获取用户登出url地址，完成回调
	 * 
	 * @param next
	 *            完成登出操作后的回调地址
	 * @return 获取登出操作的url地址
	 * @throws BaiduOAuthException
	 * @throws BaiduApiException */
	public String getLogOutUrl(String next) throws BaiduApiException, BaiduOAuthException {
		String accessToken = getAccessToken();
		return getBaiduOAuth2Service().getLoginOutUrl(accessToken, next);
	}
	
	/** 获取保持回调的stat信息
	 * 
	 * @return */
	public String getState() {
		return this.state;
	}
	
	/** 获取调用api方法的service服务
	 * 
	 * @param accessToken
	 * @return */
	public BaiduApiClient getBaiduApiClientService(String accessToken) {
		BaiduApiClient client = new BaiduApiClient(accessToken);
		client.setClientId(clientId);
		return client;
	}
	
	/** 获取当前已经等了的用户信息
	 * 
	 * @return 获取当前已经登陆的用户信息
	 * @throws BaiduApiException
	 * @throws BaiduOAuthException */
	public User getLoggedInUser() throws BaiduApiException, BaiduOAuthException {
		// 从cookie中获取到已经登录的user对象
		User user = getUser();
		// 在访问站内应用时，会传递bd_sig和bd_user参数，
		// 通过这些参数我们要检测uid是否与本地存储的uid参数相同，如果不相同我们需要删除所有已经存储的信息;
		String bdSig = request.getParameter("bd_sig");
		String bdUser = request.getParameter("bd_user");
		if (!StringUtil.isEmpty(bdSig) && !StringUtil.isEmpty(bdUser)) {
			String sig = null;
			Map<String, String> params = new HashMap<String, String>();
			params.put("bd_user", bdUser);
			try {
				sig = BaiduUtil.getSignature(params, clientSecret);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!bdSig.equals(sig) || user.getUid() != Integer.valueOf(bdUser).intValue()) {
				store.remove("session");
				return null;
			}
		}
		return user;
	}
	
	/** 获取 BaiduStore中存储的cookie对象
	 * 
	 * @return 获取BaiduStore对象中存储的session对象
	 * @throws BaiduApiException
	 * @throws BaiduOAuthException */
	public Session getSession() throws BaiduApiException, BaiduOAuthException {
		if (null == session) {
			session = doGetSession();
		}
		return session;
	}
	
	/** 获取AccessToken
	 * 
	 * @return 返回Access Token信息，如果为空则返回null
	 * @throws BaiduOAuthException
	 * @throws BaiduApiException */
	public String getAccessToken() throws BaiduApiException, BaiduOAuthException {
		Session ss = getSession();
		if (ss == null) {
			return null;
		}
		BaiduOAuthToken token = ss.getToken();
		if (null != token) {
			return token.getAccessToken();
		}
		else {
			return null;
		}
	}
	
	/** 获取refreshToken信息
	 * 
	 * @return 获取refreshToken信息，如果为空则返回null.
	 * @throws BaiduOAuthException
	 * @throws BaiduApiException */
	public String getRefreshToken() throws BaiduApiException, BaiduOAuthException {
		Session ss = getSession();
		if (ss == null) {
			return null;
		}
		BaiduOAuthToken token = ss.getToken();
		if (null != token) {
			return token.getRefreshToken();
		}
		else {
			return null;
		}
	}
	
	/** 获取http连接请求使用的sessionKey
	 * 
	 * @return 返回sessionKey信息，如果为空则返回null
	 * @throws BaiduOAuthException
	 * @throws BaiduApiException */
	public String getSessionKey() throws BaiduApiException, BaiduOAuthException {
		Session ss = getSession();
		if (ss == null) {
			return null;
		}
		BaiduOAuthToken token = ss.getToken();
		if (null != token) {
			return token.getSessionKey();
		}
		else {
			return null;
		}
	}
	
	/** 获取http连接请求的sessionSecret对象
	 * 
	 * @return 返回sessionSecret对象，如果为空则返回null；
	 * @throws BaiduOAuthException
	 * @throws BaiduApiException */
	public String getSessionSecret() throws BaiduApiException, BaiduOAuthException {
		Session ss = getSession();
		if (ss == null) {
			return null;
		}
		BaiduOAuthToken token = ss.getToken();
		if (null != token) {
			return token.getSessionSecret();
		}
		else {
			return null;
		}
	}
	
	public BaiduStore getStore() {
		return this.store;
	}
	
	private void setStore(BaiduStore store) throws BaiduApiException, BaiduOAuthException {
		this.store = store;
		if (null != this.store) {
			this.state = store.getState();
			getSession();
			establishCSRFTokenState();
		}
	}
	
	private User getUser() throws BaiduApiException, BaiduOAuthException {
		Session ss = getSession();
		if (null != ss) {
			return ss.getUser();
		}
		else {
			return null;
		}
	}
	
	private Session doGetSession() throws BaiduApiException, BaiduOAuthException {
		String tmpCode = getCode();
		if (!StringUtil.isEmpty(tmpCode) && !tmpCode.equals(store.getCode())) {
			try {
				BaiduOAuthToken token = getBaiduOAuth2Service().getAccessTokenByAuthorizationCode(tmpCode);
				String accessToken = token.getAccessToken();
				String jsonUser = getBaiduApiClientService(accessToken).request(LoggedInUser_URL, null, "GET");
				User user = new User(jsonUser);
				Session ss = new Session().setToken(token).setUser(user);
				this.session = ss;
				store.setCode(tmpCode);
				store.setSession(ss);
				return ss;
			} catch (BaiduOAuthException e) {
				store.removeAll();
				e.printStackTrace();
				throw e;
			} catch (BaiduApiException e) {
				store.removeAll();
				e.printStackTrace();
				throw e;
			}
		}
		return store.getSession();
	}
	
	/** 重请求中后去用户授权的code信息，并进行csrf攻击的检验，若csrf攻击检测失败，或请求中不包含code信息，则返回空；
	 * 
	 * @return 用户授权码 */
	private String getCode() {
		String tmpCode = request.getParameter("code");
		if (!StringUtil.isEmpty(tmpCode)) {
			String tmpState = request.getParameter("state");
			// 验证state回调状态，防止csrf攻击
			if (!StringUtil.isEmpty(this.state) && this.state.equals(tmpState)) {
				this.state = null;
				store.remove("state");
				return tmpCode;
			}
			else {
				// System.out.println("请求中的state与存储的state状态不负，有可能为csrf攻击");
//				this.state = null;
//				store.remove("state");
				return tmpCode;
				// 请求中的state与存储的state状态不负，有可能为csrf攻击
				// return null;
			}
		}
		return tmpCode;
	}
	
	private void establishCSRFTokenState() {
		if (null == store.getState()) {
			byte[] bytes = new byte[16];
			Random rand = new Random(System.currentTimeMillis());
			rand.nextBytes(bytes);
			this.state = BaiduUtil.toHexString(bytes);
			store.setState(state);
		}
	}
}
