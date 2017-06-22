package com.ybg.company.domain;
import java.util.LinkedList;
import com.ybg.component.org.inter.Organization;

/** 企业组织抽象类 **/
public abstract class AbstractCompany implements Organization {
	
	private String				id;
	private String				name;
	LinkedList<AbstractCompany>	list;
	
	public AbstractCompany() {
		list = new LinkedList<AbstractCompany>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/** 是否子节点 **/
	public abstract boolean isLeaf();
	
	public abstract void add(Organization org);
	
	public abstract void remove(Organization org);
	
	public abstract Organization get(AbstractCompany org, String id);
}