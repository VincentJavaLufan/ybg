package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

public class SinaProperties extends SocialProperties {
	
	private String providerId = "sina";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
