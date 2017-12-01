package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * xuweibo测试用
 *
 * @author auto create
 * @since 1.0, 2017-11-17 11:02:20
 */
public class AlipayOpenAppXwbtestabcQueryModel extends AlipayObject {

	private static final long serialVersionUID = 2375312963242287463L;

	/**
	 * 1
	 */
	@ApiField("xwb")
	private String xwb;

	public String getXwb() {
		return this.xwb;
	}
	public void setXwb(String xwb) {
		this.xwb = xwb;
	}

}
