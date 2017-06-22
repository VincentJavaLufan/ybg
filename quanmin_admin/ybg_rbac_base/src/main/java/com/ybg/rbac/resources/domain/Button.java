package com.ybg.rbac.resources.domain;
import com.ybg.component.org.inter.Organization;


public class Button extends AbstractResources {
	
	@Override
	public String getType() {
		return "3";
	}
	
	@Override
	public String getIcon() {
		return "";
	}
	
	@Override
	public Integer getColorid() {
		return 0;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}
	
	@Override
	public void add(Organization org) {
	}
	
	@Override
	public void remove(Organization org) {
	}
}
