package com.ybg.component.org.inter;
/** * @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1组织接口 **/
public interface Organization {
	
	/** 是否子节点
	 * 
	 * @return */
	boolean isLeaf();
	
	/** 创建组织
	 * 
	 * @param org
	 */
	void add(Organization org);
	
	/** 删除组织
	 * 
	 * @param org
	 */
	void remove(Organization org);
}
