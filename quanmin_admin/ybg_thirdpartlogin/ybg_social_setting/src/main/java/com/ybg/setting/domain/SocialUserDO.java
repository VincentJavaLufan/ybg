package com.ybg.setting.domain;
import java.io.Serializable;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class SocialUserDO implements Serializable {
	
	String	userid;
	String	providerid;
	String	provideruserid;
	Integer	rank;
	String	displayname;
	String	profileurl;
	String	imageurl;
	String	accesstoken;
	String	secret;
	String	refreshtoken;
	Long	expiretime;
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getProviderid() {
		return providerid;
	}
	
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	
	public String getProvideruserid() {
		return provideruserid;
	}
	
	public void setProvideruserid(String provideruserid) {
		this.provideruserid = provideruserid;
	}
	
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public String getDisplayname() {
		return displayname;
	}
	
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	public String getProfileurl() {
		return profileurl;
	}
	
	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}
	
	public String getImageurl() {
		return imageurl;
	}
	
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public String getAccesstoken() {
		return accesstoken;
	}
	
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public String getRefreshtoken() {
		return refreshtoken;
	}
	
	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}
	
	public Long getExpiretime() {
		return expiretime;
	}
	
	public void setExpiretime(Long expiretime) {
		this.expiretime = expiretime;
	}
	
	@Override
	public String toString() {
		return "SocialUserDO [userid=" + userid + ", providerid=" + providerid + ", provideruserid=" + provideruserid + ", rank=" + rank + ", displayname=" + displayname + ", profileurl=" + profileurl + ", imageurl=" + imageurl + ", accesstoken=" + accesstoken + ", secret=" + secret + ", refreshtoken=" + refreshtoken + ", expiretime=" + expiretime + "]";
	}
}
