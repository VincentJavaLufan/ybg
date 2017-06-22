package com.ybg.weixin.domain;
import java.io.Serializable;

/** 微信预支付 **/
public class WeixinPayRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1061054956524495557L;
	private Integer				id;
	private String				open_id;									// 订单号
	private String				body;										// 商品名称
	private Float				totalfee;									// 总价
	private String				createtime;
	private String				orderid;									// 订单号
	private String				status;										// 订单状态
	private Integer				ywid;										// 业务表的ID
	
	public Integer getYwid() {
		return ywid;
	}
	
	public void setYwid(Integer ywid) {
		this.ywid = ywid;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOpen_id() {
		return open_id;
	}
	
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public Float getTotalfee() {
		return totalfee;
	}
	
	public void setTotalfee(Float totalfee) {
		this.totalfee = totalfee;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getOrderid() {
		return orderid;
	}
	
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
