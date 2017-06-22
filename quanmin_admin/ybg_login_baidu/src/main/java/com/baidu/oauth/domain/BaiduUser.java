package com.baidu.oauth.domain;

import com.baidu.api.domain.User;

public final class BaiduUser extends User {
	String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
