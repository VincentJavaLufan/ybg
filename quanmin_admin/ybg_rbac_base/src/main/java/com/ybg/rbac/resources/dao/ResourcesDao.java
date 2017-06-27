package com.ybg.rbac.resources.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.domain.SysButtonVO;
import com.ybg.rbac.resources.domain.SysColorVO;
import com.ybg.rbac.resources.domain.SysMenuIconVO;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;
import com.ybg.rbac.resources.qvo.SysButtonQuery;
import com.ybg.rbac.resources.qvo.SysColorQuery;
import com.ybg.rbac.resources.qvo.SysMenuIconQuery;

public interface ResourcesDao {
	
	/** 返回主键的创建 **/
	// sys_resources
	SysResourcesVO create(SysResourcesVO bean);
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	// sys_resources
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询 **/
	// sys_resources
	Page query(Page page, ResourcesQuery qvo);
	
	/** 不分页查询 **/
	// sys_resources
	List<SysResourcesVO> query(ResourcesQuery qvo);
	
	/** 角色 权限集合 **/
	// sys_res_role
	// sys_resources
	List<SysResourcesVO> getRolesByUserId(String roleid);
	
	/** 授权的按钮操作 **/
	// sys_res_role
	// sys_resources
	List<SysResourcesVO> getOperatorButton(String roleid, String parentid);
	
	/** 授权按钮组 **/
	// sys_button
	List<SysButtonVO> querybutton(SysButtonQuery qvo);
	
	/** 获取菜单样式列表 **/
	List<SysMenuIconVO> queryicon(SysMenuIconQuery qvo);
	
	/** 获取颜色列表 **/
	List<SysColorVO> querycolor(SysColorQuery qvo);
}
