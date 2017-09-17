package com.ybg.weixin.login.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.weixin.login.domain.WeixinUserVO;
import com.ybg.weixin.login.qvo.WeixinUserQuery;

public interface WeixinUserDao {
	
	void create(WeixinUserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> wheremap);
	
	List<WeixinUserVO> query(WeixinUserQuery qvo) throws Exception;
	
	String queryWeixinId(String userid);
}
