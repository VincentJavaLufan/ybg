package com.ybg.core.support;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Repository;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.user.dao.UserDao;
import com.ybg.rbac.user.domain.UserVO;

@Repository
public class SocialLoginService implements SocialUserDetailsService {
	
	@Autowired
	UserDao				userdao;
	@Autowired
	ResourcesService	resourcesService;
	
	// 社交登陆接口
	/** (non-Javadoc)
	 * 
	 * @param userId
	 *            sys_userconnection 表的USERID */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return buildUser(userId);
	}
	
	private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		// 根据查找到的用户信息判断用户是否被冻结
		UserVO user = userdao.loginById(userId);
		// 这里要把权限加进去 不然无法加载权限
		List<SysResourcesVO> authlist = null;
		try {
			authlist = resourcesService.getRolesByUserId(user.getRoleid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
		for (SysResourcesVO s : authlist) {
			auths.add(new SimpleGrantedAuthority(s.getResurl()));
		}
		user.setAuthorities(auths);
		// return new SocialUser(userId, password,
		// true, true, true, true,
		// AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return new SocialUserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isEnabled() {
				return user.isEnabled();
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return user.isCredentialsNonExpired();
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return user.isCredentialsNonExpired();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return user.isCredentialsNonExpired();
			}
			
			@Override
			public String getUsername() {
				return user.getUsername();
			}
			
			@Override
			public String getPassword() {
				return user.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return auths;
			}
			
			@Override
			public String getUserId() {
				return user.getId();
			}
		};
	}
}
