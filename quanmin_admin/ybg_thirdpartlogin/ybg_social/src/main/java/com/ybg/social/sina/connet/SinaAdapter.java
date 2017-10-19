/**
 * 
 */
package com.ybg.social.sina.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.sina.api.Sina;
import com.ybg.social.sina.api.SinaUserInfo;

/** 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * 
 * 
 * @author zhailiang */
public class SinaAdapter implements ApiAdapter<Sina> {
	
	private String id;
	
	public SinaAdapter() {
	}
	
	public SinaAdapter(String id) {
		this.id = id;
	}
	
	/** @param api
	 * @return */
	@Override
	public boolean test(Sina api) {
		return true;
	}
	
	/** @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(Sina api, ConnectionValues values) {
		SinaUserInfo userInfo = api.getUserInfo();
		// 昵称
		values.setDisplayName(userInfo.getName());
		// 用户头像
		values.setImageUrl(userInfo.getProfile_image_url());
		// 个人主页，不一定有。
		values.setProfileUrl(userInfo.getUrl());
		// 服务商的用户ID ,QQ 的是QQid // 这个异常需要捕获处理 处理成运行时异常
		values.setProviderUserId(userInfo.getIdstr());
	}
	
	/** @param api
	 * @return */
	@Override
	public UserProfile fetchUserProfile(Sina api) {
		SinaUserInfo userInfo = api.getUserInfo();
		UserProfile bean = new UserProfile(userInfo.getIdstr(), userInfo.getName(), null, null, null, userInfo.getName());
		return bean;
	}
	
	/** @param api
	 * @param message
	 */
	@Override
	public void updateStatus(Sina api, String message) {
		// do nothing
	}
}
