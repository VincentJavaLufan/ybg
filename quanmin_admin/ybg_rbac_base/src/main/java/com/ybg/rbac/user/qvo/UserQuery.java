package com.ybg.rbac.user.qvo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ybg.base.jdbc.BaseQueryAble;
import com.ybg.rbac.user.domain.UserVO;

@ApiModel(value = "用户查询条件", parent = UserVO.class, description = "")
public class UserQuery extends UserVO implements BaseQueryAble {
	
	private static final long	serialVersionUID	= 3000693783594458654L;
	@ApiModelProperty(name = "blurred", dataType = "java.lang.Boolean", value = "是否模糊查询")
	boolean						blurred;
	
	@Override
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "UserQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
	}
}
