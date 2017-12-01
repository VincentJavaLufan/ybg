package com.ybg.config.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQuery;
import com.ybg.rbac.role.service.RoleService;

/** @author Deament
 * 
 * @date 2016/9/31 ***/
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private UrlMatcher			urlMatcher	= new AntUrlPathMatcher();
	/** 将所有的角色和url的对应关系缓存起来 **/
	/** 角色注入 **/
	@Autowired
	private RoleService			roleService;
	@Autowired
	private ResourcesService	resourcesService;
	
	// 启动时实例化一次
	public MySecurityMetadataSource() {
		// loadResourceDefine();
	}
	
	/** 参数是要访问的url，返回这个url对于的所有权限（或角色） http://blog.csdn.net/u012367513/article/details/38866465 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 将参数转为url
		// Map<String, List<SysResourcesVO>> allRole = null;
		String url = ((FilterInvocation) object).getRequestUrl();
		// 匹配所有的url，并对角色去重
		Set<String> roles = new HashSet<String>();
		try {
			// if (allRole == null) {
			// allRole = roleService.listIncludeResourceAllRole();
			List<SysResourcesVO> allRole = resourcesService.getRolesByRoleIdHaveNull();
			// }
			// for (String rolekey : allRole.keySet()) {
			// List<SysResourcesVO> list = allRole.get(rolekey);
			// for (SysResourcesVO resurl : list) {
			// System.out.println("63:" +rolekey+"."+ urlMatcher.pathMatchesUrl(resurl.getResurl(), url)+",url="+url+","+resurl.getResurl());
			for (SysResourcesVO resurl : allRole) {
				
				if (urlMatcher.pathMatchesUrl(resurl.getResurl(), url)) {
					System.out.println("63:"+resurl.getRolekey() +"."+ urlMatcher.pathMatchesUrl(resurl.getResurl(), url)+",url="+url+","+resurl.getResurl());
					roles.add(resurl.getRolekey());
				}
			}
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if (roles == null || roles.size() == 0) {
		// return null;
		// }
		Collection<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();
		for (String role : roles) {
			ConfigAttribute ca = new SecurityConfig(role);
			cas.add(ca);
		}
		System.out.println("61:"+roles.size()+url);
		return cas;
	}
	
	// 4
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	// 3
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
