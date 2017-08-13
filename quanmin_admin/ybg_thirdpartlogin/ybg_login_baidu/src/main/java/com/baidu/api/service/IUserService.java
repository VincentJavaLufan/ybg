/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.service;
import com.baidu.api.BaiduApiException;
import com.baidu.api.domain.User;

/** 调用用户相关api的借口类
 * 
 * @author chenhetong(chenhetong@baidu.com) */
public interface IUserService {
	
	/** 获取当前登录用户的用户uid和用户名。
	 * 
	 * @return 当前登录用户的User封装
	 * @throws BaiduApiException */
	User getLoggedInUser() throws BaiduApiException;
	
	/** 返回指定用户的用户资料。
	 * 
	 * @param uid
	 *            用户id, 为空则表示当前登录用户
	 * @param fields
	 *            期望获得的用户资料的字段列表，用逗号隔开，为空则获取userid, username, realname
	 * @return 根据fields字段，以json格式返回信息
	 * @throws BaiduApiException */
	String getInfo(long uid, String[] fields) throws BaiduApiException;
	
	/** 判定用户是否已经为应用授权。
	 * 
	 * @param uid
	 *            用户uid，为空则默认是当前用户
	 * @param appid
	 *            应用的appid，为空则默认是当前应用
	 * @return 包含result字段的json信息，1表示已经授权该应用，0表示没有授权该应用。
	 * @throws BaiduApiException */
	String isAppUser(long uid, long appid) throws BaiduApiException;
	
	/** 根据用户id以及在百度的相应的操作权限(单个权限，例如接收email等)来判断用户是否可以进行此操作
	 * 
	 * @param ext_perm
	 *            单个权限，例如接收email等，
	 * @param uid
	 *            用户uid，为空则默认是当前用户。
	 * @return 包含result字段的json信息，1表示当前功能有操作权限，0表示当前功能没有操作权限。
	 * @throws BaiduApiException */
	String hasAppPermission(String extPerm, long uid) throws BaiduApiException;
	
	/** 根据用户id以及在百度的相应的操作权限(可以是多个权限半角逗号隔开)来判断用户是否可以进行此操作。
	 * 
	 * @param ext_perms
	 *            多个权限半角逗号隔开，例如basic,email等
	 * @param uid
	 *            用户uid，为空则默认是当前用户
	 * @return 包含对应权限字段的json文本，1表示当前功能有操作权限，0表示当前功能没有操作权限。
	 * @throws BaiduApiException */
	String hasAppPermissions(String[] extPerms, long uid) throws BaiduApiException;
}
