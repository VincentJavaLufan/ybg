package com.ybg.config.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.service.ResourcesService;

/** 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 * 
 * 这个类是负责的是获取角色与url资源的所有对应关系，并根据url查询对应的所有角色。<br>
 * 今天为一个项目搭安全架构时，第一，发现上面MyInvocationSecurityMetadataSource这个类的代码有个bug：<br>
 * 上面的代码中，将所有的对应关系缓存到resourceMap，key是url，value是这个url对应所有角色。 <br>
 * getAttributes方法中，只要匹配到一个url就返回这个url对应所有角色，不再匹配后面的url，<br>
 * 问题来了，当url有交集时，就有可能漏掉一些角色了：如有两个 url ，第一个是 /** ，第二个是 /role1/index.jsp ，<br>
 * 第一个当然需要很高的权限了（因为能匹配所有 url ，即可以访问所有 url ），<br>
 * 假设它需要的角色是 ROLE_ADMIN （不是一般人拥有的），第二个所需的角色是 ROLE_1 。<br>
 * 当我用 ROLE_1 这个角色访问 /role1/index.jsp 时，在getAttributes方法中，当先迭代了 ,<br>
 * 这个url，它就能匹配 /role1/index.jsp 这个url，并直接返回 这个url对应的所有角色<br>
 * （在这，也就ROLE_ADMIN）给MyAccessDecisionManager这个投票类，<br>
 * YbgAccessDecisionManager这个类中再对比 用户的角色 ROLE_1 ，就会发现不匹配。<br>
 * 最后，明明可以有权访问的 url ，却不能访问了。
 * 
 * @author Deament
 * 
 * 
 * @date 2016/9/31 ***/
@Component
public class YbgSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private UrlMatcher			urlMatcher	= new AntUrlPathMatcher();
	/** 将所有的角色和url的对应关系缓存起来 **/
	@Autowired
	private ResourcesService	resourcesService;
	
	/** 参数是要访问的url，返回这个url对于的所有权限（或角色） http://blog.csdn.net/u012367513/article/details/38866465 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		// 匹配所有的url，并对角色去重
		Set<String> roles = new HashSet<String>();
		try {
			List<SysResourcesVO> allRole = resourcesService.getRolesByRoleIdHaveNull();
			for (SysResourcesVO resurl : allRole) {
				if (urlMatcher.pathMatchesUrl(resurl.getResurl(), url)) {
					roles.add(resurl.getRolekey());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collection<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();
		for (String role : roles) {
			ConfigAttribute ca = new SecurityConfig(role);
			cas.add(ca);
		}
		return cas;
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
