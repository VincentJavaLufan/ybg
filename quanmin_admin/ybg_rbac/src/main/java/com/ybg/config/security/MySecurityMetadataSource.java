package com.ybg.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQvo;
import com.ybg.rbac.resources.service.ResourcesService;

/** Created by Athos on 2016-10-16. */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> aclResourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
	@Autowired
	private ResourcesService aclResourcesService;

	/** 构造方法 */
	// 1
	// public MySecurityMetadataSource(ResourcesService aclResourcesService) {
	// this.aclResourcesService = aclResourcesService;
	// loadResourceDefine();
	// }
	public MySecurityMetadataSource() {
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		Set<String> keys = aclResourceMap.keySet();
		if (keys == null) {
			return null;
		}
		try {
			for (String resURL : keys) {
				RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
				if (requestMatcher.matches(request)) {
					return aclResourceMap.get(resURL);
				}
			}
		} catch (Exception e) {// 文档API接口会异常
			return null;
		}
		return null;
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

	private void loadResourceDefine() {
		/** 因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源 */
		List<SysResourcesVO> aclResourceses = aclResourcesService.query(new ResourcesQvo());
		aclResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for (SysResourcesVO aclResources : aclResourceses) {
			ConfigAttribute ca = new SecurityConfig(aclResources.getResurl());
			String url = aclResources.getResurl();
			if (aclResourceMap.containsKey(url)) {
				Collection<ConfigAttribute> value = aclResourceMap.get(url);
				if (value != null) {
					value.add(ca);
					aclResourceMap.put(url, value);
				}
			} else {
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				atts.add(ca);
				aclResourceMap.put(url, atts);
			}
		}
	}
}
