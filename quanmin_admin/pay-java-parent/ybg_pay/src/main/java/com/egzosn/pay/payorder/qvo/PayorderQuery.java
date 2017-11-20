package com.egzosn.pay.payorder.qvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.egzosn.pay.payorder.domain.PayorderDO;
import com.ybg.base.jdbc.BaseQueryAble;

/** 查询条件 **/
@ApiModel(value = "查询条件", parent = PayorderDO.class)
public class PayorderQuery extends PayorderDO implements BaseQueryAble {

	private static final long serialVersionUID = -1516990738029741157L;
	/** 是否模糊查询 **/
	@ApiModelProperty(name = "blurred", dataType = "java.lang.Boolean", value = "是否模糊查询", hidden = true)
	private boolean blurred;

	public boolean isBlurred() {
		return blurred;
	}

	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}

}