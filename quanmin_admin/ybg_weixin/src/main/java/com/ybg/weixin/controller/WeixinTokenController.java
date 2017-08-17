package com.ybg.weixin.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.weixinMenu.MessageUtil;
import com.ybg.weixinMenu.SignUtil;
import com.ybg.weixinMenu.resp.TextMessage;
import io.swagger.annotations.Api;
@Api(tags="用于验证微信服务器")
@Controller
public class WeixinTokenController {
	
	@ResponseBody
	@RequestMapping(value = "/common/weixin_do/weixintoken.do")
	public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// PrintWriter out = response.getWriter();
		// // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		// if (SignUtil.checkSignature(signature, timestamp, nonce)) {
		// out.print(echostr);
		// }
		// System.out.println(echostr);
		// out.close();
		// out = null;
		System.out.println(echostr);
		return echostr;
	}
}
