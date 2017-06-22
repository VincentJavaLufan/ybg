package com.ybg.rbac.resources.domain;
import java.io.Serializable;

/** 系统按钮实体(数据库) **/
public class SysButtonI implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2025818048874652396L;
	/** 编号 **/
	private Integer				id;
	/** 按钮名称 **/
	private String				name;
	/** 描述 **/
	private String				description;
	/** html代码 **/
	private String				button;
								
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getButton() {
		return button;
	}
	
	public void setButton(String button) {
		this.button = button;
	}
	
	@Override
	public String toString() {
		return "SysButtonI [id=" + id + ", name=" + name + ", description=" + description + ", button=" + button + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((button == null) ? 0 : button.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SysButtonI other = (SysButtonI) obj;
		if (button == null) {
			if (other.button != null)
				return false;
		}
		else if (!button.equals(other.button))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
}
