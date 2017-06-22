package com.ybg.rbac.resources.domain;
import com.ybg.component.org.inter.Organization;

/** 目录 **/
public class Directories extends AbstractResources {
	
	@Override
	public String getParentid() {
		return "0";
	}
	
	@Override
	public String getReskey() {
		return "";
	}
	
	@Override
	public String getType() {
		return "1";
	}
	
	@Override
	public String getResurl() {
		return "";
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
		if (org instanceof Button) {
			return;// 不允许直接添加按钮
		}
		list.add((AbstractResources) org);
	}
	
	@Override
	public void remove(Organization org) {
		list.remove(org);
	}
}
