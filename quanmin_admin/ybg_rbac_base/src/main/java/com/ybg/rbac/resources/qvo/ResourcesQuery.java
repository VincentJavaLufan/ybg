package com.ybg.rbac.resources.qvo;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.rbac.resources.domain.ResourcesDO;

public class ResourcesQuery extends ResourcesDO implements BaseQueryAble {
	
	private static final long	serialVersionUID	= -2003511996451369333L;
	// 是否模糊查询
	boolean						blurred;
	
	@Override
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "ResourcesQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
	}
}
