package com.ybg.weixin.login.controller;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.api.domain.WeixinOAuthConfig;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.ServletUtil;
import com.ybg.base.util.SystemConstant;
import com.ybg.base.util.VrifyCodeUtil;
import com.ybg.rbac.controllor.LoginProxyController;
import com.ybg.rbac.domain.Loginproxy;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.UserService;
import com.ybg.weixin.login.domain.WeixinUserVO;
import com.ybg.weixin.login.service.WeixinUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "微信登陆接口")
@Controller
@RequestMapping("/common/weixin_do/")
public class WeixinLoginController {
	
	@Autowired
	WeixinUserService		weixinUserService;
	@Autowired
	UserService				userService;
	@Autowired
	AuthenticationManager	authenticationManager;
	
	/** 此链接只能在微信中点击 **/
	@ApiOperation(value = "微信登陆鏈接", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin() {
		String appid = WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID);
		String preUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=";
		String domain = SystemConstant.getSystemdomain();
		String lastUrl = "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		String url = preUrl + domain + "/common/weixin_do/login.do" + lastUrl;
		return "redirect:" + url;
	}
	
	@ApiOperation(value = "微信APPid", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	@RequestMapping(value = "getAppid", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getAppid() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("domain", SystemConstant.getSystemdomain());
		map.put("appid", WeixinOAuthConfig.getValue(WeixinOAuthConfig.APPID));
		return map;
	}
	
	@ApiOperation(value = "微信登陆", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		String code = request.getParameter("code");
		String openID = weixinUserService.getOpen_id(code);
		if (!QvoConditionUtil.checkString(openID)) {
			return "";
		}
		WeixinUserVO qquser = weixinUserService.getByopenId(openID);
		if (qquser == null) {
			map.put("openid", openID);
			return "/thirdpartlogin/weixin/weixinbund";
		}
		UserVO user = userService.get(qquser.getUserid());
		if (user == null) {
			user = new UserVO();
		}
		Loginproxy proxy = LoginProxyController.login(request, user.getUsername(), new DesUtils().decrypt(user.getCredentialssalt()), null);
		if (proxy.isSuccess()) {
			return "redirect:" + proxy.getRedirecturl();
		}
		else {
			map.put("error", proxy.getResult());
			return "/login";
		}
	}
	
	@ApiOperation(value = "绑定微信账号页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "bund.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String weibobund(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		if (!VrifyCodeUtil.checkvrifyCode(request, map)) {
			return "/login";
		}
		String username = ServletUtil.getStringParamDefaultBlank(request, "username");
		String password = ServletUtil.getStringParamDefaultBlank(request, "password");
		String openid = ServletUtil.getStringParamDefaultBlank(request, "openid");
		if (openid.equals("")) {
			return null;
		}
		WeixinUserVO weibouser = weixinUserService.getByopenId(openid);
		if (weibouser != null) {
			return null;
		}
		UserVO user = userService.login(username);
		Loginproxy proxy = LoginProxyController.login(request, username, password, null);
		if (proxy.isSuccess()) {
			WeixinUserVO bean = new WeixinUserVO();
			bean.setOpenid(openid);
			bean.setUserid(user.getId());
			weixinUserService.create(bean);
			return "redirect:" + proxy.getRedirecturl();
		}
		else {
			map.put("error", proxy.getResult());
			return "/login";
		}
	}
}
