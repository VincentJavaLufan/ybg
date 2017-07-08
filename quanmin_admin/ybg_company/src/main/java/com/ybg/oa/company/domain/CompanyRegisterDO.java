package com.ybg.oa.company.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-08
 */
 @ApiModel("实体（数据库)")
public class CompanyRegisterDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "", hidden = false)
	private String id;
	//创建时间
	@ApiModelProperty(name = "gmtCreate", dataType = "java.lang.String", value = "创建时间", hidden = false)
	private String gmtCreate;
	//修改时间
	@ApiModelProperty(name = "gmtModified", dataType = "java.lang.String", value = "修改时间", hidden = false)
	private String gmtModified;
	//营业执照
	@ApiModelProperty(name = "business", dataType = "java.lang.String", value = "营业执照", hidden = false)
	private String business;
	//类型,1,企业，2政府，3，组织
	@ApiModelProperty(name = "companytype", dataType = "java.lang.Integer", value = "类型,1,企业，2政府，3，组织", hidden = false)
	private Integer companytype;
	//证件照片
	@ApiModelProperty(name = "credentialspic", dataType = "java.lang.String", value = "证件照片", hidden = false)
	private String credentialspic;
	//全称
	@ApiModelProperty(name = "fullname", dataType = "java.lang.String", value = "全称", hidden = false)
	private String fullname;
	//简称
	@ApiModelProperty(name = "shortname", dataType = "java.lang.String", value = "简称", hidden = false)
	private String shortname;
	//是否通过，1，通过0，不通过
	@ApiModelProperty(name = "deal", dataType = "java.lang.Integer", value = "是否通过，1，通过0，不通过", hidden = false)
	private Integer deal;
	//不通过的原因
	@ApiModelProperty(name = "dealresult", dataType = "java.lang.String", value = "不通过的原因", hidden = false)
	private String dealresult;

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
	 * 设置：营业执照
	 */
	public void setBusiness(String business) {
		this.business = business;
	}
	/**
	 * 获取：营业执照
	 */
	public String getBusiness() {
		return business;
	}
	/**
	 * 设置：类型,1,企业，2政府，3，组织
	 */
	public void setCompanytype(Integer companytype) {
		this.companytype = companytype;
	}
	/**
	 * 获取：类型,1,企业，2政府，3，组织
	 */
	public Integer getCompanytype() {
		return companytype;
	}
	/**
	 * 设置：证件照片
	 */
	public void setCredentialspic(String credentialspic) {
		this.credentialspic = credentialspic;
	}
	/**
	 * 获取：证件照片
	 */
	public String getCredentialspic() {
		return credentialspic;
	}
	/**
	 * 设置：全称
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * 获取：全称
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * 设置：简称
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	/**
	 * 获取：简称
	 */
	public String getShortname() {
		return shortname;
	}
	/**
	 * 设置：是否通过，1，通过0，不通过
	 */
	public void setDeal(Integer deal) {
		this.deal = deal;
	}
	/**
	 * 获取：是否通过，1，通过0，不通过
	 */
	public Integer getDeal() {
		return deal;
	}
	/**
	 * 设置：不通过的原因
	 */
	public void setDealresult(String dealresult) {
		this.dealresult = dealresult;
	}
	/**
	 * 获取：不通过的原因
	 */
	public String getDealresult() {
		return dealresult;
	}
}
