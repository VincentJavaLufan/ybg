/**
 * 
 */
package com.ybg.social.baidu.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.baidu.api.Baidu;
import com.ybg.social.baidu.api.BaiduUserInfo;
import com.ybg.social.weixin.api.Weixin;
import com.ybg.social.weixin.api.WeixinUserInfo;

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
	 * @param values */
	@Override
	public void setConnectionValues(Baidu api, ConnectionValues values) {
		BaiduUserInfo profile = api.getUserInfo(uid);
		// values.setProviderUserId(profile.getOpenid());
		// values.setDisplayName(profile.getNickname());
		// values.setImageUrl(profile.getHeadimgurl());
	}
	
	/** @param api
	 * @return */
	@Override
	public UserProfile fetchUserProfile(Baidu api) {
		return null;
	}
	
	/** @param api
	 * @param message */
	@Override
	public void updateStatus(Baidu api, String message) {
		// do nothing
	}
}
