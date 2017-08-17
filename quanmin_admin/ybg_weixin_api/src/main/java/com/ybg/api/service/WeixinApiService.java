package com.ybg.api.service;
import java.util.Map;

public interface WeixinApiService {
	
	Map<String, String> getSetting();
	
	/**根据CODE 获取OPEN_ID  没有或报错 就返回null**/
	String getOpenidByCode(String code);
}
