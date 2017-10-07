/**
 * 
 */
package com.ybg.social.mayun.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.mayun.api.Mayun;
import com.ybg.social.mayun.api.MayunUserInfo;

/** 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * 
 * 
 * @author zhailiang */
public class MayunAdapter implements ApiAdapter<Mayun> {
	
	private String id;
	
	public MayunAdapter() {
	}
	
	public MayunAdapter(String id) {
		this.id = id;
	}
	
	/** @param api
	 * @return */
	@Override
	public boolean test(Mayun api) {
		return true;
	}
	
	/** @param api
	 * @param values */
	@Override
	public void setConnectionValues(Mayun api, ConnectionValues values) {
		MayunUserInfo userInfo = api.getUserInfo(id);
		values.setDisplayName(userInfo.getName());// 昵称
		values.setImageUrl(userInfo.getAvatar_url());// 用户头像
		values.setProfileUrl(null);// 个人主页，不一定有。
		values.setProviderUserId(userInfo.getId() + "");// 服务商的用户ID ,QQ 的是QQid // 这个异常需要捕获处理 处理成运行时异常
	}
	
	/** @param api
	 * @return */
	@Override
	public UserProfile fetchUserProfile(Mayun api) {
		MayunUserInfo userInfo = api.getUserInfo(id);
		UserProfile bean = new UserProfile(userInfo.getId() + "", userInfo.getName(), null, null, null, userInfo.getName());
		return bean;
	}
	
	/** @param api
	 * @param message */
	@Override
	public void updateStatus(Mayun api, String message) {
		// do nothing
	}
}
