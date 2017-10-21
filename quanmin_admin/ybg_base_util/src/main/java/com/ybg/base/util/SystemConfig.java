package com.ybg.base.util;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Component
@ConfigurationProperties(prefix = "system", ignoreInvalidFields = false)
public class SystemConfig {
	
	private String	domain;
	private String	email;
	private String	emailpwd;
	private String	name;
	private String	icp;
	private String	auth;
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmailpwd() {
		return emailpwd;
	}
	
	public void setEmailpwd(String emailpwd) {
		this.emailpwd = emailpwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcp() {
		return icp;
	}
	
	public void setIcp(String icp) {
		this.icp = icp;
	}
	
	public String getAuth() {
		return auth;
	}
	
	public void setAuth(String auth) {
		this.auth = auth;
	}
}
