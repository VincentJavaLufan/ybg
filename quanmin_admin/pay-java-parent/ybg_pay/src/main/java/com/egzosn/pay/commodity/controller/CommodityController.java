package com.egzosn.pay.commodity.controller;
import java.util.HashMap;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.egzosn.pay.commodity.domain.CommodityVO;
import com.egzosn.pay.commodity.qvo.CommodityQuery;
import com.egzosn.pay.commodity.service.CommodityService;
import com.ybg.base.jdbc.BaseMap;
import org.springframework.web.bind.annotation.RequestBody;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.base.util.ValidatorUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/** @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0 */
@Api("商品管理")
@Controller
@RequestMapping("/pay/commodity/")
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	
	@ApiOperation(value = "商品管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/pay/commodity/index";
	}
	
	@ApiOperation(value = "商品分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "CommodityQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute CommodityQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
		Page page = new Page();
		page.setCurPage(pageNow);
		page = commodityService.list(page, qvo);
		page.init();
		return page;
	}
	
	@ApiOperation(value = "更新商品", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody CommodityVO commodity) {
		Json j = new Json();
		j.setSuccess(true);
		ValidatorUtils.validateEntity(commodity);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("name", commodity.getName());
			updatemap.put("type", commodity.getType());
			updatemap.put("price", commodity.getPrice());
			updatemap.put("picture", commodity.getPicture());
			updatemap.put("description", commodity.getDescription());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", commodity.getId());
			commodityService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除商品", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除commodity", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
				wheremap.put("id", id);
				commodityService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建商品", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody CommodityVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		ValidatorUtils.validateEntity(bean);
		try {
			commodityService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "获取单个商品", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "java.lang.String") })
	@RequestMapping(value = { "get.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> get(@RequestParam(name = "id", required = true) String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(1);
		CommodityVO bean = commodityService.get(id);
		result.put("commodity", bean);
		ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return map;
	}
}
