/**
 * 
 */
package com.ybg.social.mayun.connet;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import com.ybg.social.mayun.api.Mayun;
import com.ybg.social.mayun.api.MayunImpl;

/** 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * 
 * 泛型指向接口 多利 的 不可以 @Compoent.
 * 
 * @author zhailiang */
public class MayunServiceProvider extends AbstractOAuth2ServiceProvider<Mayun> {
	
	private String				appId;
	/** 微信获取授权码的url */
	private static final String	URL_AUTHORIZE		= "https://gitee.com/oauth/authorize";
	/** 微信获取accessToken的url */
	private static final String	URL_ACCESS_TOKEN	= "https://gitee.com/oauth/token";
	private String				redirect;
	
	/** 必须实现 父类的构造函数
	 * 
	 * @param appId
	 * @param appSecret */
	public MayunServiceProvider(String appId, String appSecret, String redirect) {
		// 父类的的 接口，第一个是应用号，第二个是 应用 密钥 ，第三个是 导向第四部URL,第四个是申请 access_token 的URL
		super(new MayunOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
		this.redirect = redirect;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public Mayun getApi(String accessToken) {
		return new MayunImpl(accessToken, appId);
	}
}
