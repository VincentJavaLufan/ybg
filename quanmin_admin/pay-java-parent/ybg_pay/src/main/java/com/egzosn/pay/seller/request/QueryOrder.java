package com.egzosn.pay.seller.request;
import java.math.BigDecimal;
import java.util.Date;

/** 订单辅助接口
 * 
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2017/3/12 14:50 */
public class QueryOrder {
	/**商户ID**/
	private String		payId;
	/** 支付平台订单号 **/
	private String		tradeNo;
	/** 商户单号 **/
	private String		outTradeNo;
	/** 退款金额 **/
	private BigDecimal	refundAmount;
	/** 总金额 **/
	private BigDecimal	totalAmount;
	/** 账单时间：具体请查看对应支付平台 **/
	private Date		billDate;
	/** 账单时间：具体请查看对应支付平台 **/
	private String		billType;
	/** 支付平台订单号或者账单日期 **/
	private Object		tradeNoOrBillDate;
	/** 商户单号或者 账单类型 **/
	private String		outTradeNoBillType;
	/** 交易类型 **/
	private String		transactionType;
	
	public String getPayId() {
		return payId;
	}
	
	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	public String getTradeNo() {
		return tradeNo;
	}
	
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public Object getTradeNoOrBillDate() {
		return tradeNoOrBillDate;
	}
	
	public void setTradeNoOrBillDate(Object tradeNoOrBillDate) {
		this.tradeNoOrBillDate = tradeNoOrBillDate;
	}
	
	public String getOutTradeNoBillType() {
		return outTradeNoBillType;
	}
	
	public void setOutTradeNoBillType(String outTradeNoBillType) {
		this.outTradeNoBillType = outTradeNoBillType;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
