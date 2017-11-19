package com.egzosn.pay.seller.dao;

import java.util.List;

import com.egzosn.pay.seller.domain.SellerVO;
import com.egzosn.pay.seller.qvo.SellerQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/**
 * @author https://gitee.com/YYDeament/88ybg
 * @date 2017年11月18日 16:06:34
 * @version v1.0
 */
public interface SellerDao {

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	SellerVO get(String id);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, SellerQuery qvo) throws Exception;

	/**
	 * 查询
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<SellerVO> list(SellerQuery qvo) throws Exception;

	/**
	 * 更新
	 * 
	 * @param updateMap
	 * @param whereMap
	 */
	void update(BaseMap<String, Object> updateMap, BaseMap<String, Object> whereMap);

	/**
	 * 删除
	 * 
	 * @param conditionmap
	 */
	void remove(BaseMap<String, Object> conditionmap);

	/**
	 * 创建（返回ID）
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	SellerVO create(SellerVO bean) throws Exception;

}
