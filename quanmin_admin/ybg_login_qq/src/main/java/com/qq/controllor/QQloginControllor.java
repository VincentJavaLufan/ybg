package com.qq.controllor;
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
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.domain.QQuser;
import com.qq.service.QQuserService;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.ServletUtil;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("QQ登陆接口")
@Controller
@RequestMapping(value = { "/common/qq/login_do/" })
public class QQloginControllor {
	
	@Autowired
	QQuserService			qQuserService;
	@Autowired
	UserService				userService;
	@Autowired
	AuthenticationManager	authenticationManager;
	
	@ApiOperation(value = "QQ登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
		String accessToken = null, openID = null;
		long tokenExpireIn = 0L;
		if (accessTokenObj.getAccessToken().equals("")) {
			// 我们的网站被CSRF攻击了或者用户取消了授权
			// 做一些数据统计工作
			System.out.print("没有获取到响应参数");
		}
		else {
			accessToken = accessTokenObj.getAccessToken();
			tokenExpireIn = accessTokenObj.getExpireIn();
			request.getSession().setAttribute("demo_access_token", accessToken);
			request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
			// 利用获取到的accessToken 去获取当前用的openid -------- start
			OpenID openIDObj = new OpenID(accessToken);
			openID = openIDObj.getUserOpenID();
			QQuser qquser = qQuserService.getByopenId(openID);
			if (qquser == null) {
				map.put("openid", openID);
				return "/qq/qqbund";
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
		return null;
	}
	
	@ApiOperation(value = "绑定QQ账号页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "bund.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String openid = ServletUtil.getStringParamDefaultBlank(request, "openid");
		if (openid.equals("")) {
			return null;
		}
		QQuser weibouser = qQuserService.getByopenId(openid);
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
			QQuser bean = new QQuser();
			bean.setOpenid(openid);
			bean.setUserid(user.getId());
			qQuserService.create(bean);
			return "redirect:/common/login_do/index.do";
		}
		else {
			request.setAttribute("error", "用户或密码不正确！");
			return "/login";
		}
	}
	
	@ApiOperation(value = "QQ登陆页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws QQConnectException {
		return "redirect:" + new Oauth().getAuthorizeURL(request);
	}
}
