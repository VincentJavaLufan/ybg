package com.ybg.student.domain;

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
public class StudentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//学生编码
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "学生编码", hidden = false)
	private String id;
	//学生姓名
	@ApiModelProperty(name = "studentname", dataType = "java.lang.String", value = "学生姓名", hidden = false)
	private String studentname;
	//学籍号
	@ApiModelProperty(name = "studentno", dataType = "java.lang.String", value = "学籍号", hidden = false)
	private String studentno;
	//班级编码
	@ApiModelProperty(name = "classid", dataType = "java.lang.String", value = "班级编码", hidden = false)
	private String classid;
	//年级
	@ApiModelProperty(name = "gradeid", dataType = "java.lang.Integer", value = "年级", hidden = false)
	private Integer gradeid;
	//学校ID
	@ApiModelProperty(name = "schoolid", dataType = "java.lang.String", value = "学校ID", hidden = false)
	private String schoolid;
	//年级名称
	@ApiModelProperty(name = "gradename", dataType = "java.lang.String", value = "年级名称", hidden = false)
	private String gradename;
	//
	@ApiModelProperty(name = "classname", dataType = "java.lang.String", value = "", hidden = false)
	private String classname;
	//
	@ApiModelProperty(name = "schoolname", dataType = "java.lang.String", value = "", hidden = false)
	private String schoolname;
	//身份证
	@ApiModelProperty(name = "identitycard", dataType = "java.lang.String", value = "身份证", hidden = false)
	private String identitycard;
	//头像
	@ApiModelProperty(name = "headurl", dataType = "java.lang.String", value = "头像", hidden = false)
	private String headurl;
	//
	@ApiModelProperty(name = "birthday", dataType = "java.lang.String", value = "", hidden = false)
	private String birthday;
	//创建日期
	@ApiModelProperty(name = "gmtCreate", dataType = "java.lang.String", value = "创建日期", hidden = false)
	private String gmtCreate;
	//修改时间
	@ApiModelProperty(name = "gmtModified", dataType = "java.lang.String", value = "修改时间", hidden = false)
	private String gmtModified;
	//籍贯
	@ApiModelProperty(name = "studentorign", dataType = "java.lang.String", value = "籍贯", hidden = false)
	private String studentorign;
	//住址
	@ApiModelProperty(name = "studentaddress", dataType = "java.lang.String", value = "住址", hidden = false)
	private String studentaddress;
	//监护人名字
	@ApiModelProperty(name = "parentname", dataType = "java.lang.String", value = "监护人名字", hidden = false)
	private String parentname;
	//性别
	@ApiModelProperty(name = "gender", dataType = "java.lang.Integer", value = "性别", hidden = false)
	private Integer gender;
	//城市编码（用于分表）
	@ApiModelProperty(name = "cityid", dataType = "java.lang.Integer", value = "城市编码（用于分表）", hidden = false)
	private Integer cityid;
	//地区表主键
	@ApiModelProperty(name = "regionid", dataType = "java.lang.Integer", value = "地区表主键", hidden = false)
	private Integer regionid;
	//地区ID
	@ApiModelProperty(name = "districtid", dataType = "java.lang.String", value = "地区ID", hidden = false)
	private String districtid;
	//省ID
	@ApiModelProperty(name = "provinceid", dataType = "java.lang.Integer", value = "省ID", hidden = false)
	private Integer provinceid;

	/**
	 * 设置：学生编码
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：学生编码
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getStudentname() {
		return studentname;
	}
	/**
	 * 设置：学籍号
	 */
	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}
	/**
	 * 获取：学籍号
	 */
	public String getStudentno() {
		return studentno;
	}
	/**
	 * 设置：班级编码
	 */
	public void setClassid(String classid) {
		this.classid = classid;
	}
	/**
	 * 获取：班级编码
	 */
	public String getClassid() {
		return classid;
	}
	/**
	 * 设置：年级
	 */
	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}
	/**
	 * 获取：年级
	 */
	public Integer getGradeid() {
		return gradeid;
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
	 * 设置：
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/**
	 * 获取：
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * 设置：
	 */
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	/**
	 * 获取：
	 */
	public String getSchoolname() {
		return schoolname;
	}
	/**
	 * 设置：身份证
	 */
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	/**
	 * 获取：身份证
	 */
	public String getIdentitycard() {
		return identitycard;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadurl() {
		return headurl;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：创建日期
	 */
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：创建日期
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
	 * 设置：籍贯
	 */
	public void setStudentorign(String studentorign) {
		this.studentorign = studentorign;
	}
	/**
	 * 获取：籍贯
	 */
	public String getStudentorign() {
		return studentorign;
	}
	/**
	 * 设置：住址
	 */
	public void setStudentaddress(String studentaddress) {
		this.studentaddress = studentaddress;
	}
	/**
	 * 获取：住址
	 */
	public String getStudentaddress() {
		return studentaddress;
	}
	/**
	 * 设置：监护人名字
	 */
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	/**
	 * 获取：监护人名字
	 */
	public String getParentname() {
		return parentname;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：城市编码（用于分表）
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	/**
	 * 获取：城市编码（用于分表）
	 */
	public Integer getCityid() {
		return cityid;
	}
	/**
	 * 设置：地区表主键
	 */
	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：地区表主键
	 */
	public Integer getRegionid() {
		return regionid;
	}
	/**
	 * 设置：地区ID
	 */
	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
	/**
	 * 获取：地区ID
	 */
	public String getDistrictid() {
		return districtid;
	}
	/**
	 * 设置：省ID
	 */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	/**
	 * 获取：省ID
	 */
	public Integer getProvinceid() {
		return provinceid;
	}
}
