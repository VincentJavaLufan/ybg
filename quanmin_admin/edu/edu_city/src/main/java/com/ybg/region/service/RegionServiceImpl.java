package com.ybg.region.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybg.region.dao.RegionDao;

import com.ybg.region.domain.RegionVO;
import com.ybg.region.qvo.RegionQuery;

import java.util.List;

import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;

	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	public RegionVO save(RegionVO bean) throws Exception {
		return regionDao.save(bean);

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
		regionDao.update(updatemap, wheremap);
	}

	/**
	 * 分页查询
	 * 
	 * @throws Exception
	 **/
	@Override
	public Page list(Page page, RegionQuery qvo) throws Exception {
		return regionDao.list(page, qvo);
	}

	/**
	 * 不分页查询
	 * 
	 * @throws Exception
	 **/
	@Override
	public List<RegionVO> list(RegionQuery qvo) throws Exception {
		return regionDao.list(qvo);
	}

	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		regionDao.remove(wheremap);
	}

}
