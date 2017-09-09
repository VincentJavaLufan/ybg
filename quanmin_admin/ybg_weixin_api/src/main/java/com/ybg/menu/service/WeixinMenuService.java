package com.ybg.menu.service;
import java.util.List;
import com.ybg.api.domain.WeixinJson;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.domain.WeixinButtonVO;

public interface WeixinMenuService {
	
	void create(WeixinButtonVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> conditionmap);
	
	List<WeixinButtonVO> list();
	
	WeixinButtonVO get(String id);
	
	List<WeixinButtonVO> menulist();
	
	List<WeixinButtonVO> buttonlist(String parentid) throws Exception;
	
	/**生成菜单到微信服务器**/
	WeixinJson save(List<WeixinButtonVO> list);
}
