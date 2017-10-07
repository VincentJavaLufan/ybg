/**
 * 
 */
package com.ybg.social.sina.connet;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.ybg.social.sina.api.Sina;

/** 微信连接工厂
 * 
 * @author zhailiang */
public class SinaConnectionFactory extends OAuth2ConnectionFactory<Sina> {
	
	/** @param appId
	 * @param appSecret */
	public SinaConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new SinaServiceProvider(appId, appSecret), new SinaAdapter());
	}
}
