package com.ybg.rbac.resources.qvo;
import com.ybg.base.jdbc.BaseQvoInter;
import com.ybg.rbac.resources.domain.SysMenuIconI;

@SuppressWarnings("serial")
public class SysMenuIconQvo extends SysMenuIconI implements BaseQvoInter {
	
	private boolean blurred;
	
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "SysMenuIconQvo [blurred=" + blurred + ", getId()=" + getId() + ", getName()=" + getName() + ", getIconclass()=" + getIconclass() + ", getCreatetime()=" + getCreatetime() + ", getType()=" + getType() + "]";
	}
}
