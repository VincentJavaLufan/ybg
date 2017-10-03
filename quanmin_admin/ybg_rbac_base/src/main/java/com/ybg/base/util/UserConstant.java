package com.ybg.base.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ybg.rbac.user.domain.UserVO;

/** 系统常量类 **/
public class UserConstant {
	
	/** 判断当前角色是超管 **/
	public static boolean IsAdmin(UserVO user) {
		if (user != null && user.getRoleid().equals("1")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是非超管 **/
	public static boolean IsOther(UserVO user) {
		if (user != null && user.getRoleid().equals("10")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业管理员 **/
	public boolean IsCompanyAdmin(UserVO user) {
		if (user != null && user.getRoleid().equals("")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业员工 **/
	public boolean IsCompanyEmployee(UserVO user) {
		if (user != null && user.getRoleid().equals("")) {
			return true;
		}
		return false;
	}
	
	private UserConstant() {
		// 禁止实例化
	}
	
	/** 获取加密后的密码 **/
	public static String getpwd(String pwd) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(pwd);
	}
}
