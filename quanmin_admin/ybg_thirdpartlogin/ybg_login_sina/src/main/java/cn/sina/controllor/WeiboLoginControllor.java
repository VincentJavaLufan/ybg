package cn.sina.controllor;

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
import com.ybg.base.util.VrifyCodeUtil;
import com.ybg.rbac.controllor.LoginProxyController;
import com.ybg.rbac.domain.Loginproxy;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.LoginService;
import com.ybg.rbac.user.service.UserService;

import cn.sina.domain.WeiboUserVO;
import cn.sina.service.WeiboUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

/** 微博登陆 **/
@Api(tags = "微博登陆")
@Controller
@RequestMapping("/common/weibo/sinalogin_do/")
public class WeiboLoginControllor {

	@Autowired
	WeiboUserService weiboUserService;
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginservice;
	@Autowired
	AuthenticationManager authenticationManager;

	@ApiOperation(value = "微博登陆页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(HttpServletRequest request, HttpServletResponse response) throws WeiboException {
		Oauth oauth = new Oauth();
		return "redirect:" + oauth.authorize("code");
	}

	@ApiOperation(value = "微博登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		Oauth oauth = new Oauth();
		String code = ServletUtil.getStringParamDefaultBlank(request, "code");
		AccessToken token = oauth.getAccessTokenByCode(code);
		// 查询绑定表中是否有绑定记录 没有则跳转到绑定账号 要求输入账号密码
		WeiboUserVO weibouser = weiboUserService.get(token.getUid());
		if (weibouser == null) {
			map.put("uid", token.getUid());
			return "/thirdpartlogin/weibo/weibobund";
		}
		UserVO user = userService.get(weibouser.getUserid());
		if(user==null){
			user=new UserVO();
		}
		Loginproxy proxy = LoginProxyController.login(request, user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()), null);
		if (proxy.isSuccess()) {

			return "redirect:" + proxy.getRedirecturl();
		} else {
			map.put("error", proxy.getResult());
			return "/login";
		}
	}

	@ApiOperation(value = "微博绑定账号页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "bund.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		if (!VrifyCodeUtil.checkvrifyCode(request, map)) {
			return "/login";
		}
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String uid = ServletUtil.getStringParamDefaultBlank(request, "uid");
		if (uid.equals("")) {
			return null;
		}
		WeiboUserVO weibouser = weiboUserService.get(uid);
		if (weibouser != null) {
			return null;
		}
		UserVO user = userService.login(username);
		Loginproxy proxy = LoginProxyController.login(request, username, password, null);
		if (proxy.isSuccess()) {
			WeiboUserVO bean = new WeiboUserVO();
			bean.setUid(uid);
			bean.setUserid(user.getId());
			weiboUserService.create(bean);
			return "redirect:" + proxy.getRedirecturl();
		} else {
			map.put("error", proxy.getResult());
			return "/login";
		}

	}
}
