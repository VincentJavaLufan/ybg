package com.ybg.rbac.resources.domain;
import com.ybg.component.org.inter.Organization;

public class Menu extends AbstractResources {
	
	@Override
	public String getType() {
		return "2";
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
		return false;
	}
	
	@Override
	public void add(Organization org) {
		if (org instanceof Directories) {
			return;// 不允许添加目录
		}
		if (org instanceof Menu) {
			return;// 不允许添加菜单
		}
		list.add((Button) org);
	}
	
	@Override
	public void remove(Organization org) {
		list.remove(org);
	}
}
