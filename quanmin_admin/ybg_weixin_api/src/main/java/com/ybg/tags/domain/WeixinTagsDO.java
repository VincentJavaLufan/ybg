package com.ybg.tags.domain;
import java.io.Serializable;

public class WeixinTagsDO implements Serializable {
	
	Integer	id;
	String	name;
	Integer	count;	// 此标签下粉丝数
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
}
