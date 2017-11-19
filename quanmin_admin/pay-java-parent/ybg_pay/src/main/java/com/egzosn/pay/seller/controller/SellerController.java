package com.egzosn.pay.seller.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egzosn.pay.seller.domain.SellerVO;
import com.egzosn.pay.seller.qvo.SellerQuery;
import com.egzosn.pay.seller.service.SellerService;
import com.ybg.base.jdbc.BaseMap;

import com.ybg.base.util.Json;
import com.ybg.base.util.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 商户管理
 * 
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
@Api(tags = "商户管理")
@Controller
@RequestMapping("/pay/seller/")
public class SellerController {

	@Autowired
	SellerService sellerService;

	@RequestMapping("index.do")
	public String index() {
		return "/pay/seller/index";
	}
@ResponseBody
	@RequestMapping("list.do")
	public Page list(@ModelAttribute SellerQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow) throws Exception {
		Page page = new Page();
		page.setCurPage(pageNow);
		page = sellerService.list(page, qvo);

		page.init();
		return page;
	}
@ResponseBody
	@RequestMapping("create.do")
	public Json create(@RequestBody SellerVO seller) throws Exception {
		Json json = new Json();
		sellerService.create(seller);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}

	@RequestMapping("update.do")
	public Json update(@RequestBody SellerVO seller) {
		Json json = new Json();
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		updatemap.put("partner", seller.getPartner());
		updatemap.put("appid", seller.getAppid());
		updatemap.put("publickey", seller.getPublickey());
		updatemap.put("privatekey", seller.getPrivatekey());
		updatemap.put("notifyurl", seller.getNotifyurl());
		updatemap.put("returnurl", seller.getReturnurl());
		updatemap.put("seller", seller.getSeller());
		updatemap.put("signtype", seller.getSigntype());
		updatemap.put("inputcharset", seller.getInputcharset());
		updatemap.put("paytype", seller.getPaytype());
		updatemap.put("msgtype", seller.getMsgtype());
		updatemap.put("test", seller.getTest());

		BaseMap<String, Object> whereMap = new BaseMap<String, Object>();
		whereMap.put("payid", seller.getPayid());

		sellerService.update(updatemap, whereMap);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	@ResponseBody
	@RequestMapping("reflush.do")
	public Json reflush() {
		Json json = new Json();
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}

	@ApiOperation(value = "根据ID删除payAccount", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除payAccount", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
				wheremap.put("payid", id);
				sellerService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	@ResponseBody
	@ApiOperation(value = "获取单个PayAccount", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "java.lang.String") })
	@RequestMapping(value = { "get.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> get(@RequestParam(name = "id", required = true) String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		SellerVO bean = sellerService.get(id);
		result.put("seller", bean);
		ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return map;
	}
}
