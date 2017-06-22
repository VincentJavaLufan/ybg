package com.ybg.weixin.domain;
public class WeiXinSetting {
	
	private String	appid;			// 微信ID
	private String	redirect_uri;
	private String	scope;
	private String	state;
	private String	secret;			// 密钥
	private String	partnerkey;		// 微信商API
	private String	notify_url;		// 微信商API
	private String	mch_id;			// 商户ID
	private Integer	isuse;			// 是否使用
	
	public Integer getIsuse() {
		return isuse;
	}
	
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getRedirect_uri() {
		return redirect_uri;
	}
	
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public String getPartnerkey() {
		return partnerkey;
	}
	
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	
	public String getNotify_url() {
		return notify_url;
	}
	
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	
	public String getMch_id() {
		return mch_id;
	}
	
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
}
