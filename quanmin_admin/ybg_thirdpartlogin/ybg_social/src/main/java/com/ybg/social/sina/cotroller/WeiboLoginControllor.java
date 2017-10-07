package com.ybg.social.sina.cotroller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

/** 微博登陆 **/
@Api(tags = "微博登陆")
@Controller
@RequestMapping("/common/weibo/sinalogin_do/")
public class WeiboLoginControllor {
	
	@ApiOperation(value = "微博登陆页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(HttpServletRequest request, HttpServletResponse response) throws WeiboException {
		System.out.println("https://api.weibo.com/oauth2/authorize?client_id=" + WeiboConfig.getValue("client_ID") + "&response_type=code&redirect_uri=" + WeiboConfig.getValue(WeiboConfig.redirect_URI));
		return "redirect:" + "https://api.weibo.com/oauth2/authorize?client_id=" + WeiboConfig.getValue("client_ID") + "&response_type=code&redirect_uri=" + WeiboConfig.getValue(WeiboConfig.redirect_URI);
	}
}
