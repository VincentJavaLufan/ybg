package com.ybg.weixin.domain;
public class Weixin_access {
	
	Integer	id;
	String	accesscode;
	Integer	type;
	Integer	stuid;
	String	addtime;
	String	city_id;
	
	public String getCity_id() {
		return city_id;
	}
	
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAccesscode() {
		return accesscode;
	}
	
	public void setAccesscode(String accesscode) {
		this.accesscode = accesscode;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStuid() {
		return stuid;
	}
	
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	
	public String getAddtime() {
		return addtime;
	}
	
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
