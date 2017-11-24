package com.egzosn.pay.product.domain;

import java.math.BigDecimal;

import com.egzosn.pay.commodity.domain.CommodityVO;

/**
 * 会员订单
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class HuiyuanProduct extends CommodityVO {
	public static final String HUIYUAN_PRODUCT = "88云报告会员";
	String name = HUIYUAN_PRODUCT;
	String type = "月";
	BigDecimal price = new BigDecimal(10.00);
}
