package com.egzosn.pay.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
@Api(tags="支付模块")
@Controller
public class PayTestController {
	
	@RequestMapping("/pay/testindex.do")
	public String index() {
		return "/demo/index";
	}
}
