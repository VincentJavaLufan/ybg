package com.ybg.clazz.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.clazz.domain.ClazzVO;
import com.ybg.clazz.service.ClazzService;
import com.ybg.clazz.domain.ClazzDO;
import com.ybg.clazz.qvo.ClazzQuery;
import springfox.documentation.annotations.ApiIgnore;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-07 */
@Api("班级管理")
@Controller
@RequestMapping("clazz")
public class ClazzController {
	
	@Autowired
	private ClazzService clazzService;
	
	@ApiOperation(value = "Clazz管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map) {
		return "/system/Clazz/index";
	}
	
	@ApiOperation(value = "Clazz分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "ClazzQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute ClazzQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = clazzService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 新增初始化
	 * 
	 * @throws Exception **/
	@ApiOperation(value = "添加Clazz页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) throws Exception {
		return "/system/Clazz/toadd";
	}
	
	@ApiOperation(value = "更新Clazz", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute ClazzVO clazz) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("classname", clazz.getClassname());
			updatemap.put("schoolid", clazz.getSchoolid());
			updatemap.put("gradeid", clazz.getGradeid());
			updatemap.put("gradename", clazz.getGradename());
			updatemap.put("regionid", clazz.getRegionid());
			updatemap.put("schoolname", clazz.getSchoolname());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", clazz.getId());
			clazzService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除clazz", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除clazz", required = true, dataType = "java.lang.String")
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
				clazzService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建clazz", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute ClazzVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			clazzService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建clazz页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd() {
		return "/system/clazz/add";
	}
	
	@ApiOperation(value = "更新clazz页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "clazz的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) throws Exception {
		map.put("clazz", clazzService.get(id));
		return "/system/clazz/edit";
	}
}
