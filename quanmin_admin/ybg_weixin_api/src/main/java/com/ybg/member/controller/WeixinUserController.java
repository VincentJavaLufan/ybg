package com.ybg.member.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** @author Deament
 * @date 2017/1/1 */
@Controller
@RequestMapping("/weixin/user_do/")
public class WeixinUserController {
	
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/weixin/user";
	}
}
