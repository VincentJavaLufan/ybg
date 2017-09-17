package com.baidu.oauth.service;
import java.util.List;
import java.util.Map;
import com.baidu.oauth.domain.BaiduUser;
import com.baidu.oauth.qvo.BaiduUserQvo;
import com.ybg.base.jdbc.BaseMap;

public interface BaiduUserService {
	
	void create(BaiduUser bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> conditionmap);
	
	List<BaiduUser> query(BaiduUserQvo qvo) throws Exception;
	
	BaiduUser getByUid(Long uid) throws Exception;
	
	Map<String, String> getSetting();
	
	/** @param appid
	 * @param value
	 * @param url */
	void updateSetting(String appid, String value, String url);
	
	String queryBaiduId(String userid);
}
