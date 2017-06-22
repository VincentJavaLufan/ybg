package com.ybg.base.util;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ybg.rbac.user.domain.User;

public class Common {
	
	/** 获取登录账号的的对象
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-27
	 * @param request
	 * @return Object 返回是Object..需要转型为(Account)Object */
	public static User findUserSession() {
		User user = null;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
		return user;
	}
}
