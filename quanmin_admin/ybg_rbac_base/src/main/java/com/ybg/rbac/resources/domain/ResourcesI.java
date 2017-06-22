package com.ybg.rbac.resources.domain;
import java.io.Serializable;

/** 菜单类实体（ 数据库） **/
public  class ResourcesI implements Serializable {
	
	private static final long	serialVersionUID	= -8990353087139542849L;
	/** 编号 **/
	private String				id;
	/** 菜单名称 **/
	private String				name;
	/** 父级编号 **/
	private String				parentid;
	/** 菜单标识 **/
	private String				reskey;
	/** 种类 1 目录，2菜单 ，3 按钮 **/
	private String				type;
	/** 菜单地址 **/
	private String				resurl;
	/** 菜单等级 **/
	private Integer				level;
	/** 菜单图标 **/
	private String				icon;
	/** 是否隐藏 **/
	private Integer				ishide;
	/** 描述 **/
	private String				description;
	/** 是否删除 **/
	private Integer				isdelete;
	/** 颜色ID **/
	private Integer				colorid;
								
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentid() {
		return parentid;
	}
	
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	public String getReskey() {
		return reskey;
	}
	
	public void setReskey(String reskey) {
		this.reskey = reskey;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getResurl() {
		return resurl;
	}
	
	public void setResurl(String resurl) {
		this.resurl = resurl;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getIshide() {
		return ishide;
	}
	
	public void setIshide(Integer ishide) {
		this.ishide = ishide;
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
	
	public Integer getColorid() {
		return colorid;
	}
	
	public void setColorid(Integer colorid) {
		this.colorid = colorid;
	}
	
	@Override
	public String toString() {
		return "ResourcesI [id=" + id + ", name=" + name + ", parentid=" + parentid + ", reskey=" + reskey + ", type=" + type + ", resurl=" + resurl + ", level=" + level + ", icon=" + icon + ", ishide=" + ishide + ", description=" + description + ", isdelete=" + isdelete + ", colorid=" + colorid + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colorid == null) ? 0 : colorid.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isdelete == null) ? 0 : isdelete.hashCode());
		result = prime * result + ((ishide == null) ? 0 : ishide.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
		result = prime * result + ((reskey == null) ? 0 : reskey.hashCode());
		result = prime * result + ((resurl == null) ? 0 : resurl.hashCode());
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
		ResourcesI other = (ResourcesI) obj;
		if (colorid == null) {
			if (other.colorid != null)
				return false;
		}
		else if (!colorid.equals(other.colorid))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		}
		else if (!icon.equals(other.icon))
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
		if (ishide == null) {
			if (other.ishide != null)
				return false;
		}
		else if (!ishide.equals(other.ishide))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		}
		else if (!level.equals(other.level))
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
		if (reskey == null) {
			if (other.reskey != null)
				return false;
		}
		else if (!reskey.equals(other.reskey))
			return false;
		if (resurl == null) {
			if (other.resurl != null)
				return false;
		}
		else if (!resurl.equals(other.resurl))
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
