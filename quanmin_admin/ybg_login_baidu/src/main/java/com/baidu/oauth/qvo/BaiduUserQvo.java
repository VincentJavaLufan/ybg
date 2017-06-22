package com.baidu.oauth.qvo;
import com.baidu.api.domain.User;

public class BaiduUserQvo extends User {
	
	Integer	userid;
	
	public Integer getUserid() {
		return userid;
	}
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
