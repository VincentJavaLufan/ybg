package com.ybg.rbac.role.domain;
import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*** 角色实体（数据库）
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@ApiModel("角色实体（数据库)")
public class RoleDO implements Serializable {
	
	private static final long	serialVersionUID	= -1911236558803130462L;
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "角色ID", hidden = false)
	private String				id;
	@NotBlank(message = "角色状态不能为空")
	@ApiModelProperty(name = "state", dataType = "java.lang.String", value = "角色状态", hidden = false)
	private String				state;
	@ApiModelProperty(name = "name", dataType = "java.lang.String", value = "角色名称", hidden = false)
	@NotBlank(message = "角色名称不能为空")
	private String				name;
	@NotBlank(message = "角色KEY不能为空")
	@ApiModelProperty(name = "rolekey", dataType = "java.lang.String", value = "系统唯一标识", hidden = false)
	private String				rolekey;
	@ApiModelProperty(name = "description", dataType = "java.lang.String", value = "描述 ", hidden = false)
	private String				description;
	@ApiModelProperty(name = "isdelete", dataType = "java.lang.Integer", value = "是否删除 ", hidden = true)
	private Integer				isdelete;
	@ApiModelProperty(name = "parentid", dataType = "java.lang.Integer", value = "父级角色ID ", hidden = true)
	private String				parentid;
	
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
	
	public String getParentid() {
		return parentid;
	}
	
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Override
	public String toString() {
		return "RoleDO [id=" + id + ", state=" + state + ", name=" + name + ", rolekey=" + rolekey + ", description=" + description + ", isdelete=" + isdelete + ", parentid=" + parentid + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isdelete == null) ? 0 : isdelete.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
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
		RoleDO other = (RoleDO) obj;
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
		if (parentid == null) {
			if (other.parentid != null)
				return false;
		}
		else if (!parentid.equals(other.parentid))
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
