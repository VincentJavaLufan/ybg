package com.ybg.weixin.domain;
public class Weixin_parent {
	
	private Integer	id;
	private String	openid;
	private Integer	student_id;
	private String	mobiephone;
	private String	unionid;
	private String	city_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Integer getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	
	public String getMobiephone() {
		return mobiephone;
	}
	
	public void setMobiephone(String mobiephone) {
		this.mobiephone = mobiephone;
	}
	
	/** @return the unionid */
	public String getUnionid() {
		return unionid;
	}
	
	/** @param unionid
	 *            the unionid to set */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	/** @return the city_id */
	public String getCity_id() {
		return city_id;
	}
	
	/** @param city_id
	 *            the city_id to set */
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
}
