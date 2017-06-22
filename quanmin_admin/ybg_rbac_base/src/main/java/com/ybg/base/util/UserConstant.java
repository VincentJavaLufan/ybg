package com.ybg.base.util;
import com.ybg.rbac.user.domain.User;

/** 系统常量类 **/
public class UserConstant {
	
	
	/** 判断当前角色是超管 **/
	public boolean IsAdmin() {
		User user = Common.findUserSession();
		if (user != null && user.getRoleid().equals("1")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业管理员 **/
	public boolean IsCompanyAdmin() {
		User user = Common.findUserSession();
		if (user != null && user.getRoleid().equals("2")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业员工 **/
	public boolean IsCompanyEmployee() {
		User user = Common.findUserSession();
		if (user != null && user.getRoleid().equals("3")) {
			return true;
		}
		return false;
	}
	
	private UserConstant() {
		// 禁止实例化
	}
}
