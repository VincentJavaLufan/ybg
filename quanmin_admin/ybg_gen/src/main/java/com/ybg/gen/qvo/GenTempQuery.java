package com.ybg.gen.qvo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.gen.domain.GenTempDO;


/** 查询条件 **/
@ApiModel(value = "角色查询条件", parent = GenTempDO.class)
public class GenTempQuery extends GenTempDO implements BaseQueryAble {
	
	private static final long	serialVersionUID	= -1516990738029741157L;
	/** 是否模糊查询 **/
	@ApiModelProperty(name = "blurred", dataType = "java.lang.Boolean", value = "是否模糊查询", hidden = true)
	private boolean				blurred;
	
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	
}