/**
 * 
 */
package com.ybg.social.baidu.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import com.ybg.social.baidu.api.Baidu;


/** 微信连接工厂
 * 
 * @author zhailiang */
public class BaiduConnectionFactory extends OAuth2ConnectionFactory<Baidu> {
	
	/** @param appId
	 * @param appSecret */
	public BaiduConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new BaiduServiceProvider(appId, appSecret), new BaiduAdapter());
	}
	
	/** 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if (accessGrant instanceof BaiduAccessGrant) {
			return ((BaiduAccessGrant) accessGrant).getUid();
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
	 */
	public Connection<Baidu> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<Baidu>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
	 */
	public Connection<Baidu> createConnection(ConnectionData data) {
		return new OAuth2Connection<Baidu>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
	}
	
	private ApiAdapter<Baidu> getApiAdapter(String providerUserId) {
		return new BaiduAdapter(providerUserId);
	}
	
	private OAuth2ServiceProvider<Baidu> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<Baidu>) getServiceProvider();
	}
}
