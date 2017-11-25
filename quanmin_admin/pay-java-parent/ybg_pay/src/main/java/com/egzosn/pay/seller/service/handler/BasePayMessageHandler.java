package com.egzosn.pay.seller.service.handler;
import com.egzosn.pay.common.api.PayMessageHandler;

/** Created by ZaoSheng on 2016/6/1. @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0 */
public abstract class BasePayMessageHandler implements PayMessageHandler {
	
	/** 支付账户id **/
	private String payId;
	
	public BasePayMessageHandler(String payId) {
		this.payId = payId;
	}
	
	public String getPayId() {
		return payId;
	}
}
