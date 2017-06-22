package com.ybg.rbac.resources.domain;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SysColorI implements Serializable {
	
	private Integer	id;
	private String	colorclass;	// 颜色
	private String	description;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getColorclass() {
		return colorclass;
	}
	
	public void setColorclass(String colorclass) {
		this.colorclass = colorclass;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "SysColorI [id=" + id + ", colorclass=" + colorclass + ", description=" + description + "]";
	}
}
