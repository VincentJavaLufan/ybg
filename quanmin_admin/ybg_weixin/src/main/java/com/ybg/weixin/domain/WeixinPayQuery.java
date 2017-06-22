package com.ybg.weixin.domain;
/** 微信查询订单的签名类 **/
public class WeixinPayQuery {
	
	String	appid;			// 公众账号ID
	String	mch_id;			// 商户号
	String	out_trade_no;	// 微信订单号
	String	nonce_str;		// 随机字符串
	String	sign;
	String	trade_state;	// 支付状态
	
	public String getTrade_state() {
		return trade_state;
	}
	
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getMch_id() {
		return mch_id;
	}
	
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	public String getNonce_str() {
		return nonce_str;
	}
	
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
}
