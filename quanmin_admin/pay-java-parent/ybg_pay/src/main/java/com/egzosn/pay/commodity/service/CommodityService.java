package com.egzosn.pay.commodity.service;

import java.util.List;

import com.egzosn.pay.commodity.domain.CommodityVO;
import com.egzosn.pay.commodity.qvo.CommodityQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/**
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public interface CommodityService {
	/**
	 * 创建商品
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	CommodityVO save(CommodityVO bean) throws Exception;

	/**
	 * 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称
	 **/

	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, CommodityQuery qvo) throws Exception;

	/**
	 * 不分页查询
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<CommodityVO> list(CommodityQuery qvo) throws Exception;

	/**
	 * 根据条件删除
	 * 
	 * @param wheremap
	 */
	void remove(BaseMap<String, Object> wheremap);

	/**
	 * 获取单个
	 * 
	 * @param id
	 * @return
	 */
	CommodityVO get(String id);
}
