package com.ybg.clazz.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.clazz.dao.ClazzDao;

import com.ybg.clazz.domain.ClazzVO;
import com.ybg.clazz.qvo.ClazzQuery;
import java.util.List;

import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class ClazzServiceImpl implements ClazzService {
	@Autowired
	private ClazzDao clazzDao;

	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	public ClazzVO save(ClazzVO bean) throws Exception {
		return clazzDao.save(bean);

	}

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

	@Override
	public void update(BaseMap<String, Object> updatemap,
			BaseMap<String, Object> wheremap) {
		clazzDao.update(updatemap, wheremap);
	}

	/** 分页查询 **/
	@Override
	public Page list(Page page, ClazzQuery qvo) throws Exception {
		return clazzDao.list(page, qvo);
	}

	/** 不分页查询 **/
	@Override
	public List<ClazzVO> list(ClazzQuery qvo) throws Exception {
		return clazzDao.list(qvo);
	}

	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		clazzDao.remove(wheremap);
	}

	public ClazzVO get(String id) {
		return clazzDao.get(id);
	}
}
