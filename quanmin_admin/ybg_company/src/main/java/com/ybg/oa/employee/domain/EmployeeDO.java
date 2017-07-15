package com.ybg.oa.employee.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 部门和用户之间绑定
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-15
 */
 @ApiModel("实体（数据库)")
public class EmployeeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "", hidden = false)
	private String id;
	//企业名称
	@ApiModelProperty(name = "name", dataType = "java.lang.String", value = "企业名称", hidden = false)
	private String name;
	//公司编码
	@ApiModelProperty(name = "companyid", dataType = "java.lang.String", value = "公司编码", hidden = false)
	private String companyid;
	//部门ID
	@ApiModelProperty(name = "deptid", dataType = "java.lang.String", value = "部门ID", hidden = false)
	private String deptid;
	//用户ID
	@ApiModelProperty(name = "userid", dataType = "java.lang.String", value = "用户ID", hidden = false)
	private String userid;
	//是否管理员,0否，1 是
	@ApiModelProperty(name = "manager", dataType = "java.lang.Integer", value = "是否管理员,0否，1 是", hidden = false)
	private Integer manager;
	//公司名称
	@ApiModelProperty(name = "companyname", dataType = "java.lang.String", value = "公司名称", hidden = false)
	private String companyname;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：企业名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：企业名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：公司编码
	 */
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取：公司编码
	 */
	public String getCompanyid() {
		return companyid;
	}
	/**
	 * 设置：部门ID
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * 获取：部门ID
	 */
	public String getDeptid() {
		return deptid;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户ID
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置：是否管理员,0否，1 是
	 */
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	/**
	 * 获取：是否管理员,0否，1 是
	 */
	public Integer getManager() {
		return manager;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyname() {
		return companyname;
	}
}
