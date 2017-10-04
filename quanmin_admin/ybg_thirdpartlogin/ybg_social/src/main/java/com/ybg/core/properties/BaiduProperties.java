package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

public class BaiduProperties extends SocialProperties {
	
	private String providerId = "baidu";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
