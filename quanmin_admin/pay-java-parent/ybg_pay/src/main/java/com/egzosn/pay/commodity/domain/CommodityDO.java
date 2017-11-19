package com.egzosn.pay.commodity.domain;

import java.math.BigDecimal;

/**
 * 商品
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class CommodityDO {

	String id;
	/** 名称 **/
	String name;
	/** 计量单位，件，米，个。。。 **/
	String type;
	/** 价格 **/
	BigDecimal price;
	/** 图片 **/
	String picture;
	/** 商品描述 **/
	String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CommodityDO [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", picture=" + picture + ", description=" + description + "]";
	}

}
