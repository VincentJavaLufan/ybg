package com.egzosn.pay.seller.domain;

import com.egzosn.pay.common.bean.MsgType;

/**
 * 平台商户
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class SellerDO {

	/** 支付账号id **/
	private String payid;
	/** 支付合作id,商户id，差不多是支付平台的账号或id **/
	private String partner;
	/** 应用id **/
	private String appid;
	/**
	 * 支付平台公钥(签名校验使用)，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况
	 **/
	private String publickey;
	/** 应用私钥(生成签名) **/
	private String privatekey;
	/** 异步回调地址 **/
	private String notifyurl;
	/** 同步回调地址 **/
	private String returnurl;
	/** 收款账号 **/
	private String seller;
	/** 请求证书地址，请使用绝对路径 **/
	private String keystorepath;
	/** 证书对应的密码 **/
	private String storepassword;
	/** 签名类型 **/
	private String signtype;
	/** 编码类型 枚举值，字符编码 utf-8,gbk等等 **/
	private String inputcharset;
	/**
	 * 支付类型,aliPay：支付宝，wxPay：微信, youdianPay:
	 * 友店微信,此处开发者自定义对应com.egzosn.pay.seller.entity.PayType枚举值
	 **/
	private PayType paytype;
	/** 消息类型，text,xml,json **/
	private MsgType msgtype;
	// /** 是否为测试环境 **/
	// private boolean istest = false;
	private Integer test;

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getReturnurl() {
		return returnurl;
	}

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getKeystorepath() {
		return keystorepath;
	}

	public void setKeystorepath(String keystorepath) {
		this.keystorepath = keystorepath;
	}

	public String getStorepassword() {
		return storepassword;
	}

	public void setStorepassword(String storepassword) {
		this.storepassword = storepassword;
	}

	public String getSigntype() {
		return signtype;
	}

	public void setSigntype(String signtype) {
		this.signtype = signtype;
	}

	public String getInputcharset() {
		return inputcharset;
	}

	public void setInputcharset(String inputcharset) {
		this.inputcharset = inputcharset;
	}

	public PayType getPaytype() {
		return paytype;
	}

	public void setPaytype(PayType paytype) {
		this.paytype = paytype;
	}

	public MsgType getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(MsgType msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getTest() {
		return test;
	}

	public void setTest(Integer test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "ApyAccountDO [payid=" + payid + ", partner=" + partner + ", appid=" + appid + ", publickey=" + publickey + ", privatekey=" + privatekey + ", notifyurl=" + notifyurl + ", returnurl=" + returnurl + ", seller=" + seller + ", keystorepath=" + keystorepath + ", storepassword=" + storepassword + ", signtype=" + signtype + ", inputcharset=" + inputcharset + ", paytype=" + paytype + ", msgtype=" + msgtype + ", test=" + test + "]";
	}

}
