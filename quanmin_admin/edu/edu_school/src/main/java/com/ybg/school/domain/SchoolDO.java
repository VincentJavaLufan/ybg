package com.ybg.school.domain;

import java.io.Serializable;
import java.util.Date;
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
public class SchoolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "主键", hidden = false)
	private String id;
	//学校名字
	@ApiModelProperty(name = "schoolname", dataType = "java.lang.String", value = "学校名字", hidden = false)
	private String schoolname;
	//学校类型，1111四位，分别代表高中到幼儿，0001代表学校包含幼儿
	@ApiModelProperty(name = "schooltype", dataType = "java.lang.Integer", value = "学校类型，1111四位，分别代表高中到幼儿，0001代表学校包含幼儿", hidden = false)
	private Integer schooltype;
	//
	@ApiModelProperty(name = "info", dataType = "java.lang.String", value = "", hidden = false)
	private String info;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：学校名字
	 */
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	/**
	 * 获取：学校名字
	 */
	public String getSchoolname() {
		return schoolname;
	}
	/**
	 * 设置：学校类型，1111四位，分别代表高中到幼儿，0001代表学校包含幼儿
	 */
	public void setSchooltype(Integer schooltype) {
		this.schooltype = schooltype;
	}
	/**
	 * 获取：学校类型，1111四位，分别代表高中到幼儿，0001代表学校包含幼儿
	 */
	public Integer getSchooltype() {
		return schooltype;
	}
	/**
	 * 设置：
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * 获取：
	 */
	public String getInfo() {
		return info;
	}
}
