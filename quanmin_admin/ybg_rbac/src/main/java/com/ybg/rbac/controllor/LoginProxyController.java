package com.ybg.rbac.controllor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.rbac.domain.Loginproxy;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;

/** @author Deament */
public class LoginProxyController {
	
	/** @param httpServletRequest
	 * @param loginname
	 *            登陆名称
	 * @param password
	 *            登陆密码
	 * @param redirurl
	 *            跳转URL 默认 /common/login_do/index.do
	 * @return 返回结果类
	 * @throws AuthenticationException
	 * @throws Exception */
	public static Loginproxy login(HttpServletRequest httpServletRequest, String loginname, String password, String redirurl) throws AuthenticationException, Exception {
		Loginproxy loginproxy = new Loginproxy();
		UserService userService = (UserService) SpringContextUtils.getBean(UserService.class);
		UserVO user = userService.login(loginname);
		if (user == null) {
			loginproxy.setResult("用户或密码不正确！");
			return loginproxy;
		}
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		AuthenticationManager authenticationManager = (AuthenticationManager) SpringContextUtils.getBean(AuthenticationManager.class);
		if (!(user.isAccountNonLocked())) {
			loginproxy.setResult("用户已经被锁定不能绑定，请与管理员联系！");
			// map.put("error", "用户已经被锁定不能绑定，请与管理员联系！");
		}
		if (!user.isAccountNonExpired()) {
			loginproxy.setResult("账号未激活！");
			// map.put("error", "账号未激活！");
		}
		if (new DesUtils().encrypt(password).equals(user.getCredentialssalt())) {
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()));
			token2.setDetails(new WebAuthenticationDetails(httpServletRequest));
			Authentication authenticatedUser = authenticationManager.authenticate(token2);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			loginproxy.setSuccess(true);
			if (redirurl != null) {
				loginproxy.setRedirecturl(redirurl);
			}
			else {
				loginproxy.setRedirecturl("/common/login_do/index.do");
			}
		}
		else {
			// map.put("error", "用户或密码不正确！");
			loginproxy.setResult("用户或密码不正确！");
		}
		return loginproxy;
	}
}
