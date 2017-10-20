package com.ybg.base.jdbc;
import java.util.LinkedHashMap;

/** * @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1<br>
 * 		改进的LinkedHashMap <br>
 *       put 元素取消 value为null的集合 **/
public class BaseMap<K, V> extends LinkedHashMap<K, V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 当value 为空时，不加入集合中 **/
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		key = (K) key.toString().toLowerCase();
		if (key != null && value != null) {
			super.put(key, value);
		}
		return null;
	}
}
