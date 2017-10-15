package com.ybg.base.util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import groovy.util.logging.Commons;

@Component
@ConfigurationProperties(prefix = "system", ignoreInvalidFields = false)
public class SystemConfig {
	
	// @Value("${domain}")
	private String	domain;
	// @Value("${email}")
	private String	email;
	// @Value("${emailpwd}")
	private String	emailpwd;
	// @Value("${name}")
	private String	name;
	// @Value("${icp}")
	private String	icp;
	// @Value("${auth}")
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
