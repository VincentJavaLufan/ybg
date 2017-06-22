package com.ybg.rbac.resources.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.domain.SysButton;
import com.ybg.rbac.resources.domain.SysColor;
import com.ybg.rbac.resources.domain.SysMenuIcon;
import com.ybg.rbac.resources.domain.SysResources;
import com.ybg.rbac.resources.qvo.ResourcesQvo;
import com.ybg.rbac.resources.qvo.SysButtonQvo;
import com.ybg.rbac.resources.qvo.SysColorQvo;
import com.ybg.rbac.resources.qvo.SysMenuIconQvo;

public interface ResourcesService {
	
	/** 返回主键的创建 **/
	// sys_resources
	SysResources create(SysResources bean);
	
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
	Page query(Page page, ResourcesQvo qvo);
	
	/** 不分页查询 **/
	// sys_resources
	List<SysResources> query(ResourcesQvo qvo);
	
	/** 角色 权限集合 **/
	// sys_res_role
	// sys_resources
	List<SysResources> getRolesByUserId(String roleid);
	
	/** 授权的按钮操作 **/
	// sys_res_role
	// sys_resources
	List<SysResources> getOperatorButton(String roleid, String parentid);
	
	/** 授权按钮组 **/
	// sys_button
	List<SysButton> querybutton(SysButtonQvo qvo);
	
	/** 获取菜单样式列表 **/
	List<SysMenuIcon> queryicon(SysMenuIconQvo qvo);
	
	/** 获取颜色列表 **/
	List<SysColor> querycolor(SysColorQvo qvo);
	
	void removebyid(String id);
	
	SysResources get(String id);
}
