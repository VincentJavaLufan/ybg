package com.ybg.component.email.sendemail;
public interface SendEmailInter {
	
	void sendMail(String toEmail, String title, String centent) throws Exception;
}
