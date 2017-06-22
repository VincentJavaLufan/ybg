package com.ybg.company.domain;
import java.util.Iterator;
import java.util.List;
import com.ybg.component.org.inter.Organization;

/** 部门 **/
public class DepartmentI extends AbstractCompany {
	
	private String	companyid;
	private String	parentid;
	private String	createtime;
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getParentid() {
		return parentid;
	}
	
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	public String getCompanyid() {
		return companyid;
	}
	
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	@Override
	public void add(Organization org) {
		if (org instanceof Company) {
			return;
		}
		list.add((AbstractCompany) org);
	}
	
	@Override
	public void remove(Organization org) {
		if (org instanceof Company) {
			return;
		}
		list.remove(org);
	}
	
	@Override
	public Organization get(AbstractCompany org, String id) {
		return getsub(org, id);
	}
	
	private AbstractCompany getsub(AbstractCompany org, String id) {
		if (org.getId().equals(id)) {
			return org;
		}
		else {
			if (!org.isLeaf()) {
				return getsub2(org.list, id);
			}
		}
		return null;
	}
	
	private AbstractCompany getsub2(List<AbstractCompany> list, String id) {
		AbstractCompany trueorg = null;
		Iterator<AbstractCompany> it = list.listIterator();
		while (it.hasNext()) {
			AbstractCompany org = it.next();
			if (org.getId().equals(id)) {
				trueorg = org;
			}
			else {
				if (trueorg != null && !org.isLeaf() && it.hasNext()) {
					trueorg = getsub2(org.list, id);
				}
			}
		}
		return trueorg;
	}
}
