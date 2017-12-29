package com.ybg.rbac.user.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.role.service.RoleService;
import com.ybg.rbac.user.dao.UserDao;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

/** 在这个类中，你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等<br>
 * 这个类负责的是只是获取登陆用户的详细信息（包括密码、角色等），不负责和前端传过来的密码对比，<br>
 * 只需返回User对象，后会有其他类根据User对象对比密码的正确性（框架帮我们做）
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * 
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
	
	/** 登陆验证时，通过username获取用户的所有权限信息， <br>
	 * 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用 <br>
	 * 如果需要数据库时，读者可以用自己写的dao通过参数username来查询出这个用户的权限信息（或是角色信息，就是那个ROLE_*，<br>
	 * 对必须是ROLE_开头的，不然spring security不认账的，其实是spring security里面做了一个判断，必须要ROLE_开头，<br>
	 * 读者可以百度改一下），再返回spring自带的数据模型User即可。 **/
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
		if (user != null) {
			try {
				List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
				for (String rolekey : user.getRolekeys()) {
					auths.add(new SimpleGrantedAuthority(rolekey));
				}
				user.setAuthorities(auths);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
