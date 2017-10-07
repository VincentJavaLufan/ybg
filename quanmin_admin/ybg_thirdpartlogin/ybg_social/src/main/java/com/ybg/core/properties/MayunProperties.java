package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

public class MayunProperties extends SocialProperties {
	
	private String providerId = "mayun";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}