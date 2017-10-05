package com.ybg.rbac.user.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.user.dao.UserDao;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQuery;

@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	/** 新增并返回主键
	 * 
	 * @throws Exception **/
	@CacheEvict(value = "userCache", allEntries = true)
	public UserVO save(UserVO user) throws Exception {
		return userDao.save(user);
	}
	
	/** 根据id删除 **/
	@CacheEvict(value = "userCache", allEntries = true)
	public void removebyid(String id) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("id", id);
		updatemap.put("isdelete", "1");
		update(updatemap, wheremap);
	}
	
	/** 注册 发送邮箱失败删除 **/
	@CacheEvict(value = "userCache", allEntries = true)
	public void remove(BaseMap<String, Object> wheremap) {
		userDao.remove(wheremap);
	}
	
	/** 根据某些属性来更新 不再限定固定属性 **/
	@CacheEvict(value = "userCache", allEntries = true)
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		userDao.update(updatemap, wheremap);
	}
	
	/** 获取单个信息
	 * 
	 * @throws Exception **/
	@Cacheable(value = "userCache", key = "#root.method.name+#root.args[0]")
	public UserVO get(String id) throws Exception {
		return userDao.loginById(id);
	}
	
	/** 分页查询用户信息
	 * 
	 * @throws Exception **/
	@Cacheable(value = "userCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public Page list(Page page, UserQuery qvo) throws Exception {
		return userDao.list(page, qvo);
	}
	
	/** 不分页查询用户信息
	 * 
	 * @throws Exception **/
	@Cacheable(value = "userCache", key = "#root.method.name+#root.args[0]")
	public List<UserVO> list(UserQuery qvo) throws Exception {
		return userDao.list(qvo);
	}
	
	/** 登陆 **/
	@Cacheable(value = "userCache", key = "#root.method.name+#root.args[0]")
	public UserVO login(String loginname) {
		return userDao.login(loginname);
	}
	
	/** 清楚注册不激活的用户
	 * 
	 * @throws Exception **/
	@CacheEvict(value = "userCache", allEntries = true)
	public void removeExpired() throws Exception {
		userDao.removeExpired();
	}
	
	@Override
	public boolean checkisExist(UserQuery qvo) {
		return userDao.checkisExist(qvo);
	}
}
