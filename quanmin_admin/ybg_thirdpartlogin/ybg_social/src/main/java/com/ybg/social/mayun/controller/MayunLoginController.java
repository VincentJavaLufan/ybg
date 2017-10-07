package com.ybg.social.mayun.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ybg.mayun.api.Mayun;
import com.ybg.mayun.oauth.controller.MayunConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "码云登陆API2")
@Controller
@RequestMapping("/common/mayun_do/")
public class MayunLoginController {
	
	@ApiOperation(value = "码云登陆跳转", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "tologin.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String tologin(HttpServletRequest request, HttpServletResponse response) {
		Mayun mayun = new Mayun(MayunConfig.getValue(MayunConfig.client_ID), MayunConfig.getValue(MayunConfig.client_SERCRET), MayunConfig.getValue(MayunConfig.redirect_URI));
		String authorizeUrl = mayun.getLoginURL();
		System.out.println(authorizeUrl);
		return "redirect:" + authorizeUrl;
	}
}
