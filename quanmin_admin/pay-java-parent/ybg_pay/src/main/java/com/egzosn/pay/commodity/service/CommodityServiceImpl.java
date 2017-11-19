package com.egzosn.pay.commodity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egzosn.pay.commodity.dao.CommodityDao;
import com.egzosn.pay.commodity.domain.CommodityVO;
import com.egzosn.pay.commodity.qvo.CommodityQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/**
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
@Repository
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	CommodityDao commodityDao;

	@Override
	public CommodityVO save(CommodityVO bean) throws Exception {

		return commodityDao.save(bean);
	}

	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		commodityDao.update(updatemap, wheremap);
	}

	@Override
	public Page list(Page page, CommodityQuery qvo) throws Exception {

		return commodityDao.list(page, qvo);
	}

	@Override
	public List<CommodityVO> list(CommodityQuery qvo) throws Exception {

		return commodityDao.list(qvo);
	}

	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		commodityDao.remove(wheremap);
	}

	@Override
	public CommodityVO get(String id) {

		return commodityDao.get(id);
	}

}
