package com.ybg.rbac.resources.qvo;
import com.ybg.base.jdbc.BaseQvoInter;
import com.ybg.rbac.resources.domain.ResourcesI;

public class ResourcesQvo extends ResourcesI implements BaseQvoInter {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2003511996451369333L;
	boolean						blurred;									// 是否模糊查询
								
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
