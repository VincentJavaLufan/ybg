package com.ybg.core.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import com.ybg.base.util.Json;
import com.ybg.core.support.SocialUserInfo;
import com.ybg.rbac.user.domain.UserVO;
import io.swagger.annotations.ApiOperation;

@Controller
public class SocialController {
	
	@Autowired
	private ProviderSignInUtils	providerSignInUtils;
	@Autowired
	private RequestCache		requestCache	= new HttpSessionRequestCache();
	
	
	
	/** 当需要身份认证时，跳转到这里
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String requireAuthentication(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			return "redirect:" + targetUrl;
			// if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
			//
			// //redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			// }
		}
		map.put("msg", "访问的服务需要身份认证，请引导用户到登录页");
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/social/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
	
	
	@ApiOperation(value = "注册页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/common/login_do/toregister.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toregister() {
		return "/thirdregister";
	}
	
	// 第三方 登陆注册新用户绑定方式
	@ResponseBody
	@PostMapping("/common/thirdpart/register")
	public Json regist(UserVO user, HttpServletRequest request) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		if (connection.getKey().getProviderId() == null) {
			j.setMsg("操作失败，请返回主页从新操作！");
			j.setSuccess(false);
			return j;
			// 会话失效 或者是被攻击
		}
		// 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		return j;
	}
	
	// 第三方 账号绑定
	@ResponseBody
	@PostMapping("/common/thirdpart/bind")
	public Json bind(UserVO user, HttpServletRequest request) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		if (connection.getKey().getProviderId() == null) {
			j.setMsg("操作失败，请返回主页从新操作！");
			j.setSuccess(false);
			return j;
			// 会话失效 或者是被攻击
		}
		// 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		return j;
	}
}
