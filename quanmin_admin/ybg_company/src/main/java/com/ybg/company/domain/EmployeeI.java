package com.ybg.company.domain;
import com.ybg.component.org.inter.Organization;

/** 员工 **/
public class EmployeeI extends AbstractCompany {
	
	private String	companyid;
	private String	createtime;
	private String	deptid;
	
	public String getDeptid() {
		return deptid;
	}
	
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCompanyid() {
		return companyid;
	}
	
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
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
	
	@Override
	public Organization get(AbstractCompany org, String id) {
		if (org.getId().equals(id)) {
			return org;
		}
		return null;
	}
}
