package com.ybg.social.github.connect;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import com.ybg.social.github.api.GitHub;
import com.ybg.social.github.api.GithubImpl;

/** @author Deament
 * @date 2017/10/1 **/
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<GitHub> {
	
	private String				appId;
	private String				secret;
	/** 微信获取授权码的url */
	private static final String	URL_AUTHORIZE		= "https://github.com/login/oauth/authorize";
	/** 微信获取accessToken的url */
	private static final String	URL_ACCESS_TOKEN	= "https://github.com/login/oauth/access_token";
	
	/** 必须实现 父类的构造函数
	 * 
	 * @param appId
	 * @param appSecret
	 */
	public GithubServiceProvider(String appId, String appSecret) {
		// 父类的的 接口，第一个是应用号，第二个是 应用 密钥 ，第三个是 导向第四部URL,第四个是申请 access_token 的URL
		super(new GithubAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
		this.secret = appSecret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public GitHub getApi(String accessToken) {
		return new GithubImpl(accessToken, appId, secret);
	}
}
