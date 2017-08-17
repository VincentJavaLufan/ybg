package com.ybg.oa.employee.controller;

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
import com.ybg.oa.employee.domain.EmployeeVO;
import com.ybg.oa.employee.service.EmployeeService;
import com.ybg.oa.employee.domain.EmployeeDO;
import com.ybg.oa.employee.qvo.EmployeeQuery;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 部门和用户之间绑定
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-15
 */
 @Api(tags="Employee管理")
@Controller
@RequestMapping("/oa/employee_do/")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
		
	@ApiOperation(value = "Employee管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index( ModelMap map) {		
		return "/system/employee/index";
	}
	
	@ApiOperation(value = "Employee分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "EmployeeQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute EmployeeQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = employeeService.list(page, qvo);
		page.init();
		return page;
	}
	
	
	/** 新增初始化 
	 * @throws Exception **/
	@ApiOperation(value = "添加Employee页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) throws Exception {
		
		return "/system/employee/toadd";
	}
	
	@ApiOperation(value = "更新Employee", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute EmployeeVO employee) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
													  		updatemap.put("name", employee.getName());
										  		updatemap.put("companyid", employee.getCompanyid());
										  		updatemap.put("deptid", employee.getDeptid());
										  		updatemap.put("userid", employee.getUserid());
										  		updatemap.put("manager", employee.getManager());
										  		updatemap.put("companyname", employee.getCompanyname());
									BaseMap<String, Object> wheremap = new BaseMap<String, Object>();			
			wheremap.put("id", employee.getId());
			employeeService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除employee", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除employee", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				BaseMap<String, Object> wheremap= new BaseMap<String, Object>();
				wheremap.put("id", id);
				employeeService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建employee", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute EmployeeVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			employeeService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	
	
	@ApiOperation(value = "更新employee页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "employee的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) throws Exception {
		map.put("employee", employeeService.get(id));
		return "/system/employee/edit";
	}
	
	
	
	
}
