package com.ybg.menu.domain;
import java.util.ArrayList;
import java.util.List;

public class WeixinButtonVO extends WeixinButtonDO implements Comparable<WeixinButtonVO> {
	
	List<WeixinButtonVO> sub_button = new ArrayList<>();
	
	public List<WeixinButtonVO> getSub_button() {
		return sub_button;
	}
	
	public void setSub_button(List<WeixinButtonVO> sub_button) {
		this.sub_button = sub_button;
	}
	
	String parentname;
	
	public String getParentname() {
		return parentname;
	}
	
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	@Override
	public int compareTo(WeixinButtonVO o) {
		int i = this.getMenuorder() - o.getMenuorder();
		if (i == 0) {
			return this.getButtonorder() - o.getButtonorder();
		}
		return i;
	}
}
