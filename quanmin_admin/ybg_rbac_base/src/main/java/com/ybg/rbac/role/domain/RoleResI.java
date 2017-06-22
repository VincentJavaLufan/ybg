package com.ybg.rbac.role.domain;
import java.io.Serializable;

/** 菜单 角色关联实体 （数据库) **/
public class RoleResI implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5334721818648118713L;
	/** 角色编号 **/
	private String				roleid;										// 角色编号
	/** 菜单编号 **/
	private String				resid;										// 菜单编号
	/** 是否可用 **/
	private Integer				state;										// 是否可用
								
	public String getRoleid() {
		return roleid;
	}
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getResid() {
		return resid;
	}
	
	public void setResid(String resid) {
		this.resid = resid;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "RoleResI [roleid=" + roleid + ", resid=" + resid + ", state=" + state + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resid == null) ? 0 : resid.hashCode());
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleResI other = (RoleResI) obj;
		if (resid == null) {
			if (other.resid != null)
				return false;
		}
		else if (!resid.equals(other.resid))
			return false;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		}
		else if (!roleid.equals(other.roleid))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		}
		else if (!state.equals(other.state))
			return false;
		return true;
	}
}
