package com.ybg.oa.employee.qvo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.oa.employee.domain.EmployeeDO;


/** 查询条件 **/
@ApiModel(value = "角色查询条件", parent = EmployeeDO.class)
public class EmployeeQuery extends EmployeeDO implements BaseQueryAble {
	
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