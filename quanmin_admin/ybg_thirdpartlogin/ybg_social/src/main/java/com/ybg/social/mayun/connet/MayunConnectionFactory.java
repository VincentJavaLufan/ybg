/**
 * 
 */
package com.ybg.social.mayun.connet;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.ybg.social.mayun.api.Mayun;

/** 微信连接工厂
 * 
 * @author zhailiang */
public class MayunConnectionFactory extends OAuth2ConnectionFactory<Mayun> {
	
	/** @param appId
	 * @param appSecret */
	public MayunConnectionFactory(String providerId, String appId, String appSecret, String redirurl) {
		super(providerId, new MayunServiceProvider(appId, appSecret, redirurl), new MayunAdapter());
	}
}
