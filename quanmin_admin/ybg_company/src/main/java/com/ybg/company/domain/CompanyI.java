package com.ybg.company.domain;
import java.util.Iterator;
import java.util.List;
import com.ybg.component.org.inter.Organization;

/** 企业 **/
public class CompanyI extends AbstractCompany {
	
	/** 是否认证/通过审核 **/
	private boolean	qualification;
	/** 创建时间 **/
	private String	createtime;
	
	public boolean isQualification() {
		return qualification;
	}
	
	public void setQualification(boolean qualification) {
		this.qualification = qualification;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	@Override
	public boolean isLeaf() {
		return false;
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
	
	@Override
	public void add(Organization org) {
		list.add((AbstractCompany) org);
	}
	
	@Override
	public void remove(Organization org) {
		list.remove(org);
	}
}
