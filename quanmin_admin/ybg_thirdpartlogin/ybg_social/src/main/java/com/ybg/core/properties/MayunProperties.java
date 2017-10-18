package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/** @author Deament
 * @date 2017/10/1 **/
public class MayunProperties extends SocialProperties {
	
	private String providerId = "mayun";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}