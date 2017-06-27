package com.ybg.rbac.role.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.rbac.role.domain.RoleResDO;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQvo;

public interface RoleService {
	
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	// sys_role
	SysRoleVO createandid(SysRoleVO role) throws Exception;
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	// sys_role
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询 **/
	// sys_role
	Page query(Page page, RoleQvo qvo);
	
	/** 不分页查询 **/
	// sys_role
	List<SysRoleVO> query(RoleQvo qvo);
	
	/** 角色授权 增删改都在里面了 **/
	// sys_res_role
	void saveOrupdateRole_Res(List<RoleResDO> list);
	
	SysRoleVO get(String id);
	
	void removebyid(String id);
}
