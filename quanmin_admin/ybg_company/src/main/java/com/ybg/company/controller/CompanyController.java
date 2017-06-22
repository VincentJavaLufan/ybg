package com.ybg.company.controller;
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
import com.ybg.company.domain.Company;
import com.ybg.company.qvo.CompanyQvo;
import com.ybg.company.service.CompanyService;
import com.ybg.component.org.inter.Organization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("企业管理API")
@Controller
@RequestMapping("/org/compamy_do/")
public class CompanyController  {
	
	@Autowired
	CompanyService companyService;
	
	@ApiOperation(value = "企业管理首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	/** 列表初始化 **/
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String oaEmployeelistindex() throws Exception {
		return "/xxpt/index";
	}
	
	/** 新增初始化 **/
	@ApiOperation(value = "新增企业页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(ModelMap map) {
		return "/xxpt/add";
	}
	
	/** 更新初始化 **/
	@ApiOperation(value = "更新企业页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "企业ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true, defaultValue = "0") String id, ModelMap map) {
		Organization company = companyService.get(id);
		map.put("company", company);
		return "/xxpt/edit";
	}
	
	/** 新增 **/
	@ApiOperation(value = "创建企业", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json add(@ModelAttribute Company oaEmployee) throws Exception {
		Json j = new Json();
		try {
			companyService.create(oaEmployee);
			j.setSuccess(true);
			j.setMsg("新增成功！");
			j.setObj(oaEmployee);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/** 更新 **/
	@ApiOperation(value = "更新企业", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json updateoaEmployee(@ModelAttribute Company oaEmployee) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			companyService.update(updatemap, wheremap);
		} catch (Exception e) {
			j.setMsg("操作失败！");
			j.setObj(oaEmployee);
			return j;
		}
		j.setMsg("操作成功！");
		return j;
	}
	
	/** 获取列表 **/
	@ApiOperation(value = "企业列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page getOaEmployee_List(@ModelAttribute CompanyQvo qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow) throws Exception {
		Page page = new Page();
		page.setCurPage(pageNow);
		page = companyService.query(page, qvo);
		page.init();
		return page;
	}
	
	/** 删除 **/
	@ApiOperation(value = "删除企业", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除企业的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json removeOaEmployee(@RequestParam(name = "ids", required = true) String ids2) throws Exception {
		Json j = new Json();
		j.setSuccess(true);
		String[] ids = ids2.trim().split(",");
		try {
			for (String id : ids) {
				BaseMap<String, Object> condition = new BaseMap<String, Object>();
				condition.put("id", id);
				companyService.remove(condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	/** 企业 认证 接口 **/
}
