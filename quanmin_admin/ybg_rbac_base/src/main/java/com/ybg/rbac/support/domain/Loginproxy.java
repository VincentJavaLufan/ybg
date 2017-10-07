package com.ybg.rbac.support.domain;
import com.ybg.rbac.user.domain.UserVO;

public class Loginproxy {
	
	String	redirecturl	= "/common/login_do/index.do";
	String	loginname;
	String	password;
	String	result;
	boolean	success;
	UserVO	user;										// 成功后的 用户
	
	public UserVO getUser() {
		return user;
	}
	
	public void setUser(UserVO user) {
		this.user = user;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getRedirecturl() {
		return redirecturl;
	}
	
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
