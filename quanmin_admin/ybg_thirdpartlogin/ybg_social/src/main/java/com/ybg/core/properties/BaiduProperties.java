package com.ybg.core.properties;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/** @author Deament
 * @date 2017/10/1 **/
public class BaiduProperties extends SocialProperties {
	
	private String providerId = "baidu";
	
	public String getProviderId() {
		return providerId;
	}
	
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
