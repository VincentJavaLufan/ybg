package com.ybg.region.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybg.region.proxy.RegionServiceProxy;
import com.ybg.region.qvo.RegionQuery;
import com.ybg.region.service.RegionService;
import com.ybg.base.util.Common;
import com.ybg.base.util.Json;
import com.ybg.rbac.user.domain.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("地市三级查询")
@Controller("/edu/region_do/")
public class RegionController {

	@Autowired
	RegionService regionService;

	@ApiOperation(value = "省份列表（全部）", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listProvice.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json listProvince(
			@RequestParam(name = "provinceid", required = false) Integer provinceid)
			throws Exception {
		Json json = new Json();
		RegionQuery qvo = new RegionQuery();
		qvo.setProvinceid(provinceid);
		json.setObj(regionService.list(qvo));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

	@ApiOperation(value = "省份列表 ,隶属个人", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listProviceByUser.do" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public Json listProviceByUser(
			@RequestParam(name = "provinceid", required = false) Integer parentId)
			throws Exception {
		Json json = new Json();
		UserVO user = Common.findUserSession();
		RegionQuery qvo = new RegionQuery();
		qvo.setParentid(parentId);
		RegionServiceProxy regionServiceProxy = new RegionServiceProxy(
				regionService);
		json.setObj(regionServiceProxy.listPriVince(qvo, user));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

	@ApiOperation(value = "城市列表 ,全部", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listCity.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json listCity(
			@RequestParam(name = "provinceid", required = false) Integer provinceid)
			throws Exception {
		Json json = new Json();
		RegionQuery qvo = new RegionQuery();
		qvo.setProvinceid(provinceid);
		json.setObj(regionService.list(qvo));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

	@ApiOperation(value = "城市列表 ,隶属个人", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listCityByUser.do" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public Json listCityByUser(
			@RequestParam(name = "provinceid", required = false) Integer provinceid)
			throws Exception {
		Json json = new Json();
		RegionQuery qvo = new RegionQuery();
		qvo.setProvinceid(provinceid);
		json.setObj(regionService.list(qvo));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

	@ApiOperation(value = "区县列表 ,全部", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listAgency.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json listAgency(
			@RequestParam(name = "districtid", required = false) Integer districtid)
			throws Exception {
		Json json = new Json();
		RegionQuery qvo = new RegionQuery();
		qvo.setDistrictid(districtid);
		json.setObj(regionService.list(qvo));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

	@ApiOperation(value = "区县列表 ,隶属个人", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "listAgencyByUser.do" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public Json listAgencyByUser(
			@RequestParam(name = "districtid", required = false) Integer districtid)
			throws Exception {
		Json json = new Json();
		RegionQuery qvo = new RegionQuery();
		qvo.setDistrictid(districtid);
		json.setObj(regionService.list(qvo));
		json.setSuccess(true);
		json.setMsg("");
		return json;
	}

}
