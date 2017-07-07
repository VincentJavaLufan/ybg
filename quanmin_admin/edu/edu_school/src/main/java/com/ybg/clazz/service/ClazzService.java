package com.ybg.clazz.service;

import com.ybg.clazz.domain.ClazzVO;
import com.ybg.clazz.qvo.ClazzQuery;

import java.util.List;

import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-07
 */
public interface ClazzService {

	/**
	 * 返回主键的创建
	 * 
	 * @throws Exception
	 **/
	ClazzVO save(ClazzVO bean) throws Exception;

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
	// sys_role
	void update(BaseMap<String, Object> updatemap,
			BaseMap<String, Object> wheremap);

	/** 分页查询 **/
	// sys_role
	Page list(Page page, ClazzQuery qvo) throws Exception;

	/** 不分页查询 **/
	// sys_role
	List<ClazzVO> list(ClazzQuery qvo) throws Exception;

	/** 根据条件删除 **/
	void remove(BaseMap<String, Object> wheremap);

	ClazzVO get(String id);
}
