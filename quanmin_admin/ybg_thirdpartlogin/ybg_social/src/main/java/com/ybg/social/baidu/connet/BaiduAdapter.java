/**
 * 
 */
package com.ybg.social.baidu.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.baidu.api.Baidu;
import com.ybg.social.baidu.api.BaiduUserInfo;

/** 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * 
 * 
 * @author zhailiang */
public class BaiduAdapter implements ApiAdapter<Baidu> {
	
	private String uid;
	
	public BaiduAdapter() {
	}
	
	public BaiduAdapter(String uid) {
		this.uid = uid;
	}
	
	/** @param api
	 * @return */
	@Override
	public boolean test(Baidu api) {
		return true;
	}
	
	/** @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(Baidu api, ConnectionValues values) {
		BaiduUserInfo userInfo = api.getUserInfo(uid);
		// 昵称
		values.setDisplayName(userInfo.getUsername());
		// 用户头像
		values.setImageUrl(userInfo.getPortrait());
		// 个人主页，不一定有。
		values.setProfileUrl(null);
		// 服务商的用户ID ,QQ 的是QQid // 这个异常需要捕获处理 处理成运行时异常
		values.setProviderUserId(userInfo.getUserid());
	}
	
	/** @param api
	 * @return */
	@Override
	public UserProfile fetchUserProfile(Baidu api) {
		BaiduUserInfo userInfo = api.getUserInfo(uid);
		UserProfile bean = new UserProfile(userInfo.getUserid(), userInfo.getUsername(), null, null, null, userInfo.getUsername());
		return bean;
	}
	
	/** @param api
	 * @param message
	 */
	@Override
	public void updateStatus(Baidu api, String message) {
		// do nothing
	}
}
