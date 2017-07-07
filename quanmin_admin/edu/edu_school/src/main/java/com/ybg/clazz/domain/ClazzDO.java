package com.ybg.clazz.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-07
 */
@ApiModel("实体（数据库)")
public class ClazzDO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 班级编码
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "班级编码", hidden = false)
	private String id;
	// 班级名称
	@ApiModelProperty(name = "classname", dataType = "java.lang.String", value = "班级名称", hidden = false)
	private String classname;
	// 学校ID
	@ApiModelProperty(name = "schoolid", dataType = "java.lang.String", value = "学校ID", hidden = false)
	private String schoolid;
	// 年级ID,3开始代表幼儿小班6一年级
	@ApiModelProperty(name = "gradeid", dataType = "java.lang.Integer", value = "年级ID,3开始代表幼儿小班6一年级", hidden = false)
	private Integer gradeid;
	// 年级名称
	@ApiModelProperty(name = "gradename", dataType = "java.lang.String", value = "年级名称", hidden = false)
	private String gradename;
	// 地区ID
	@ApiModelProperty(name = "regionid", dataType = "java.lang.Integer", value = "地区ID", hidden = false)
	private Integer regionid;
	// 学校名称
	@ApiModelProperty(name = "schoolname", dataType = "java.lang.String", value = "学校名称", hidden = false)
	private String schoolname;

	/**
	 * 设置：班级编码
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取：班级编码
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置：班级名称
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}

	/**
	 * 获取：班级名称
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * 设置：学校ID
	 */
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	/**
	 * 获取：学校ID
	 */
	public String getSchoolid() {
		return schoolid;
	}

	/**
	 * 设置：年级ID,3开始代表幼儿小班6一年级
	 */
	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}

	/**
	 * 获取：年级ID,3开始代表幼儿小班6一年级
	 */
	public Integer getGradeid() {
		return gradeid;
	}

	/**
	 * 设置：年级名称
	 */
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	/**
	 * 获取：年级名称
	 */
	public String getGradename() {
		return gradename;
	}

	/**
	 * 设置：地区ID
	 */
	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}

	/**
	 * 获取：地区ID
	 */
	public Integer getRegionid() {
		return regionid;
	}

	/**
	 * 设置：学校名称
	 */
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	/**
	 * 获取：学校名称
	 */
	public String getSchoolname() {
		return schoolname;
	}
}
