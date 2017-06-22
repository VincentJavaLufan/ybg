package com.ybg.rbac.role.domain;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 角色实体（数据库） **/
@ApiModel("角色实体（数据库)")
public class RoleI implements Serializable {
	
	private static final long	serialVersionUID	= -1911236558803130462L;
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "角色ID", hidden = false)
	private String				id;
	@ApiModelProperty(name = "state", dataType = "java.lang.String", value = "角色状态", hidden = false)
	private String				state;
	@ApiModelProperty(name = "name", dataType = "java.lang.String", value = "角色名称", hidden = false)
	private String				name;
	@ApiModelProperty(name = "rolekey", dataType = "java.lang.String", value = "系统唯一标识", hidden = false)
	private String				rolekey;
	@ApiModelProperty(name = "description", dataType = "java.lang.String", value = "描述 ", hidden = false)
	private String				description;
	@ApiModelProperty(name = "isdelete", dataType = "java.lang.Integer", value = "描述 ", hidden = true)
	private Integer				isdelete;
	
	@Override
	public String toString() {
		return "RoleI [id=" + id + ", state=" + state + ", name=" + name + ", rolekey=" + rolekey + ", description=" + description + ", isdelete=" + isdelete + "]";
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRolekey() {
		return rolekey;
	}
	
	public void setRolekey(String rolekey) {
		this.rolekey = rolekey;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIsdelete() {
		return isdelete;
	}
	
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isdelete == null) ? 0 : isdelete.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rolekey == null) ? 0 : rolekey.hashCode());
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
		RoleI other = (RoleI) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (isdelete == null) {
			if (other.isdelete != null)
				return false;
		}
		else if (!isdelete.equals(other.isdelete))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (rolekey == null) {
			if (other.rolekey != null)
				return false;
		}
		else if (!rolekey.equals(other.rolekey))
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
