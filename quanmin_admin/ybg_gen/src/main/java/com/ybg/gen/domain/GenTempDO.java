package com.ybg.gen.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2018-02-05
 */
 @ApiModel("实体（数据库)")
public class GenTempDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "", hidden = false)
	private String id;
	//描述
	@ApiModelProperty(name = "description", dataType = "java.lang.String", value = "描述", hidden = false)
	private String description;
	//文件名称
	@ApiModelProperty(name = "genfilename", dataType = "java.lang.String", value = "文件名称", hidden = false)
	private String genfilename;
	//
	@ApiModelProperty(name = "gmtCreate", dataType = "java.lang.String", value = "", hidden = false)
	private String gmtCreate;
	//
	@ApiModelProperty(name = "gmtModified", dataType = "java.lang.String", value = "", hidden = false)
	private String gmtModified;
	//文本内容
	@ApiModelProperty(name = "gencontext", dataType = "java.lang.String", value = "文本内容", hidden = false)
	private String gencontext;
	//是否启用
	@ApiModelProperty(name = "state", dataType = "java.lang.Integer", value = "是否启用", hidden = false)
	private Integer state;

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
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：文件名称
	 */
	public void setGenfilename(String genfilename) {
		this.genfilename = genfilename;
	}
	/**
	 * 获取：文件名称
	 */
	public String getGenfilename() {
		return genfilename;
	}
	/**
	 * 设置：
	 */
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：
	 */
	public String getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：
	 */
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：
	 */
	public String getGmtModified() {
		return gmtModified;
	}
	/**
	 * 设置：文本内容
	 */
	public void setGencontext(String gencontext) {
		this.gencontext = gencontext;
	}
	/**
	 * 获取：文本内容
	 */
	public String getGencontext() {
		return gencontext;
	}
	/**
	 * 设置：是否启用
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：是否启用
	 */
	public Integer getState() {
		return state;
	}
}
