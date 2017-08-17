package com.ybg.mayun.oauth.controller;
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
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.ServletUtil;
import com.ybg.mayun.api.Mayun;
import com.ybg.mayun.oauth.domain.MayunUserVO;
import com.ybg.mayun.oauth.service.MayunUserService;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "码云登陆API")
@Controller
@RequestMapping("/commom/mayun_do/")
public class MayunLoginController {
	
	@Autowired
	MayunUserService		mayunUserService;
	@Autowired
	UserService				userService;
	@Autowired
	AuthenticationManager	authenticationManager;
	
	@ApiOperation(value = "码云登陆跳转", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(HttpServletRequest request, HttpServletResponse response) {
		Mayun mayun = new Mayun(MayunConfig.getValue(MayunConfig.client_ID), MayunConfig.getValue(MayunConfig.client_SERCRET), MayunConfig.getValue(MayunConfig.redirect_URI));
		String authorizeUrl = mayun.getLoginURL();
		return "redirect:" + authorizeUrl;
	}
	
	@ApiOperation(value = "码云账号登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		String code = ServletUtil.getStringParamDefaultBlank(request, "code");
		String access_token = mayunUserService.getAccessToken(code);
		if (access_token == null) {
			return "";
		}
		Long id = mayunUserService.getMayunUserIdByToken(access_token);
		if (QvoConditionUtil.checkLong(id)) {
			MayunUserVO qvo = new MayunUserVO();
			qvo.setMayunid(id + "");
			MayunUserVO mayunuser = mayunUserService.getUserByMayunId(id + "");
			if (mayunuser == null) {
				map.put("mayunid", id);
				return "/thirdpartlogin/mayun/mayunbund";
			}
			UserVO user = this.userService.get(mayunuser.getUserid());
			// XXX 可能綁定的用戶已刪除
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
		return "";
	}
	
	@ApiOperation(value = "无绑定账号。申请一个绑定账号", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "bund.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String mayunid = ServletUtil.getStringParamDefaultBlank(request, "mayunid").replaceAll(",", "");
		if (mayunid.equals("")) {
			return null;
		}
		MayunUserVO mayunuser = mayunUserService.getUserByMayunId(mayunid);
		if (mayunuser != null) {
			return null;
		}
		mayunuser = new MayunUserVO();
		UserVO user = userService.login(username);
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!(user.isAccountNonLocked())) {
			map.put("error", "用户已经被锁定不能绑定，请与管理员联系！");
		}
		if (!user.isAccountNonExpired()) {
			map.put("error", "账号未激活！");
		}
		if (new DesUtils().encrypt(password).equals(user.getCredentialssalt())) {
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
			token2.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token2);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			mayunuser.setMayunid(mayunid);
			mayunuser.setUserid(user.getId());
			mayunUserService.create(mayunuser);
			return "redirect:/common/login_do/index.do";
		}
		else {
			request.setAttribute("error", "用户或密码不正确！");
			return "/login";
		}
	}
}
