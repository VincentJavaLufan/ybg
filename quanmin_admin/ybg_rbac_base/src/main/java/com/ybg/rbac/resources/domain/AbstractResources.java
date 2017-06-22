package com.ybg.rbac.resources.domain;
import java.util.LinkedList;
import java.util.List;
import com.ybg.component.org.inter.Organization;

/** 菜单抽象类 **/
public abstract class AbstractResources implements Organization {
	
	/** 编号 **/
	private String					id;
	/** 菜单名称 **/
	private String					name;
	/** 父级编号 **/
	private String					parentid;
	/** 菜单标识 **/
	private String					reskey;
	/** 种类 1 目录，2菜单 ，3 按钮 **/
	private String					type;
	/** 菜单地址 **/
	private String					resurl;
	/** 菜单等级 **/
	private Integer					level;
	/** 菜单图标 **/
	private String					icon;
	/** 是否隐藏 **/
	private Integer					ishide;
	/** 描述 **/
	private String					description;
	/** 是否删除 **/
	private Integer					isdelete;
	/** 颜色ID **/
	private Integer					colorid;
	public List<AbstractResources>	list;
	
	public AbstractResources() {
		list = new LinkedList<AbstractResources>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentid() {
		return parentid;
	}
	
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	public String getReskey() {
		return reskey;
	}
	
	public void setReskey(String reskey) {
		this.reskey = reskey;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getResurl() {
		return resurl;
	}
	
	public void setResurl(String resurl) {
		this.resurl = resurl;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getIshide() {
		return ishide;
	}
	
	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIsdelete() {
		return isdelete;
	}
	
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	public Integer getColorid() {
		return colorid;
	}
	
	public void setColorid(Integer colorid) {
		this.colorid = colorid;
	}
	
	public abstract boolean isLeaf();
	
	public abstract void add(Organization org);
	
	public abstract void remove(Organization org);
}
