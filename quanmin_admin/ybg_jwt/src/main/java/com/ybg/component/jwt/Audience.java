package com.ybg.component.jwt;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "audience", locations = "classpath:jwt.properties")  
@Component
public class Audience {
	
	// audience.clientId=098f6bcd4621d373cade4e832627b4f6
	// audience.base64Secret=MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=
	// audience.name=restapiuser
	// audience.expiresSecond=172800
	private String	clientId;
	private String	base64Secret;
	private String	name;
	private int		expiresSecond;
	
	public String getClientId() {
		return "098f6bcd4621d373cade4e832627b4f6";
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getBase64Secret() {
		return "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
	}
	
	public void setBase64Secret(String base64Secret) {
		this.base64Secret = base64Secret;
	}
	
	public String getName() {
		return "restapiuser";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getExpiresSecond() {
		return 172800;
	}
	
	public void setExpiresSecond(int expiresSecond) {
		this.expiresSecond = expiresSecond;
	}
}
