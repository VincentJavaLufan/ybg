package com.ybg.core.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.Json;
import com.ybg.base.util.ServletUtil;
import com.ybg.base.util.SystemConstant;
import com.ybg.base.util.UserConstant;
import com.ybg.base.util.VrifyCodeUtil;
import com.ybg.component.email.sendemail.SendEmailInter;
import com.ybg.component.email.sendemail.SendQQmailImpl;
import com.ybg.core.support.SocialLoginService;
import com.ybg.core.support.SocialUserInfo;
import com.ybg.rbac.support.controller.LoginProxyController;
import com.ybg.rbac.support.domain.Loginproxy;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import io.swagger.annotations.ApiOperation;

@Controller
public class SocialController {
	
	@Autowired
	private ProviderSignInUtils	providerSignInUtils;
	private RequestCache		requestCache	= new HttpSessionRequestCache();
	@Autowired
	UserService					userService;
	@Autowired
	SocialLoginService			socialLoginService;
	
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
		// map.put("error", "访问的服务需要身份认证");
		// return "redirect:/";
		System.out.println("需要认证");
		return "/";
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
	
	@ApiOperation(value = "注册或者绑定页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/common/login_do/toregister.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toregister(HttpServletRequest request) throws Exception {
		// 如果已经绑定， 那就直接登陆吧
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		// 不知道那里配置权限到springsecurity 中了 。配置本地系统有问题。。
		List<String> userId = userService.findUserIdsWithConnection(connection);
		if (userId.size() == 1) {
			Loginproxy proxy = LoginProxyController.loginByUserId(request, userId.get(0), null);
			if (proxy.isSuccess()) {
				return "redirect:" + proxy.getRedirecturl();
			}
		}
		return "/thirdregister";
	}
	
	// 第三方 登陆注册新用户绑定方式
	@ResponseBody
	@PostMapping(value = { "/common/thirdpart/register", "/signup" })
	public Json regist(UserVO user, HttpServletRequest request, @RequestParam(name = "email", required = true) String email, @RequestParam(name = VrifyCodeUtil.PARAMETERNAME, required = true) String vrifyCode, HttpSession session) throws Exception {
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
		if (!VrifyCodeUtil.checkvrifyCode(vrifyCode, session)) {
			j.setSuccess(true);
			j.setMsg("验证码不正确！");
			return j;
		}
		j.setSuccess(true);
		j.setMsg("我们将发送邮箱到您的邮箱中进行验证，大约3小时左右不验证将删除注册信息");
		String now = DateUtil.getDateTime();
		user.setCredentialssalt(new DesUtils().encrypt(user.getPassword()));
		user.setPassword(UserConstant.getpwd(user.getPassword()));
		user.setRoleid("10");
		user.setPhone("");
		user.setState(UserStateConstant.DIE);
		user.setCreatetime(now);
		try {
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("创建失败，已存在该用户");
			return j;
		}
		String url = SystemConstant.getSystemdomain() + "/common/login_do/relife.do?userid=" + user.getId() + "&username=" + user.getUsername() + "&salt=" + user.getCredentialssalt();
		String contemt = this.getActiveContent(url, user.getUsername()); // 获取激活邮件的hmtl内容
		try {
			SendEmailInter send = new SendQQmailImpl();
			send.sendMail(email, SystemConstant.getSystemName() + "注册", contemt);
		} catch (Exception e) {
			e.printStackTrace();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", user.getId());
			userService.remove(wheremap);
			j.setMsg("发送邮箱失败，可能被提供方拦截，再试一次或者换一种邮箱类型");
			return j;
		}
		providerSignInUtils.doPostSignUp(user.getId(), new ServletWebRequest(request));
		return j;
	}
	
	// 第三方 账号绑定
	@ResponseBody
	@PostMapping("/common/thirdpart/bind")
	public Json bind(HttpServletRequest httpServletRequest, ModelMap map) throws Exception {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(httpServletRequest));
		if (connection.getKey().getProviderId() == null) {
			j.setMsg("操作失败，请返回主页从新操作！");
			j.setSuccess(false);
			return j;
			// 会话失效 或者是被攻击
		}
		// 首先检测验证码
		if (!VrifyCodeUtil.checkvrifyCode(httpServletRequest, map)) {
			j.setMsg("验证码错误");
			j.setSuccess(false);
			return j;
		}
		String username = ServletUtil.getStringParamDefaultBlank(httpServletRequest, "username");
		String password = ServletUtil.getStringParamDefaultBlank(httpServletRequest, "password");
		Loginproxy proxy = LoginProxyController.login(httpServletRequest, username, password, null);
		if (proxy.isSuccess()) {
			SocialUserInfo userInfo = new SocialUserInfo();
			userInfo.setProviderId(connection.getKey().getProviderId());
			userInfo.setProviderUserId(connection.getKey().getProviderUserId());
			userInfo.setNickname(connection.getDisplayName());
			userInfo.setHeadimg(connection.getImageUrl());
			providerSignInUtils.doPostSignUp(proxy.getUser().getId(), new ServletWebRequest(httpServletRequest));
			j.setMsg("绑定成功");
			j.setSuccess(true);
			return j;
		}
		else {
			j.setMsg(proxy.getResult());
			j.setSuccess(false);
			return j;
		}
	}
	
	/** 获取拼接的激活邮件的内容
	 * 
	 * @param url
	 *            激活链接
	 * @param username
	 *            用户名
	 * @return 字符串形的邮件内容 */
	private String getActiveContent(String activeurl, String username) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head>");
		buffer.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");
		buffer.append("<base target=\"_blank\" />");
		buffer.append("</head>");
		buffer.append("<body>尊敬的 ，");
		buffer.append(username);
		buffer.append(" 您好！<br>");
		buffer.append("请点击");
		buffer.append("<a href=" + activeurl + ">激活</a>");
		buffer.append("激活您的账号,<br>");
		buffer.append("为保障您的帐号安全，请在3小时内点击该链接<br>");
		buffer.append("如无法点击请您将下面链接<br><span style=\"color:blue\">" + activeurl + "</span><br>复制到浏览器地址栏访问。 若如果您已激活，请忽略本邮件，由此给您带来的不便请谅解。<br><br><br>");
		buffer.append("本邮件由系统自动发出，请勿直接回复！ ");
		buffer.append("</body></html>");
		return buffer.toString();
	}
}
