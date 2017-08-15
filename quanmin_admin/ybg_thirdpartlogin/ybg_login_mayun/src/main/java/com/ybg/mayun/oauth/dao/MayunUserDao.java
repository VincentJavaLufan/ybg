package com.ybg.mayun.oauth.dao;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.mayun.oauth.domain.MayunUserVO;
import com.ybg.mayun.oauth.qvo.MayunUserQuery;

public interface MayunUserDao {
	
	void create(MayunUserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> conditionmap);
	
	List<MayunUserVO> query(MayunUserQuery qvo) throws Exception;
	
	Map<String, String> getSetting();
}
