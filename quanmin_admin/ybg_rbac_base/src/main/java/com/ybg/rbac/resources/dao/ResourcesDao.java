package com.ybg.rbac.resources.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;

public interface ResourcesDao {
	
	/** 返回主键的创建 **/
	SysResourcesVO save(SysResourcesVO bean);
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询
	 * 
	 * @throws Exception
	 **/
	Page list(Page page, ResourcesQuery qvo) throws Exception;
	
	/** 不分页查询
	 * 
	 * @throws Exception
	 **/
	List<SysResourcesVO> list(ResourcesQuery qvo) throws Exception;
	
	/** 角色 权限集合
	 * 
	 * @throws Exception
	 **/
	List<SysResourcesVO> getRolesByUserId(String roleid) throws Exception;
	
	/** 授权的按钮操作
	 * 
	 * @throws Exception
	 **/
	List<SysResourcesVO> getOperatorButton(String roleid, String parentid) throws Exception;
}
