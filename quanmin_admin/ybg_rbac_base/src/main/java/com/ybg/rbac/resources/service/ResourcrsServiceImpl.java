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
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;

@Repository
public class ResourcrsServiceImpl implements ResourcesService {
	
	@Autowired
	ResourcesDao resourcesDao;
	
	/** 返回主键的创建 **/
	@Override
	@Caching(evict = { @CacheEvict(value = "resourcesCache", allEntries = true), @CacheEvict(value = "resroleCache", allEntries = true) })
	public SysResourcesVO save(SysResourcesVO bean) {
		return resourcesDao.save(bean);
	}
	
	/** 根据ID删除 **/
	@Override
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
	@Override
	@Caching(evict = { @CacheEvict(value = "resourcesCache", allEntries = true), @CacheEvict(value = "resroleCache", allEntries = true) })
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		resourcesDao.update(updatemap, wheremap);
	}
	
	/** 获取单个实体信息
	 * 
	 * @throws Exception **/
	@Override
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public SysResourcesVO get(String id) throws Exception {
		ResourcesQuery qvo = new ResourcesQuery();
		qvo.setId(id);
		List<SysResourcesVO> list = resourcesDao.list(qvo);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	/** 分页查询
	 * 
	 * @throws Exception **/
	@Override
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public Page list(Page page, ResourcesQuery qvo) throws Exception {
		return resourcesDao.list(page, qvo);
	}
	
	/** 不分页查询
	 * 
	 * @throws Exception **/
	@Override
	@Cacheable(value = "resourcesCache", key = "#root.method.name+#root.args[0]")
	public List<SysResourcesVO> list(ResourcesQuery qvo) throws Exception {
		return resourcesDao.list(qvo);
	}
	
	/** 角色 权限集合
	 * 
	 * @throws Exception **/
	@Override
	@Cacheable(value = "resroleCache", key = "#root.method.name+#root.args[0]")
	public List<SysResourcesVO> getRolesByUserId(String roleid) throws Exception {
		return resourcesDao.getRolesByUserId(roleid);
	}
	
	/** 授权的按钮操作
	 * 
	 * @throws Exception **/
	@Override
	@Cacheable(value = "resroleCache", key = "#root.method.name+#root.args[0]+#root.method.name+#root.args[1]")
	public List<SysResourcesVO> getOperatorButton(String roleid, String parentid) throws Exception {
		return resourcesDao.getOperatorButton(roleid, parentid);
	}
}
