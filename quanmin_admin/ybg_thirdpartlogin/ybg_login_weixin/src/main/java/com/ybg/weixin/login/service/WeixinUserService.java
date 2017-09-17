package com.ybg.weixin.login.service;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.weixin.login.domain.WeixinUserVO;
import com.ybg.weixin.login.qvo.WeixinUserQuery;

public interface WeixinUserService {
	
	void create(WeixinUserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> wheremap);
	
	List<WeixinUserVO> query(WeixinUserQuery qvo) throws Exception;
	
	WeixinUserVO getByopenId(String openID) throws Exception;
	
	/** 获取微信ID **/
	String getOpen_id(String code);
	
	String queryWeixinId(String userid);
}
