package com.ybg.rbac.resources.domain;
/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1 */
public class SysResourcesVO extends ResourcesDO {
	
	private static final long	serialVersionUID	= -7166979139317222396L;
	/** 颜色 **/
	private String				colorclass;
	/** 父目录名称 **/
	private String				parentname;
	
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
	
	@Override
	public String toString() {
		return "SysResourcesVO [colorclass=" + colorclass + ", parentname=" + parentname + ", getId()=" + getId() + ", getName()=" + getName() + ", getParentid()=" + getParentid() + ", getReskey()=" + getReskey() + ", getType()=" + getType() + ", getResurl()=" + getResurl() + ", getLevel()=" + getLevel() + ", getIcon()=" + getIcon() + ", getIshide()=" + getIshide() + ", getDescription()=" + getDescription() + ", getIsdelete()=" + getIsdelete() + ", getColorid()=" + getColorid() + "]";
	}
}
