package com.ybg.social.github.connect;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.ybg.social.github.api.GitHub;

/** @author Deament
 * @date 2017/10/1 **/
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GitHub> {
	
	/** @param appId
	 * @param appSecret
	 */
	public GithubConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new GithubServiceProvider(appId, appSecret), new GithubAdapter());
	}
}
