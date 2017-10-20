package com.ybg.rbac.role.domain;
import java.util.List;

public class SysRoleVO extends RoleDO {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6992716444714136013L;
	/** 角色权限列表 **/
	private List<String>		menuIdList;
	
	public List<String> getMenuIdList() {
		return menuIdList;
	}
	
	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}
	
	@Override
	public String toString() {
		return "SysRoleVO [menuIdList=" + menuIdList + ", getId()=" + getId() + ", getState()=" + getState() + ", getName()=" + getName() + ", getRolekey()=" + getRolekey() + ", getDescription()=" + getDescription() + ", getIsdelete()=" + getIsdelete() + "]";
	}
}
