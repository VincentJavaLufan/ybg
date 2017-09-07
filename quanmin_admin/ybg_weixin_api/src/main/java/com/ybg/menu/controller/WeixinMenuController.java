package com.ybg.menu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.util.Json;
import com.ybg.menu.domain.WeixinButtonDO;
import com.ybg.menu.service.WeixinMenuService;
import io.swagger.annotations.Api;

@Api(tags = "微信菜单管理")
@RequestMapping("/weixin/menu_do/")
@Controller
public class WeixinMenuController {
	
	@Autowired
	WeixinMenuService weixinMenuService;
	
	@RequestMapping("index.do")
	public String index() {
		return "/weixin/menu";
	}
	
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody WeixinButtonDO weixinmenu) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			weixinMenuService.create(weixinmenu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setMsg("操作成功");
		return j;
	}
}
