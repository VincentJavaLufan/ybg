package com.ybg.social.baidu.api;
/** @author Deament
 * @date 2017/10/1 **/
public interface Baidu {
	
	/** 返回用户信息
	 * 
	 * @param uid
	 *            用户ID
	 * 
	 * 
	 * @return BaiduUserInfo **/
	BaiduUserInfo getUserInfo(String uid);
}
