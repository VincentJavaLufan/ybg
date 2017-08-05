package com.ybg.rbac.resources.domain;
public class SysResourcesVO extends ResourcesDO {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7166979139317222396L;
	private String				colorclass;									// 颜色
	private String				parentname;									// 父目录名称
	
	public String getParentname() {
		return parentname;
	}
	
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	public String getColorclass() {
		return colorclass;
	}
	
	public void setColorclass(String colorclass) {
		this.colorclass = colorclass;
	}
}
