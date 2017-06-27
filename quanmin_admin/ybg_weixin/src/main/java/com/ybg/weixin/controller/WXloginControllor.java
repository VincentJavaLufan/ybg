package com.ybg.weixin.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.ServletUtil;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import com.ybg.weixin.domain.WXuser;
import com.ybg.weixin.service.WeixinUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("微信登陆接口")
@Controller
@RequestMapping(value = { "/common/weixin/login_do/" })
public class WXloginControllor extends WeiXinbaseControllor {
	
	@Autowired
	WeixinUserService		weixinUserService;
	@Autowired
	UserService				userService;
	@Autowired
	AuthenticationManager	authenticationManager;
	
	@ApiOperation(value = "微信登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, ModelMap map) throws Exception {
		String openID = getOpen_id(ServletUtil.getStringParamDefaultBlank(request, "code"));
		WXuser qquser = weixinUserService.getByopenId(openID);
		if (qquser == null) {
			map.put("openid", openID);
			return "/wx/bund";
		}
		UserVO user = userService.get(qquser.getUserid());
		if (user.getState().equals(UserStateConstant.LOCK)) {
			return "/lock";
		}
		if (user.getState().equals(UserStateConstant.DIE)) {
			return "/die";
		}
		if (!user.getState().equals(UserStateConstant.OK)) {
			return "";// XXX 返回错误的请求 比如账号封锁、未激活等状态；
		}
		/***/
		UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
		token2.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token2);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);/****/
		return "redirect:/common/login_do/index.do";
	}
	
	@ApiOperation(value = "绑定微信账号页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "bund.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String openid = ServletUtil.getStringParamDefaultBlank(request, "openid");
		if (openid.equals("")) {
			return null;
		}
		WXuser weibouser = weixinUserService.getByopenId(openid);
		if (weibouser != null) {
			return null;
		}
		request.removeAttribute("error");
		UserVO user = userService.login(username);
		if (!(user.isAccountNonLocked())) {
			request.setAttribute("error", "用户已经被锁定不能绑定，请与管理员联系！");
		}
		if (user.isAccountNonExpired()) {
			request.setAttribute("error", "账号未激活！");
		}
		if (new DesUtils().encrypt(password).equals(user.getPassword())) {
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
			token2.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token2);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			WXuser bean = new WXuser();
			bean.setOpenid(openid);
			bean.setUserid(user.getId());
			weixinUserService.create(bean);
			return "redirect:/common/login_do/index.do";
		}
		else {
			request.setAttribute("error", "用户或密码不正确！");
			return "/login";
		}
	}
	// @ApiOperation(value = "QQ登陆页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	// @RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	// public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws QQConnectException {
	// return "";
	// }
}
