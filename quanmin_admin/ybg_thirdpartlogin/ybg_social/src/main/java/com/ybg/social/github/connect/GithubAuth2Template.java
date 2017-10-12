package com.ybg.social.github.connect;
import java.nio.charset.Charset;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.web.client.RestTemplate;

public class GithubAuth2Template extends OAuth2Template {
	
	public GithubAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
}
