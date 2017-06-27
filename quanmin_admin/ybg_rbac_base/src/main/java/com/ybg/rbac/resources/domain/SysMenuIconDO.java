package com.ybg.rbac.resources.domain;
import java.io.Serializable;

/** 菜单图标 **/
@SuppressWarnings("serial")
public class SysMenuIconDO implements Serializable {
	
	private Integer	id;
	private String	name;
	private String	iconclass;
	private String	createtime;
	private String	type;
					
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIconclass() {
		return iconclass;
	}
	
	public void setIconclass(String iconclass) {
		this.iconclass = iconclass;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "SysMenuIconI [id=" + id + ", name=" + name + ", iconclass=" + iconclass + ", createtime=" + createtime + ", type=" + type + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((iconclass == null) ? 0 : iconclass.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		SysMenuIconDO other = (SysMenuIconDO) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		}
		else if (!createtime.equals(other.createtime))
			return false;
		if (iconclass == null) {
			if (other.iconclass != null)
				return false;
		}
		else if (!iconclass.equals(other.iconclass))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		}
		else if (!type.equals(other.type))
			return false;
		return true;
	}
}
