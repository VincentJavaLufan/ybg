package com.ybg.tags.domain;
import java.io.Serializable;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class WeixinTagsDO implements Serializable {
	
	Integer	id;
	String	name;
	/** 此标签下粉丝数 */
	Integer	count;
	
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
	
	@Override
	public String toString() {
		return "WeixinTagsDO [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
