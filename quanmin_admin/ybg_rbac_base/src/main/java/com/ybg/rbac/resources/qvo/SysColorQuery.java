package com.ybg.rbac.resources.qvo;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.rbac.resources.domain.SysColorDO;

@SuppressWarnings("serial")
public class SysColorQuery extends SysColorDO implements BaseQueryAble {
	
	private boolean blurred;
	
	@Override
	public String toString() {
		return "SysColorQvo [blurred=" + blurred + ", getId()=" + getId() + ", getColorclass()=" + getColorclass() + ", getDescription()=" + getDescription() + "]";
	}
	
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
}
