package com.ybg.component.email.sendemail;
/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface SendEmailInter {
	
	/** 发送邮件
	 * 
	 * @param toEmail
	 * @param title
	 * @param centent
	 * @throws Exception
	 */
	void sendMail(String toEmail, String title, String centent) throws Exception;
}
