package com.ybg.mayun.oauth.service;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.mayun.oauth.domain.MayunUserVO;
import com.ybg.mayun.oauth.qvo.MayunUserQuery;

public interface MayunUserService {
	
	void create(MayunUserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> conditionmap);
	
	List<MayunUserVO> query(MayunUserQuery qvo) throws Exception;
	
	Map<String, String> getSetting();
	
	/** 获取token **/
	String getAccessToken(String code);
	
	/** 根据token 获取 userid **/
	Long getMayunUserIdByToken(String access_token);
	
	MayunUserVO getUserByMayunId(String id) throws Exception;
	
}
