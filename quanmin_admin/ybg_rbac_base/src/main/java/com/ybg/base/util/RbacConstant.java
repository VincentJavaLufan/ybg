package com.ybg.base.util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.rbac.user.domain.UserVO;

/** 系统常量类
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 **/
public class RbacConstant {
	
	/** 超管角色 **/
	public static final String	ROLE_ADMIN_ID				= "1";
	/** 非超管角色 **/
	public static final String	ROLE_OTHER_ID				= "10";
	/** 最小密码长度 **/
	public static final int		MIN_PASSWORD_LENTH			= 7;
	/** 目录类型 **/
	public static final String	RESOURCE_MENU				= "0";
	/** 系统默认 授权资源ID **/
	public static final String	RESOURCE_DEFAULT_PARENTID	= "0";
	/** 超管的角色KEY **/
	public static final String	ROLE_ADMIN					= "ROLE_ADMIN";
	/** 普通角色KEY **/
	public static final String	ROLE_OTHER					= "ROLE_OTHER";
	/** VIP角色KEY **/
	public static final String	ROLE_VIP					= "ROLE_VIP";
	
	/** 判断当前角色是超管 **/
	public static boolean isAdmin(UserVO user) {
		if (user == null || user.getId() == null) {
			return false;
		}
		if (!QvoConditionUtil.checkList(user.getRoleids())) {
			return false;
		}
		for (String roleid : user.getRoleids()) {
			if (user != null && roleid.equals(ROLE_ADMIN_ID)) {
				return true;
			}
		}
		return false;
	}
	
	/** 判断当前角色是非超管 **/
	public static boolean isOther(UserVO user) {
		if (user == null || user.getId() == null) {
			return false;
		}
		if (!QvoConditionUtil.checkList(user.getRoleids())) {
			return false;
		}
		for (String roleid : user.getRoleids()) {
			if (user != null && roleid.equals(ROLE_OTHER_ID)) {
				return true;
			}
		}
		return false;
	}
	
	private RbacConstant() {
		// 禁止实例化
	}
	
	/** 首次注册用户时，赋予的角色ID **/
	public static List<String> initRole() {
		List<String> roles = new ArrayList<>();
		roles.add(ROLE_OTHER_ID);
		return roles;
	}
	
	/** 获取加密后的密码 **/
	public static String getpwd(String pwd) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(pwd);
	}
}
