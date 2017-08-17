package com.ybg.weixin.login.domain;
import java.io.Serializable;

public class WeixinUserDO implements Serializable {
	
	private String	id;
	private String	openid;
	private String	userid;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
