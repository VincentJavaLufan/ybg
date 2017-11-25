package com.egzosn.pay.product.domain;

import java.math.BigDecimal;
import java.util.UUID;

import com.egzosn.pay.commodity.domain.CommodityVO;
import com.egzosn.pay.payorder.domain.PayorderVO;
import com.egzosn.pay.seller.domain.PayType;
import com.egzosn.pay.seller.service.PayResponse;

/**
 * 会员订单
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class HuiyuanProductUtil {
	public static final String HUIYUAN_PRODUCT = "88云报告会员";
	/** 折扣 **/
	public static final Float DISCOUNT = 1F;
	public static final String NAME = HUIYUAN_PRODUCT;
	public static final String TYPE = "月";
	public static final Float PRICE = 10F;

	/** 返回会员商品 **/
	public static CommodityVO getHuiyuanBean() {
		CommodityVO bean = new CommodityVO();
		bean.setDescription("88云报告会员服务");
		bean.setName(NAME);
		bean.setPicture("");
		bean.setPrice(new BigDecimal(PRICE * DISCOUNT));
		bean.setType(TYPE);
		return bean;
	}

	/** 返回会员订单 **/
	public static PayorderVO gethuiyuanOrder(String payid,String uid, PayResponse payResponse, String transactionType) {
		PayorderVO bean = new PayorderVO();
		bean.setUserid(uid);
		bean.setSubject(NAME);
		bean.setPrice(new BigDecimal(PRICE * DISCOUNT));
		bean.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
		bean.setTransactionType(PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
		bean.setTransactionype(bean.getTransactionType().getType());
		bean.setPayid(payid);
		return bean;
	}

}
