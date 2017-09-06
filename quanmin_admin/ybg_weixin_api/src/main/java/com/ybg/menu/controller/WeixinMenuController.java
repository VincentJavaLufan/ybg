package com.ybg.menu.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;

@Api(tags = "微信菜单管理")
@RequestMapping("/weixin/menu_do/")
@Controller
public class WeixinMenuController {
	
	@RequestMapping("index.do")
	public String index() {
		return "";
	}
}
