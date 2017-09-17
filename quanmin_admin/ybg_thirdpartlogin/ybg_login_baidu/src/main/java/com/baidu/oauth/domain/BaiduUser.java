package com.baidu.oauth.domain;
import com.baidu.api.domain.User;

public final class BaiduUser extends User {
	
	String	userid;
	String	uid_str;
	
	public String getUid_str() {
		return uid_str;
	}
	
	public void setUid_str(String uid_str) {
		this.uid_str = uid_str;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
