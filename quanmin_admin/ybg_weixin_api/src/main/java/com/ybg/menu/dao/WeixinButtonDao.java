package com.ybg.menu.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.menu.domain.WeixinButtonVO;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public interface WeixinButtonDao {
	
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
}
