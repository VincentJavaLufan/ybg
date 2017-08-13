package com.baidu.oauth.controllor;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.baidu.api.Baidu;
import com.baidu.api.BaiduApiClient;
import com.baidu.api.BaiduApiException;
import com.baidu.api.BaiduOAuthException;
import com.baidu.api.service.IUserService;
import com.baidu.api.service.impl.UserServiceImpl;
import com.baidu.api.store.BaiduCookieStore;
import com.baidu.api.store.BaiduStore;
import com.baidu.oauth.domain.BaiduUser;
import com.baidu.oauth.service.BaiduUserService;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.ServletUtil;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 百度授权认证 **/
@Api("百度登陆接口")
@Controller
@RequestMapping(value = { "/common/baidu_do/" })
public class BaiduControllor {
	
	private Logger			logger	= Logger.getLogger(getClass());
	@Autowired
	UserService				userService;
	@Autowired
	BaiduUserService		baiduUserService;
	@Autowired
	AuthenticationManager	authenticationManager;
	
	@ApiOperation(value = "百度登陆跳转", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(HttpServletRequest request, HttpServletResponse response) {
		BaiduStore store = new BaiduCookieStore(Oauth.getClientID(), request, response);
		Baidu baidu = null;
		try {
			baidu = new Baidu(Oauth.getClientID(), Oauth.getClient_SERCRET(), Oauth.getCallBackUrl(), store, request);
			String state = baidu.getState();
			Map<String, String> params = new HashMap<String, String>();
			params.put("state", state);
			String authorizeUrl = baidu.getBaiduOAuth2Service().getAuthorizeUrl(params);
			// response.sendRedirect(authorizeUrl);
			return "redirect:" + authorizeUrl;
		} catch (BaiduOAuthException e) {
			e.printStackTrace();
			logger.debug("BaiduOAuthException ", e);
		} catch (BaiduApiException e) {
			e.printStackTrace();
			logger.debug("BaiduApiException ", e);
		}
		return null;
	}
	
	@ApiOperation(value = "百度账号登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		BaiduStore store = new BaiduCookieStore(Oauth.getClientID(), request, response);
		Baidu baidu = null;
		String accessToken = "";
		String refreshToken = "";
		String sessionKey = "";
		String sessionSecret = "";
		com.baidu.api.domain.User loggedInUser = null;
		try {
			baidu = new Baidu(Oauth.getClientID(), Oauth.getClient_SERCRET(), Oauth.getCallBackUrl(), store, request);
			accessToken = baidu.getAccessToken();
			refreshToken = baidu.getRefreshToken();
			sessionKey = baidu.getSessionKey();
			sessionSecret = baidu.getSessionSecret();
			loggedInUser = baidu.getLoggedInUser();
		} catch (BaiduApiException e) {
			e.printStackTrace();
			logger.debug("BaiduApiException", e);
		} catch (BaiduOAuthException e) {
			e.printStackTrace();
			logger.debug("BaiduOAuthException ", e);
		}
		map.put("accessToken", accessToken);
		map.put("refreshToken", refreshToken);
		map.put("sessionKey", sessionKey);
		map.put("sessionSecret", sessionSecret);
		if (loggedInUser != null) {
			map.put("baiduUser", loggedInUser);
		}
		String token = getAccessToken(response, request);
		IUserService userService = new UserServiceImpl(new BaiduApiClient(token));
		try {
			loggedInUser = userService.getLoggedInUser();
		} catch (BaiduApiException e) {
			e.printStackTrace();
		}
		BaiduUser weibouser = baiduUserService.getByUid(loggedInUser.getUid());
		if (weibouser == null) {
			map.put("uid", loggedInUser.getUid());
			return "/baidu/baidubund";
		}
		UserVO user = this.userService.get(weibouser.getUserid());
		//XXX 可能綁定的用戶已刪除
		if (user.getState().equals(UserStateConstant.LOCK)) {
			return "/lock";
		}
		if (user.getState().equals(UserStateConstant.DIE)) {
			return "/die";
		}
		if (!user.getState().equals(UserStateConstant.OK)) {
			return "";// 返回错误的请求 比如账号封锁、未激活等状态；
		}
		UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
		token2.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token2);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		return "redirect:/common/login_do/index.do";
	}
	
	private String getAccessToken(HttpServletResponse response, HttpServletRequest request) {
		BaiduStore store = new BaiduCookieStore(Oauth.getClientID(), request, response);
		com.baidu.api.domain.Session session = store.getSession();
		if (session == null) {
			try {
				// response.sendRedirect("index");
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return session.getToken().getAccessToken();
	}
	
	@ApiOperation(value = "无绑定账号。申请一个绑定账号", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "bund.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String uid = ServletUtil.getStringParamDefaultBlank(request, "uid").replaceAll(",", "");
		if (uid.equals("")) {
			return null;
		}
		BaiduUser baiduuser = baiduUserService.getByUid(Long.parseLong(uid));
		if (baiduuser != null) {
			return null;
		}
		baiduuser = new BaiduUser();
		UserVO user = userService.login(username);
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!(user.isAccountNonLocked())) {
			map.put("error", "用户已经被锁定不能绑定，请与管理员联系！");
		}
		if (user.isAccountNonExpired()) {
			map.put("error", "账号未激活！");
		}
		if (new DesUtils().encrypt(password).equals(user.getCredentialssalt())) {
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
			token2.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token2);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			baiduuser.setUid(Long.parseLong(uid));
			baiduuser.setUserid(user.getId());
			baiduUserService.create(baiduuser);
			return "redirect:/common/login_do/index.do";
		}
		else {
			request.setAttribute("error", "用户或密码不正确！");
			return "/login";
		}
	}
}
