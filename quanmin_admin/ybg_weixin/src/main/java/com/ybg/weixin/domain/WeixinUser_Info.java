package com.ybg.weixin.domain;
import java.io.Serializable;

public class WeixinUser_Info implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				openid;
	private String				sex;
	private String				language;
	private String				city;
	private String				province;
	private String				country;
	private String				headimgurl;
	private String				nickname;
	private String				unionid;
	
	/** @return the unionid */
	public String getUnionid() {
		return unionid;
	}
	
	/** @param unionid
	 *            the unionid to set */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getHeadimgurl() {
		return headimgurl;
	}
	
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
