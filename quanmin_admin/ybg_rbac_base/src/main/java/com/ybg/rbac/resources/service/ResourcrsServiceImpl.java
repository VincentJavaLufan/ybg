package com.ybg.rbac.resources.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.dao.ResourcesDao;
import com.ybg.rbac.resources.domain.SysButton;
import com.ybg.rbac.resources.domain.SysColor;
import com.ybg.rbac.resources.domain.SysMenuIcon;
import com.ybg.rbac.resources.domain.SysResources;
import com.ybg.rbac.resources.qvo.ResourcesQvo;
import com.ybg.rbac.resources.qvo.SysButtonQvo;
import com.ybg.rbac.resources.qvo.SysColorQvo;
import com.ybg.rbac.resources.qvo.SysMenuIconQvo;

@Repository
public class ResourcrsServiceImpl implements ResourcesService {
	
	@Autowired
	ResourcesDao resourcesDao;
	
	/** 返回主键的创建 **/
	@Caching(evict = { @CacheEvict(value = "resourcesCache", allEntries = true), @CacheEvict(value = "resroleCache", allEntries = true) })
	public SysResources create(SysResources bean) {
		return resourcesDao.create(bean);
	}
	
	/** 根据ID删除 **/
	@Caching(evict = { @CacheEvict(value = "resourcesCache", allEntries = true), @CacheEvict(value = "resroleCache", allEntries = true) })
	public void removebyid(String id) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		updatemap.put("isdelete", 1);
		wheremap.put("id", id);
		update(updatemap, wheremap);
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
	@Caching(evict = { @CacheEvict(value = "resourcesCache", allEntries = true), @CacheEvict(value = "resroleCache", allEntries = true) })
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		resourcesDao.update(updatemap, wheremap);
	}
	
	/** 获取单个实体信息 **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public SysResources get(String id) {
		ResourcesQvo qvo = new ResourcesQvo();
		qvo.setId(id);
		List<SysResources> list = resourcesDao.query(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	/** 分页查询 **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public Page query(Page page, ResourcesQvo qvo) {
		return resourcesDao.query(page, qvo);
	}
	
	/** 不分页查询 **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public List<SysResources> query(ResourcesQvo qvo) {
		return resourcesDao.query(qvo);
	}
	
	/** 角色 权限集合 **/
	@Cacheable(value = "resroleCache", key = "#root.method.name+#root.args[0]")
	public List<SysResources> getRolesByUserId(String roleid) {
		return resourcesDao.getRolesByUserId(roleid);
	}
	
	/** 授权的按钮操作 **/
	@Cacheable(value = "resroleCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public List<SysResources> getOperatorButton(String roleid, String parentid) {
		return resourcesDao.getOperatorButton(roleid, parentid);
	}
	
	/** 授权按钮组 **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public List<SysButton> querybutton(SysButtonQvo qvo) {
		return resourcesDao.querybutton(qvo);
	}
	
	/*** 菜单Icon **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public List<SysMenuIcon> queryicon(SysMenuIconQvo qvo) {
		return resourcesDao.queryicon(qvo);
	}
	
	/** 获取颜色列表 **/
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public List<SysColor> querycolor(SysColorQvo qvo) {
		return resourcesDao.querycolor(qvo);
	}
}
