package com.ybg.social.github.connect;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.github.api.GitHub;
import com.ybg.social.github.api.GithubUserInfo;

/** @author Deament
 * @date 2017/10/1 **/
public class GithubAdapter implements ApiAdapter<GitHub> {
	
	@Override
	public boolean test(GitHub api) {
		return true;
	}
	
	@Override
	public void setConnectionValues(GitHub api, ConnectionValues values) {
		GithubUserInfo userInfo = api.getUserInfo();
		// 昵称
		values.setDisplayName(userInfo.getName());
		// 用户头像
		values.setImageUrl(userInfo.getAvatar_url());
		// 个人主页，不一定有。
		values.setProfileUrl(userInfo.getHtml_url());
		// 服务商的用户ID ,QQ 的是QQid // 这个异常需要捕获处理 处理成运行时异常
		values.setProviderUserId(userInfo.getId() + "");
	}
	
	@Override
	public UserProfile fetchUserProfile(GitHub api) {
		GithubUserInfo userInfo = api.getUserInfo();
		UserProfile bean = new UserProfile(userInfo.getId() + "", userInfo.getName(), null, null, null, userInfo.getName());
		return bean;
	}
	
	@Override
	public void updateStatus(GitHub api, String message) {
		// TODO Auto-generated method stub
	}
}
