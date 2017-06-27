package com.ybg.rbac.user.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.user.dao.UserDao;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQvo;

@Repository
public class LoginService implements UserDetailsService {
	
	@Autowired
	UserDao				userdao;
	@Autowired
	ResourcesService	resourcesService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserQvo qvo = new UserQvo();
		qvo.setUsername(username);
		if (!QvoConditionUtil.checkString(username)) {
			return null;
		}
		List<UserVO> list = userdao.query(qvo);
		if (list == null || list.size() == 0) {
			throw new UsernameNotFoundException(username + " not found");
		}
		UserVO user = list.get(0);
		// 这里要把权限加进去 不然无法加载权限
		List<SysResourcesVO> authlist = resourcesService.getRolesByUserId(user.getRoleid());
		List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
		for (SysResourcesVO s : authlist) {
			auths.add(new SimpleGrantedAuthority(s.getResurl()));
		}
		user.setAuthorities(auths);
		return user;
	}
	
	public UserVO getUserByname(String username) {
		UserQvo qvo = new UserQvo();
		qvo.setUsername(username);
		List<UserVO> list = userdao.query(qvo);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
