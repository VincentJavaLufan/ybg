package com.ybg.school.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybg.school.dao.SchoolDao;
import com.ybg.school.domain.SchoolVO;
import com.ybg.school.qvo.SchoolQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolDao schoolDao;

	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	public SchoolVO save(SchoolVO bean) throws Exception {
		return schoolDao.save(bean);

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
		schoolDao.update(updatemap, wheremap);
	}

	/** 分页查询 **/
	@Override
	public Page list(Page page, SchoolQuery qvo) throws Exception {
		return schoolDao.list(page, qvo);
	}

	/** 不分页查询 **/
	@Override
	public List<SchoolVO> list(SchoolQuery qvo) throws Exception {
		return schoolDao.list(qvo);
	}

	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		schoolDao.remove(wheremap);
	}

	public SchoolVO get(String id) {
		return schoolDao.get(id);
	}
}
