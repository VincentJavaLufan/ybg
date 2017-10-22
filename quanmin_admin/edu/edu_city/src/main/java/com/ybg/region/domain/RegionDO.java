package com.ybg.region.domain;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 行政区域表
 * 
 * @author Deament
 * @email
 * @date 2017-07-06 */
@ApiModel("实体（数据库)")
public class RegionDO implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	// 主键
	@ApiModelProperty(name = "pkid", dataType = "java.lang.Integer", value = "主键", hidden = false)
	private Integer				pkid;
	// 区域名称
	@ApiModelProperty(name = "regionname", dataType = "java.lang.String", value = "区域名称", hidden = false)
	private String				regionname;
	// 级别
	@ApiModelProperty(name = "level", dataType = "java.lang.Integer", value = "级别", hidden = false)
	private Integer				level;
	// 父级RegionId
	@ApiModelProperty(name = "parentid", dataType = "java.lang.Integer", value = "父级RegionId", hidden = false)
	private Integer				parentid;
	// 城市编码
	@ApiModelProperty(name = "citycode", dataType = "java.lang.String", value = "城市编码", hidden = false)
	private String				citycode;
	// 区域编码
	@ApiModelProperty(name = "adcode", dataType = "java.lang.String", value = "区域编码", hidden = false)
	private String				adcode;
	// 中心经度
	@ApiModelProperty(name = "centerlng", dataType = "java.lang.Double", value = "中心经度", hidden = false)
	private Double				centerlng;
	// 中心维度
	@ApiModelProperty(name = "centerlat", dataType = "java.lang.Double", value = "中心维度", hidden = false)
	private Double				centerlat;
	// 省Id
	@ApiModelProperty(name = "provinceid", dataType = "java.lang.Integer", value = "省Id", hidden = false)
	private Integer				provinceid;
	// 省名称
	@ApiModelProperty(name = "provincename", dataType = "java.lang.String", value = "省名称", hidden = false)
	private String				provincename;
	// 市Id
	@ApiModelProperty(name = "cityid", dataType = "java.lang.Integer", value = "市Id", hidden = false)
	private Integer				cityid;
	// 市名称
	@ApiModelProperty(name = "cityname", dataType = "java.lang.String", value = "市名称", hidden = false)
	private String				cityname;
	// 区Id
	@ApiModelProperty(name = "districtid", dataType = "java.lang.Integer", value = "区Id", hidden = false)
	private Integer				districtid;
	// 区名称
	@ApiModelProperty(name = "districtname", dataType = "java.lang.String", value = "区名称", hidden = false)
	private String				districtname;
	// 是否有效
	@ApiModelProperty(name = "isactive", dataType = "java.lang.Integer", value = "是否有效", hidden = false)
	private Integer				isactive;
	// 创建人
	@ApiModelProperty(name = "createby", dataType = "java.lang.String", value = "创建人", hidden = false)
	private String				createby;
	// 创建时间
	@ApiModelProperty(name = "createtime", dataType = "java.lang.String", value = "创建时间", hidden = false)
	private String				createtime;
	// 最后修改人
	@ApiModelProperty(name = "modifyby", dataType = "java.lang.String", value = "最后修改人", hidden = false)
	private String				modifyby;
	// 最后修改时间
	@ApiModelProperty(name = "modifytime", dataType = "java.lang.String", value = "最后修改时间", hidden = false)
	private String				modifytime;
	
	/** 设置：主键 */
	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}
	
	/** 获取：主键 */
	public Integer getPkid() {
		return pkid;
	}
	
	/** 设置：区域名称 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	
	/** 获取：区域名称 */
	public String getRegionname() {
		return regionname;
	}
	
	/** 设置：级别 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/** 获取：级别 */
	public Integer getLevel() {
		return level;
	}
	
	/** 设置：父级RegionId */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	
	/** 获取：父级RegionId */
	public Integer getParentid() {
		return parentid;
	}
	
	/** 设置：城市编码 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	/** 获取：城市编码 */
	public String getCitycode() {
		return citycode;
	}
	
	/** 设置：区域编码 */
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	
	/** 获取：区域编码 */
	public String getAdcode() {
		return adcode;
	}
	
	/** 设置：中心经度 */
	public void setCenterlng(Double centerlng) {
		this.centerlng = centerlng;
	}
	
	/** 获取：中心经度 */
	public Double getCenterlng() {
		return centerlng;
	}
	
	/** 设置：中心维度 */
	public void setCenterlat(Double centerlat) {
		this.centerlat = centerlat;
	}
	
	/** 获取：中心维度 */
	public Double getCenterlat() {
		return centerlat;
	}
	
	/** 设置：省Id */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	
	/** 获取：省Id */
	public Integer getProvinceid() {
		return provinceid;
	}
	
	/** 设置：省名称 */
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	
	/** 获取：省名称 */
	public String getProvincename() {
		return provincename;
	}
	
	/** 设置：市Id */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	
	/** 获取：市Id */
	public Integer getCityid() {
		return cityid;
	}
	
	/** 设置：市名称 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	/** 获取：市名称 */
	public String getCityname() {
		return cityname;
	}
	
	/** 设置：区Id */
	public void setDistrictid(Integer districtid) {
		this.districtid = districtid;
	}
	
	/** 获取：区Id */
	public Integer getDistrictid() {
		return districtid;
	}
	
	/** 设置：区名称 */
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	
	/** 获取：区名称 */
	public String getDistrictname() {
		return districtname;
	}
	
	/** 设置：是否有效 */
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	
	/** 获取：是否有效 */
	public Integer getIsactive() {
		return isactive;
	}
	
	/** 设置：创建人 */
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	/** 获取：创建人 */
	public String getCreateby() {
		return createby;
	}
	
	/** 设置：创建时间 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	/** 获取：创建时间 */
	public String getCreatetime() {
		return createtime;
	}
	
	/** 设置：最后修改人 */
	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}
	
	/** 获取：最后修改人 */
	public String getModifyby() {
		return modifyby;
	}
	
	/** 设置：最后修改时间 */
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	
	/** 获取：最后修改时间 */
	public String getModifytime() {
		return modifytime;
	}
	
	@Override
	public String toString() {
		return "RegionDO [pkid=" + pkid + ", regionname=" + regionname + ", level=" + level + ", parentid=" + parentid + ", citycode=" + citycode + ", adcode=" + adcode + ", centerlng=" + centerlng + ", centerlat=" + centerlat + ", provinceid=" + provinceid + ", provincename=" + provincename + ", cityid=" + cityid + ", cityname=" + cityname + ", districtid=" + districtid + ", districtname=" + districtname + ", isactive=" + isactive + ", createby=" + createby + ", createtime=" + createtime + ", modifyby=" + modifyby + ", modifytime=" + modifytime + "]";
	}
}
