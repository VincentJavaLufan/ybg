package com.ybg.config.security;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.LoginServiceImpl;

/** @author 自定义验证 */
@Component
public class YcAnthencationProder implements AuthenticationProvider {
	
	@Autowired
	private LoginServiceImpl userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserVO user = null;
		try {
			user = userService.getUserByname(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new BadCredentialsException("Username not found.");
		}
		Collection<? extends GrantedAuthority> authorities = userService.loadUserByUsername(username).getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
