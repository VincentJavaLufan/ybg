package com.egzosn.pay.payorder.domain;

import java.io.Serializable;

import com.egzosn.pay.common.bean.PayOrder;

/**
 * 订单（开通会员型，其他请自行实现。这不是商城系统。）
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class PayorderDO extends PayOrder implements Serializable {
	String id;
	String userid;
	String payid;
	String transactionype;

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getTransactionype() {
		return transactionype;
	}

	public void setTransactionype(String transactionype) {
		this.transactionype = transactionype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "PayorderDO [id=" + id + ", userid=" + userid + ", getCurType()=" + getCurType() + ", getSubject()=" + getSubject() + ", getBody()=" + getBody() + ", getPrice()=" + getPrice() + ", getTradeNo()=" + getTradeNo() + ", getOutTradeNo()=" + getOutTradeNo() + ", getTransactionType()=" + getTransactionType() + ", getBankType()=" + getBankType() + ", getSpbillCreateIp()=" + getSpbillCreateIp() + ", getAuthCode()=" + getAuthCode() + ", getDeviceInfo()=" + getDeviceInfo() + ", getWapUrl()=" + getWapUrl() + ", getWapName()=" + getWapName() + ", getOpenid()=" + getOpenid() + "]";
	}

}
