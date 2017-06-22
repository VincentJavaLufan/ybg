package com.ybg.weixin.domain;
/** 教师绑定查询 */
public class Weixin_teacher_b {
	
	private Integer	user_id;
	private String	open_id;
	private Integer	id;
	private String	unionid;
	private String	city_id;
	
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
	
	public Integer getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getOpen_id() {
		return open_id;
	}
	
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
