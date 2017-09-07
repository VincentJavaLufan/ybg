package com.ybg.menu.dao;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.domain.WeixinButtonDO;

public interface WeixinButtonDao {
	
	void create(WeixinButtonDO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> conditionmap);
	
	List<WeixinButtonDO> list();
	
	WeixinButtonDO get(String id);
}
