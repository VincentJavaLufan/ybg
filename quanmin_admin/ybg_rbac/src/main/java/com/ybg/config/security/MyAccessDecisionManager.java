package com.ybg.config.security;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/** @author Deament
 * 
 * @date 2016/9/31 ***/
public class MyAccessDecisionManager extends AbstractAccessDecisionManager {
	
	public MyAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
		super(decisionVoters);
	}
	
	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		// 检查用户是否够权限访问资源
		// 参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
		// 参数object是url
		// 参数configAttributes所需的权限
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		// 注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面
		throw new AccessDeniedException("没有权限,拒绝访问!");
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	
	/** Iterates through all <code>AccessDecisionVoter</code>s and ensures each can support the presented class.
	 * <p>
	 * If one or more voters cannot support the presented class, <code>false</code> is returned.
	 *
	 * @param clazz
	 *            the type of secured object being presented
	 * @return true if this type is supported */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
