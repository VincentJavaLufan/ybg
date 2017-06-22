package com.ybg.weixin.domain;
import java.io.Serializable;

/** 微信已支付 **/
public class WeixinHadPay implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2676928682951587041L;
	String						createtime;									// 交易时间
	String						appid;										// 公众号
	String						mchid;										// 商户号
	String						submchid;									// 子商户好
	String						device_info;								// 设备号
	String						transaction_id;								// 微信订单号
	String						out_trade_no;								// 商户订单号
	String						open_id;									// 用户标识
	String						trade_type;									// 交易类型
	String						result_code;								// 交易状态
	String						bank_type;									// 付款银行
	String						fee_type;									// 货币种类
	String						total_fee;									// 总金额
	String						erea;										// 企业红包金额 Enterprise red envelope amount
	String						body;										// 商品名称
	String						merchant_data_package;						// 商户数据包
	String						poundage;									// 手续费
	String						rate;										// 费率
	// 关联
	Integer						ywid;
	String						receivephone;
	String						studentname;
	Integer						studentId;
	
	@Override
	public String toString() {
		return getAppid() + "#" + getBank_type() + "#" + getBody() + "#" + getCreatetime() + "#" + getDevice_info() + "#" + getErea() + "#" + getFee_type() + "#" + getMchid() + "#" + getMerchant_data_package() + "#" + getOpen_id() + "#" + getOut_trade_no() + "#" + getPoundage() + "#" + getPoundage() + "#" + getRate() + "#" + getResult_code() + "#" + getSubmchid() + "#" + getTotal_fee() + "#" + getTrade_type() + "#" + getTransaction_id() + "#" + getYwid() + "#" + getReceivephone() + "#" + getStudentname() + "#" + getStudentId();
	}
	
	public Integer getYwid() {
		return ywid;
	}
	
	public void setYwid(Integer ywid) {
		this.ywid = ywid;
	}
	
	public String getReceivephone() {
		return receivephone;
	}
	
	public void setReceivephone(String receivephone) {
		this.receivephone = receivephone;
	}
	
	public String getStudentname() {
		return studentname;
	}
	
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getMchid() {
		return mchid;
	}
	
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	
	public String getSubmchid() {
		return submchid;
	}
	
	public void setSubmchid(String submchid) {
		this.submchid = submchid;
	}
	
	public String getDevice_info() {
		return device_info;
	}
	
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}
	
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	public String getOpen_id() {
		return open_id;
	}
	
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	public String getTrade_type() {
		return trade_type;
	}
	
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
	public String getResult_code() {
		return result_code;
	}
	
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	public String getBank_type() {
		return bank_type;
	}
	
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	
	public String getFee_type() {
		return fee_type;
	}
	
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	
	public String getTotal_fee() {
		return total_fee;
	}
	
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	
	public String getErea() {
		return erea;
	}
	
	public void setErea(String erea) {
		this.erea = erea;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getMerchant_data_package() {
		return merchant_data_package;
	}
	
	public void setMerchant_data_package(String merchant_data_package) {
		this.merchant_data_package = merchant_data_package;
	}
	
	public String getPoundage() {
		return poundage;
	}
	
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}
}
