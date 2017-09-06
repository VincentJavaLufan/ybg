package com.ybg.menu.domain;
public class WeixintMenuDO {
	
	// 一级菜单数组，个数应为1~3个
	WeixinButtonDO[] button;
	
	public WeixinButtonDO[] getButton() {
		return button;
	}
	
	public void setButton(WeixinButtonDO[] button) {
		this.button = button;
	}
}
