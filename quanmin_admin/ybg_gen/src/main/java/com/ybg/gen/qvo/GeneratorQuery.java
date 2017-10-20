package com.ybg.gen.qvo;
import java.io.Serializable;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class GeneratorQuery implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2999652572222957636L;
	private String				tablename;
	
	public String getTablename() {
		return tablename;
	}
	
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
