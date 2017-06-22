//package com.ybg.component.email.controller;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import javax.mail.Message;
//import javax.mail.Part;
//import javax.mail.internet.MimeMessage;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.ybg.base.jdbc.util.QvoConditionUtil;
//import com.ybg.base.util.Json;
//import com.ybg.base.util.ServletUtil;
//import com.ybg.component.email.domain.EmailMessage;
//import com.ybg.component.email.receivemail.ReceiveMailInter;
//import com.ybg.component.email.receivemail.ReceiveQQMailImpl;
//import com.ybg.component.email.sendemail.SendEmailInter;
//import com.ybg.component.email.sendemail.SendQQmailImpl;
//
///** 系统邮箱 **/
//@Controller
//@RequestMapping("/xxpt/email_do/")
//public class EmailController {
//	
//	@RequestMapping("index.do")
//	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		return "/newbate3/xxpt/email/index";
//	}
//	
//	@RequestMapping("list.do")
//	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		ReceiveMailInter receive = new ReceiveQQMailImpl();
//		Message[] message = null;
//		try {
//			message = receive.receiveMail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		List<EmailMessage> list = new ArrayList<EmailMessage>();
//		for (Message m : message) {
//			EmailMessage pmm = null;
//			pmm = new EmailMessage((MimeMessage) m);
//			pmm.setAttachPath("c:\\");
//			try {
//				pmm.getMailContent((Part) m);
//				pmm.setDateFormat("yy年MM月dd日 HH:mm");
//				// pmm.saveAttachMent((Part) m);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			list.add(pmm);
//		}
//		// 倒序
//		Collections.reverse(list);
//		map.put("page", list);
//		return "/newbate3/xxpt/email/list";
//	}
//	
//	@RequestMapping("tosendmail.do")
//	public String tosendmail(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		return "/newbate3/xxpt/email/tosendmail";
//	}
//	
//	@ResponseBody
//	@RequestMapping("sendmail.do")
//	public Json sendmail(HttpServletRequest request, HttpServletResponse response) {
//		Json j = new Json();
//		SendEmailInter sendmail = new SendQQmailImpl();
//		String toEmail = ServletUtil.getStringParamDefaultBlank(request, "tomail");
//		String title = ServletUtil.getStringParamDefaultBlank(request, "title");
//		String content = ServletUtil.getStringParamDefaultBlank(request, "content");
//		String[] allemail = toEmail.replaceAll("；", ";").trim().split(";");
//		for (String email : allemail) {
//			if (!checkformat(email)) {
//				j.setMsg("发送失败，邮箱格式不正确");
//				j.setSuccess(false);
//				return j;
//			}
//		}
//		if (!QvoConditionUtil.checkString(toEmail)) {
//			j.setMsg("发送失败，请输入邮箱");
//			j.setSuccess(false);
//			return j;
//		}
//		try {
//			sendmail.sendMail(toEmail, title, content);
//		} catch (Exception e) {
//			e.printStackTrace();
//			j.setMsg("发送失败" + e.getStackTrace()[0]);
//			j.setSuccess(false);
//			return j;
//		}
//		// 发邮件
//		j.setMsg("发送成功");
//		j.setSuccess(true);
//		return j;
//	}
//	
//	private boolean checkformat(String tomail) {
//		String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
//		return tomail.matches(regex);
//	}
//}
