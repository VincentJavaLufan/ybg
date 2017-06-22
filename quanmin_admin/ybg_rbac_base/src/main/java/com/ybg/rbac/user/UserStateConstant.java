package com.ybg.rbac.user;
/** 账号状态常量 **/
public class UserStateConstant {
	
	private UserStateConstant() {
		// 禁止实例化
	}
	
	/** 没有在限期内激活 **/
	public static String	DIE		= "die";
	/** 正常 **/
	public static String	OK		= "OK";
	/** 封锁 **/
	public static String	LOCK	= "lock";
}
