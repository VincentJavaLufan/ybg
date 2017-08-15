package com.ybg.mayun.oauth.domain;
import java.io.Serializable;

/** 码云用户绑定类 **/
public class MayunUserDO implements Serializable {
	
	private String	id;
	private String	mayunid;
	private String	userid;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMayunid() {
		return mayunid;
	}
	
	public void setMayunid(String mayunid) {
		this.mayunid = mayunid;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
