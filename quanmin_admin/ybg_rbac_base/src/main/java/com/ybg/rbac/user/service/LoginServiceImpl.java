package com.ybg.rbac.user.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.service.RoleService;
import com.ybg.rbac.user.dao.UserDao;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Component
public class LoginServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDao				userdao;
	@Autowired
	ResourcesService	resourcesService;
	@Autowired
	RoleService			roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserQuery qvo = new UserQuery();
		qvo.setUsername(username);
		if (!QvoConditionUtil.checkString(username)) {
			return null;
		}
		UserVO user = userdao.login(username);
		// 这里要把权限加进去 不然无法加载权限
		// List<SysResourcesVO> authlist = null;
		try {
			List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
			// authlist = resourcesService.getRolesByRoleIds(user.getRoleids());
			// List<SysRoleVO> allRole = roleService.listIncludeResourceAllRole();
			// for(SysRoleVO role:allRole) {
			// for(String roleid:user.getRoleids()) {
			// if(roleid.equals(role.getId())) {
			// auths.add(new SimpleGrantedAuthority(role.getRolekey()));
			//
			// }
			//
			// }
			// }
			for (String rolekey : user.getRolekeys()) {
				auths.add(new SimpleGrantedAuthority(rolekey));
			}
			user.setAuthorities(auths);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// for (SysResourcesVO s : authlist) {
		// auths.add(new SimpleGrantedAuthority(s.getResurl()));
		// }
		return user;
	}
	
	public UserVO getUserByname(String username) throws Exception {
		UserQuery qvo = new UserQuery();
		qvo.setUsername(username);
		if (QvoConditionUtil.checkString(username)) {
			List<UserVO> list = userdao.list(qvo);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
}
