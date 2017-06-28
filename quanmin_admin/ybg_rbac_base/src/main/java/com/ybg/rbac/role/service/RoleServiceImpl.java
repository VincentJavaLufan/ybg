package com.ybg.rbac.role.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.role.dao.RoleDao;
import com.ybg.rbac.role.domain.RoleResDO;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQuery;
@Repository
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao	roleDao;
	
	/** 返回主键的创建 
	 * @throws Exception **/
	@CacheEvict(value = "roleCache", allEntries = true)
	public SysRoleVO createandid(SysRoleVO role) throws Exception {
		return roleDao.createandid(role);
	}
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	@CacheEvict(value = "roleCache", allEntries = true)
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		roleDao.update(updatemap, wheremap);
	}
	
	/** 根据id删除 **/
	@CacheEvict(value = "roleCache", allEntries = true)
	public void removebyid(String id) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("id", id);
		updatemap.put("isdelete", "1");
		update(updatemap, wheremap);
	}
	
	/** 获取单个实体信息 **/
	@Cacheable(value = "roleCache", key = "#root.method.name+#root.args[0]")
	public SysRoleVO get(String id) {
		RoleQuery qvo = new RoleQuery();
		qvo.setId(id);
		List<SysRoleVO> list = roleDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	/** 分页查询 **/
	@Cacheable(value = "roleCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public Page query(Page page, RoleQuery qvo) {
		return roleDao.query(page, qvo);
	}
	
	/** 不分页查询 **/
	@Cacheable(value = "roleCache", key = "#root.method.name+#root.args[0]")
	public List<SysRoleVO> query(RoleQuery qvo) {
		return roleDao.query(qvo);
	}
	
	/** 角色授权 增删改都在里面了 **/
	@CacheEvict(value = "resroleCache", allEntries = true)
	public void saveOrupdateRole_Res(List<RoleResDO> list) {
		roleDao.saveOrupdateRole_Res(list);
	}
}
