package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

public class GithubProperties extends SocialProperties {
	
	private String providerId = "github";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
