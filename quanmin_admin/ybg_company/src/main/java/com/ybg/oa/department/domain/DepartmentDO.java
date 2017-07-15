package com.ybg.oa.department.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-15
 */
 @ApiModel("实体（数据库)")
public class DepartmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "ID", hidden = false)
	private String id;
	//部门名称
	@ApiModelProperty(name = "name", dataType = "java.lang.String", value = "部门名称", hidden = false)
	private String name;
	//公司ID
	@ApiModelProperty(name = "companyid", dataType = "java.lang.String", value = "公司ID", hidden = false)
	private String companyid;
	//创建时间
	@ApiModelProperty(name = "gmtCreate", dataType = "java.lang.String", value = "创建时间", hidden = false)
	private String gmtCreate;
	//父级ID
	@ApiModelProperty(name = "parentid", dataType = "java.lang.String", value = "父级ID", hidden = false)
	private String parentid;
	//修改时间
	@ApiModelProperty(name = "gmtModified", dataType = "java.lang.String", value = "修改时间", hidden = false)
	private String gmtModified;
	//公司名称
	@ApiModelProperty(name = "companyname", dataType = "java.lang.String", value = "公司名称", hidden = false)
	private String companyname;

	/**
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：公司ID
	 */
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取：公司ID
	 */
	public String getCompanyid() {
		return companyid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public String getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：父级ID
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：父级ID
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：修改时间
	 */
	public String getGmtModified() {
		return gmtModified;
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
