package com.ybg.component.org.inter;
/** 组织接口 **/
public interface Organization {
	
	/** 是否子节点 **/
	boolean isLeaf();
	
	void add(Organization org);
	
	void remove(Organization org);
}
