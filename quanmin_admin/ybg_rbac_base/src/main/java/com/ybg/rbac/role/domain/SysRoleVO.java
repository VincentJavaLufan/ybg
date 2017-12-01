package com.ybg.rbac.role.domain;
import java.util.ArrayList;
import java.util.List;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.rbac.resources.domain.SysResourcesVO;

/** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class SysRoleVO extends RoleDO {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6992716444714136013L;
	/** 角色权限id列表 **/
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
	
	List<SysResourcesVO> resourcesList;
	
	public List<SysResourcesVO> getResourcesList() {
		return resourcesList;
	}
	
	public void setResourcesList(List<SysResourcesVO> resourcesList) {
		this.resourcesList = resourcesList;
	}
	
	/** 返回所有URL **/
	public List<String> getResourceUrl() {
		List<String> allurl = new ArrayList<>();
		if (QvoConditionUtil.checkList(resourcesList)) {
			for (SysResourcesVO bean : resourcesList) {
				if (QvoConditionUtil.checkString(bean.getResurl())) {
					allurl.add(bean.getResurl());
				}
			}
		}
		return new ArrayList<>();
	}
}
