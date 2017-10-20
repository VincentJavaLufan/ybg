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
	private String				table_name;
	
	public String getTable_name() {
		return table_name;
	}
	
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
}
