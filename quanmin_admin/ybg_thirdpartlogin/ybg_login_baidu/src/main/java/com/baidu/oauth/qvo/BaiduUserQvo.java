package com.baidu.oauth.qvo;
import com.baidu.api.domain.User;

public class BaiduUserQvo extends User {
	
	String	userid;
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
