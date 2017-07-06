package com.ybg.region.proxy;

import java.util.List;

import com.ybg.region.domain.RegionVO;
import com.ybg.region.qvo.RegionQuery;
import com.ybg.region.service.RegionService;

import com.ybg.rbac.user.domain.UserVO;

/** 代理查询 **/
public class RegionServiceProxy {

	RegionService regionService;

	public RegionServiceProxy(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * 省级别 根据用户的所在地区 进行过滤
	 * 
	 * @param qvo
	 *            查询
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<RegionVO> listPriVince(RegionQuery qvo, UserVO user)
			throws Exception {

		// TODO 查询用户所在的省份。
		return regionService.list(qvo);
	}

	/**
	 * 城市列表， 根据用户的所在地区 进行过滤
	 * 
	 * @param qvo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<RegionVO> listCity(RegionQuery qvo, UserVO user)
			throws Exception {

		// TODO 查询用户所在的地市。
		return regionService.list(qvo);
	}

	/**
	 * 区县列表 根据用户的所在地区 进行过滤
	 * 
	 * @param qvo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<RegionVO> listAgency(RegionQuery qvo, UserVO user)
			throws Exception {

		// TODO 查询用户所在的的区县逻辑
		return regionService.list(qvo);
	}

}
