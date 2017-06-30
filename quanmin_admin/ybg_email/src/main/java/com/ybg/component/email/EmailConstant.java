package com.ybg.component.email;

import com.ybg.base.util.SystemConstant;

/** 定义 邮箱常量 **/
public class EmailConstant {
	
	/** 使用的协议 **/
	public static final String	PROTOCOL		= "pop3";
	// 定义连接POP3服务器的属性信息
	public static final String	POP163SERVER	= "pop.163.com";
	public static final String	SMTP163SERVER	= "smtp.163.com";
	// qq
	public static final String	POPqqSERVER		= "pop.qq.com";
	public static final String	SMTPqqSERVER	= "smtp.qq.com";
	// 有SSL端口
	public static final String	SSLPOP3PORT		= "995";
	public static final String	SSLSMTPPORT		= "587";
	// 无SSL端口
	public static final String	POP3PORT		= "110";
	public static final String	SMTPPORT		= "25";
	
	/*** 邮箱帐号 **/
	public static String getEmailaccount() {
		return SystemConstant.getEmailAdress();
	}
	
	/** 邮箱密码 如果是QQ邮箱 请设置授权码（POP3） **/
	public static String getEmailpwd() {
		return SystemConstant.getEmailPwd();
	}
	
	private EmailConstant() {
		// 禁止实例化
	}
}
