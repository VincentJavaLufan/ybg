package com.ybg.rbac.controllor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.Common;
import com.ybg.base.util.DesUtils;
import com.ybg.base.util.Json;
import com.ybg.base.util.SystemConstant;
import com.ybg.component.email.sendemail.SendEmailInter;
import com.ybg.component.email.sendemail.SendQQmailImpl;
import com.ybg.config.security.MyAuthenticationToken;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.user.UserStateConstant;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;
import com.ybg.rbac.user.service.LoginService;
import com.ybg.rbac.user.service.UserService;

/*** 用Shiro登陆 **/
@Api("平台登录操作")
@Controller
public class LoginControllor {
	
	@Autowired
	UserService			userService;
	@Autowired
	ResourcesService	resourcesService;
	@Autowired
	LoginService		loginService;
	
	@ApiOperation(value = "登录页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/common/login_do/tologin.do", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(ModelMap map) {
		map.put("icp", SystemConstant.getICP());
		map.put("systemname", SystemConstant.getSystemName());
		map.put("systemdomain", SystemConstant.getSystemdomain());
		return "/login";
	}
	
	@ApiOperation(value = "退出系统 ", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "/common/login_do/loginout.do", method = RequestMethod.GET)
	public String loginout() {
		// 使用权限管理工具进行用户的退出，注销登录
		// session 会销毁，在SessionListener监听session销毁，清理权限缓存
		// SecurityUtils.getSubject().logout();
		return "redirect:/common/login_do/tologin.do";
	}
	
	@ApiOperation(value = "登录系统 ", notes = "", produces = MediaType.ALL_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "帐号", dataType = "java.lang.String", required = true), @ApiImplicitParam(name = "password", value = "密码", dataType = "java.lang.String", required = true) })
	@RequestMapping(value = "/common/login_do/login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth instanceof MyAuthenticationToken) {
				Object pinciba = auth.getPrincipal();
				if (pinciba instanceof UserDetails) {
					UserDetails userDetail = ((UserDetails) pinciba);
					model.addAttribute("username", userDetail.getUsername());
					UserQuery qvo = new UserQuery();
					qvo.setUsername(userDetail.getUsername());
					UserVO u = userService.list(qvo).get(0);
					model.addAttribute("realName", u.getUsername());
					return "redirect:/common/login_do/index.do";
				}
			}
			else if (auth instanceof AnonymousAuthenticationToken) {
				model.addAttribute("error", "用户或密码不正确！");
				return "/login";
			}
			else {
				// 获取用户登录权限详细
				Object pinciba = auth.getPrincipal();
				if (pinciba instanceof UserDetails) {
					UserDetails userDetail = ((UserDetails) pinciba);
					model.addAttribute("username", userDetail.getUsername());
					UserQuery qvo = new UserQuery();
					qvo.setUsername(userDetail.getUsername());
					UserVO u = userService.list(qvo).get(0);
					model.addAttribute("realName", u.getUsername());
				}
				// 登录成功跳到主页
				return "redirect:/common/login_do/index.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/login";
		}
		return "/login";
	}
	
	@ApiOperation(value = "无权限提示页面 ", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/common/login_do/unauthorizedUrl.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String unauthorizedUrl() throws Exception {
		return "/denied";
	}
	
	@ApiOperation(value = "注册页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "/common/login_do/toregister.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toregister() {
		return "/register";
	}
	
	/** 注册
	 *
	 * @throws Exception **/
	@ApiOperation(value = "注册", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/common/login_do/register.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Json register(UserVO user, @RequestParam(name = "email", required = true) String email) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("我们将发送邮箱到您的邮箱中进行验证，大约3小时左右不验证将删除注册信息");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCredentialssalt(new DesUtils().encrypt(user.getPassword()));
		user.setRoleid("7");
		user.setPhone("");
		user.setState(UserStateConstant.DIE);
		user.setCreatetime(DateUtil.getDateTime());
		String contemt = "<a href='http://" + SystemConstant.getSystemdomain() + "/common/login_do/relife.do?userid=" + user.getId() + "&salt=" + user.getCredentialssalt() + "'>激活</a>";
		try {
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("创建失败，已存在该用户");
			return j;
		}
		try {
			SendEmailInter send = new SendQQmailImpl();
			send.sendMail(email, SystemConstant.getSystemName() + "注册", contemt);
			// EmailUtils.sendMail(fromEmail, email, emailName, emailPassword,
			// "aaa", contemt);
		} catch (Exception e) {
			e.printStackTrace();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", user.getId());
			userService.remove(wheremap);
			j.setMsg("发送邮箱失败，可能被提供方拦截，再试一次或者换一种邮箱类型");
			return j;
		}
		return j;
	}
	
	@ApiOperation(value = "激活邮箱页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = "/common/login_do/relife.do", method = RequestMethod.GET)
	public String relife(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "salt", required = true) String salt, ModelMap map) throws Exception {
		UserQuery qvo = new UserQuery();
		qvo.setUsername(username);
		qvo.setState(UserStateConstant.DIE);
		List<UserVO> list = userService.list(qvo);
		if (list != null && list.size() == 1) {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			updatemap.put("state", UserStateConstant.OK);
			wheremap.put("username", list.get(0).getUsername());
			userService.update(updatemap, wheremap);
			map.put("error", "激活成功 ，现在可以登录");
			return "/login";
		}
		map.put("error", "该链接已经失效");
		return "/login";
	}
	
	// /** 忘记密码 **/
	@ApiOperation(value = "忘记密码", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "帐号", dataType = "java.lang.String", required = true), @ApiImplicitParam(name = "password", value = "密码", dataType = "java.lang.String", required = true) })
	@ResponseBody
	@RequestMapping(value = "/common/login_do/forgetpwd.do", method = RequestMethod.GET)
	public Json forgetpwd(@RequestParam(name = "username", required = true) String username, Model model) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		UserQuery userqvo = new UserQuery();
		userqvo.setUsername(username);
		List<UserVO> userlist = userService.list(userqvo);
		if (userlist == null || userlist.size() == 0) {
			j.setSuccess(false);
			j.setMsg("无此账号");
			return j;
		}
		UserVO user = userlist.get(0);
		if (user.getState().equals(UserStateConstant.LOCK)) {
			j.setSuccess(false);
			j.setMsg("账号被锁 ，无法使用");
			return j;
		}
		if (user.getState().equals(UserStateConstant.DIE)) {
			j.setSuccess(false);
			j.setMsg("账号未激活 ，无法使用");
			return j;
		}
		if (!user.getState().equals(UserStateConstant.OK)) {
			j.setSuccess(false);
			j.setMsg("未知原因 ，无法使用");
			return j;
		}
		// 加密 的字符串 防止 用户知道自己的ID
		JSONObject json = new JSONObject();
		json.put("uid", user.getId());
		json.put("dietime", DateUtil.getDate());
		String encryptInfo = json.toString();
		encryptInfo = "encryptInfo=" + new DesUtils().encrypt(encryptInfo);
		String contemt = "<a href='http://" + SystemConstant.getSystemdomain() + "/common/login_do/resetpwd.do?" + encryptInfo + "'>重置密码，有效期截止到当天晚上24：00</a>";
		try {
			SendEmailInter send = new SendQQmailImpl();
			send.sendMail(user.getEmail(), SystemConstant.getSystemName() + "-找回密码", contemt);
			// EmailUtils.sendMail(fromEmail, user.getEmail(), emailName,
			// emailPassword, SystemConstant.getSystemName() + "-找回密码",
			// contemt);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("发送邮箱失败，可能被提供方拦截");
			return j;
		}
		j.setMsg("发送邮箱成功，请到邮箱重置密码");
		return j;
	}
	
	// /** 重置密码初始化 **/
	// XXX 需要添加校验机制 不然是个BUG
	@ApiOperation("重置密码页面")
	@ApiImplicitParams({ @ApiImplicitParam(name = "encryptInfo", value = "加密信息", dataType = "java.lang.String", required = true), @ApiImplicitParam(name = "password", value = "密码", dataType = "java.lang.String", required = true) })
	@RequestMapping(value = "/common/login_do/resetpwd.do", method = RequestMethod.GET)
	public String resetpwd(@RequestParam(name = "encryptInfo", required = true) String encryptInfo, Model model) {
		// String userid = ServletUtil.getStringParamDefaultBlank(request,
		// "userid");
		try {
			JSONObject json = JSONObject.fromObject(new DesUtils().decrypt(encryptInfo));
			String userid = json.getString("uid");
			String dietime = json.getString("dietime");
			if (dietime.equals(DateUtil.getDate())) {
				UserVO user = userService.get(userid);
				if (user.getState().equals(UserStateConstant.LOCK)) {
					return "/lock";
				}
				if (user.getState().equals(UserStateConstant.DIE)) {
					return "/die";
				}
				if (!user.getState().equals(UserStateConstant.OK)) {
					return "";// XXX 返回错误的请求 比如账号封锁、未激活等状态；
				}
				model.addAttribute("encryptInfo", encryptInfo);
				return "/reset";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "加密信息错误");
			return "/fail";
		}
		model.addAttribute("msg", "该链接已过期");
		return "/fail";
	}
	
	// /** 重置密码 **/
	// // XXX 需要添加校验机制 不然是个BUG
	@ApiOperation(value = "重置密码", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "encryptInfo", value = "加密信息", dataType = "java.lang.String", required = true), @ApiImplicitParam(name = "password", value = "密码", dataType = "java.lang.String", required = true) })
	@ResponseBody
	@RequestMapping(value = "/common/login_do/resetpassword.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Json resetpassword(@RequestParam(name = "encryptInfo", required = true) String encryptInfo, @RequestParam(name = "password", required = true) String password, Model model) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("操作成功");
		try {
			JSONObject json = JSONObject.fromObject(new DesUtils().decrypt(encryptInfo));
			String userid = json.getString("uid");
			String dietime = json.getString("dietime");
			if (!dietime.equals(DateUtil.getDate())) {
				j.setSuccess(false);
				j.setMsg("操作失败！时间已过");
				return j;
			}
			UserVO user = userService.get(userid + "");
			if (user.getState().equals(UserStateConstant.LOCK)) {
				j.setSuccess(false);
				j.setMsg("账号被锁 ，无法使用");
				return j;
			}
			if (user.getState().equals(UserStateConstant.DIE)) {
				j.setSuccess(false);
				j.setMsg("账号未激活 ，无法使用");
				return j;
			}
			if (!user.getState().equals(UserStateConstant.OK)) {
				j.setSuccess(false);
				j.setMsg("未知原因 ，无法使用");
				return j;
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			updatemap.put("password", passwordEncoder.encode(password));
			updatemap.put("credentialssalt", new DesUtils().encrypt(password));
			wheremap.put("id", user.getId());
			userService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("操作失败！");
			return j;
		}
		return j;
	}
	
	/** 检测账号是否存在 **/
	@ApiOperation(value = " 检测账号是否存在", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "/common/login_do/isexist.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public boolean isexist(UserQuery qvo) {
		return userService.checkisExist(qvo);
	}
	
	/** 修改密码
	 * 
	 * @throws Exception **/
	@ApiOperation(value = "修改密码", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "/common/login_do/modifypwd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json modifypwd(@RequestParam(name = "password", required = true) String password) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("操作成功");
		UserVO user = (UserVO) Common.findUserSession();
		if (user == null) {
			j.setMsg("您尚未登陆");
			return j;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		updatemap.put("password", passwordEncoder.encode(password));
		updatemap.put("credentialssalt", new DesUtils().encrypt(password));
		wheremap.put("id", user.getId());
		userService.update(updatemap, wheremap);
		return j;
	}
	
	/** 清除过期没有激活的用户 
	 * @throws Exception **/
	@Scheduled(cron = "0 0 */6 * * ?")
	// XXX 好像还有点问题
	// @Scheduled(cron = "1 * * * * ? ")
	public void cleanuser() throws Exception {
		userService.removeExpired();
	}
}
