package com.ybg.rbac.role.domain;
import java.util.List;

/** 批量授权
 * 
 * @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0 */
public class BatchResRoleVO {
	
	/** 权限id列表 **/
	private List<String>	menuIdList;
	/** 角色id列表 **/
	private List<String>	roleIdList;
	
	public List<String> getMenuIdList() {
		return menuIdList;
	}
	
	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}
	
	public List<String> getRoleIdList() {
		return roleIdList;
	}
	
	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
}
