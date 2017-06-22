package com.ybg.weixin.domain;
import java.io.Serializable;

/** 微信H5支付发起请求的包 **/
public class Finalpackage implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String	appid;
	private String	timeStamp;
	private String	nonceStr;
	private String	package_wx;
	private String	signType;
	private String	paySign;
	
	@Override
	public String toString() {
		return "Finalpackage [appid=" + appid + ", timeStamp=" + timeStamp + ", nonceStr=" + nonceStr + ", package_wx=" + package_wx + ", signType=" + signType + ", paySign=" + paySign + "]";
	}
	
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getNonceStr() {
		return nonceStr;
	}
	
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	public String getPackage_wx() {
		return package_wx;
	}
	
	public void setPackage_wx(String package_wx) {
		this.package_wx = package_wx;
	}
	
	public String getSignType() {
		return signType;
	}
	
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	public String getPaySign() {
		return paySign;
	}
	
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
