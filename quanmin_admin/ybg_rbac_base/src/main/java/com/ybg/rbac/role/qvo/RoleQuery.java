package com.ybg.rbac.role.qvo;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.rbac.role.domain.RoleDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 角色查询条件 **/
@ApiModel(value = "角色查询条件", parent = RoleDO.class)
public class RoleQuery extends RoleDO implements BaseQueryAble {
	
	private static final long	serialVersionUID	= -1516990738029741157L;
	/** 是否模糊查询 **/
	@ApiModelProperty(name = "blurred", dataType = "java.lang.Boolean", value = "是否模糊查询", hidden = true)
	private boolean				blurred;
	@Override
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "RoleQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
	}
}
