package com.ybg.menu.service;
import java.util.List;
import com.ybg.api.domain.WeixinJson;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.domain.WeixinButtonVO;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface WeixinMenuService {
	
	/** c创建
	 * 
	 * @param bean
	 * @throws Exception
	 */
	void create(WeixinButtonVO bean) throws Exception;
	
	/** 更新
	 * 
	 * @param updatemap
	 * @param wheremap
	 */
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 删除
	 * 
	 * @param conditionmap
	 */
	void remove(BaseMap<String, Object> conditionmap);
	
	/** 列表
	 * 
	 * @return */
	List<WeixinButtonVO> list();
	
	/** 获取单个
	 * 
	 * @param id
	 * @return */
	WeixinButtonVO get(String id);
	
	/** 列表
	 * 
	 * @return */
	List<WeixinButtonVO> menulist();
	
	/** 根据父级目录查询
	 * 
	 * @param parentid
	 * @return
	 * @throws Exception
	 */
	List<WeixinButtonVO> buttonlist(String parentid) throws Exception;
	
	/** 生成菜单到微信服务器
	 * 
	 * @param list
	 * @return */
	WeixinJson save(List<WeixinButtonVO> list);
	
	/** 清除微信服务器的菜单
	 * 
	 * @return */
	WeixinJson cleanmenu();
}
