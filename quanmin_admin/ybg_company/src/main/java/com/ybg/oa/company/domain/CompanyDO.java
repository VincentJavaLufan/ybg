package com.ybg.oa.company.domain;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-08 */
@ApiModel("实体（数据库)")
public class CompanyDO implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	// 编码
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "编码", hidden = false)
	private String				id;
	// 简称
	@ApiModelProperty(name = "name", dataType = "java.lang.String", value = "简称", hidden = false)
	private String				name;
	//
	@ApiModelProperty(name = "createTime", dataType = "java.lang.String", value = "", hidden = false)
	private String				createTime;
	// 营业执照
	@ApiModelProperty(name = "business", dataType = "java.lang.String", value = "营业执照", hidden = false)
	private String				business;
	// 类型,1,企业，2政府，3，组织
	@ApiModelProperty(name = "companytype", dataType = "java.lang.Integer", value = "类型,1,企业，2政府，3，组织", hidden = false)
	private Integer				companytype;
	// 证件照片
	@ApiModelProperty(name = "credentialspic", dataType = "java.lang.String", value = "证件照片", hidden = false)
	private String				credentialspic;
	// 全称
	@ApiModelProperty(name = "fullname", dataType = "java.lang.String", value = "全称", hidden = false)
	private String				fullname;
	// 创建时间
	@ApiModelProperty(name = "gmtCreate", dataType = "java.lang.String", value = "创建时间", hidden = false)
	private String				gmtCreate;
	// 修改时间
	@ApiModelProperty(name = "gmtModified", dataType = "java.lang.String", value = "修改时间", hidden = false)
	private String				gmtModified;
	
	/** 设置：编码 */
	public void setId(String id) {
		this.id = id;
	}
	
	/** 获取：编码 */
	public String getId() {
		return id;
	}
	
	/** 设置：简称 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 获取：简称 */
	public String getName() {
		return name;
	}
	
	/** 设置： */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/** 获取： */
	public String getCreateTime() {
		return createTime;
	}
	
	/** 设置：营业执照 */
	public void setBusiness(String business) {
		this.business = business;
	}
	
	/** 获取：营业执照 */
	public String getBusiness() {
		return business;
	}
	
	/** 设置：类型,1,企业，2政府，3，组织 */
	public void setCompanytype(Integer companytype) {
		this.companytype = companytype;
	}
	
	/** 获取：类型,1,企业，2政府，3，组织 */
	public Integer getCompanytype() {
		return companytype;
	}
	
	/** 设置：证件照片 */
	public void setCredentialspic(String credentialspic) {
		this.credentialspic = credentialspic;
	}
	
	/** 获取：证件照片 */
	public String getCredentialspic() {
		return credentialspic;
	}
	
	/** 设置：全称 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	/** 获取：全称 */
	public String getFullname() {
		return fullname;
	}
	
	/** 设置：创建时间 */
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	/** 获取：创建时间 */
	public String getGmtCreate() {
		return gmtCreate;
	}
	
	/** 设置：修改时间 */
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	/** 获取：修改时间 */
	public String getGmtModified() {
		return gmtModified;
	}
}
