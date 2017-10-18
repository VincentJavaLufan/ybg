package com.ybg.social.sina.connet;
import org.springframework.social.oauth2.AccessGrant;

/** 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,并没有单独的通过accessToke换取openId的服务
 * 
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装。
 * 
 * @author zhailiang */
public class SinaAccessGrant extends AccessGrant {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7243374526633186782L;
	private String				id;
	private String				access_token;
	private Integer				remind_in;
	private Long				expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public Integer getRemind_in() {
		return remind_in;
	}
	
	public void setRemind_in(Integer remind_in) {
		this.remind_in = remind_in;
	}
	
	public Long getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	
	public SinaAccessGrant() {
		super("");
	}
	
	public SinaAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "SinaAccessGrant [getAccess_token()=" + getAccess_token() + ", getRemind_in()=" + getRemind_in() + ", getExpires_in()=" + getExpires_in() + ", getId()=" + getId() + ", getAccessToken()=" + getAccessToken() + ", getScope()=" + getScope() + ", getRefreshToken()=" + getRefreshToken() + ", getExpireTime()=" + getExpireTime() + "]";
	}
}
