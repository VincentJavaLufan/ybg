/**
 * 
 */
package com.ybg.social.baidu.connet;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
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
}
