package com.ybg.rbac.syspro.qvo;

import com.ybg.base.jdbc.BaseQvoInter;
import com.ybg.rbac.syspro.domain.Syspro;

public class SysproQvo extends Syspro implements BaseQvoInter {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3000693783594458654L;
	boolean						blurred;									// 是否模糊查询
								
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "SysproQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
	}
}
