//package com.ybg.company.controller;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.ybg.base.jdbc.BaseMap;
//
//import com.ybg.base.util.Json;
//import com.ybg.base.util.Page;
//import com.ybg.base.util.ServletUtil;
////import com.ybg.base.util.SystemLog;
//import com.ybg.company.domain.Department;
//import com.ybg.company.qvo.DepartmentQvo;
//import com.ybg.company.service.DepartmentService;
//import com.ybg.component.org.inter.Organization;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Api("企业-部门管理")
//@Controller
//@RequestMapping(value = { "/org/department_do/" })
//public class DepartmentController  {
//	
//	@Autowired
//	DepartmentService departmentService;
//	
//	/** 新增初始化 **/
//	@ApiOperation(value = "新增部门页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
//	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "创建初始化")
//	public String toadd(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		return "/xxpt/add";
//	}
//	
//	/** 更新初始化 **/
//	@ApiOperation(value = "更新部门页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
//	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "更新初始化 ")
//	public String toupdate(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		String id = ServletUtil.getStringParamDefaultBlank(request, "id");
//		Organization oaDepartment = departmentService.get(id);
//		map.put("oaDepartment", oaDepartment);
//		return "/xxpt/edit";
//	}
//	
//	/** 新增 **/
//	@ApiOperation(value = "新增部门", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "创建")
//	public Json add(HttpServletRequest request) throws Exception {
//		Json j = new Json();
//		try {
//			Department oaDepartment = getServletOaDepartment(request);
//			departmentService.create(oaDepartment);
//			j.setSuccess(true);
//			j.setMsg("新增成功！");
//			j.setObj(oaDepartment);
//		} catch (Exception e) {
//			e.printStackTrace();
//			j.setMsg(e.getMessage());
//		}
//		return j;
//	}
//	
//	// 获取信息
//	private Department getServletOaDepartment(HttpServletRequest request) {
//		return (Department) getCommandObject(request, Department.class);
//	}
//	
//	// 获取信息-查询
//	private DepartmentQvo getServletOaDepartmentQvo(HttpServletRequest request) {
//		return (DepartmentQvo) getCommandObject(request, DepartmentQvo.class);
//	}
//	
//	/** 更新 **/
//	@ApiOperation(value = "更新部门页面", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "更新")
//	public Json updateoaDepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Department oaDepartment = getServletOaDepartment(request);
//		Json j = new Json();
//		j.setSuccess(true);
//		try {
//			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
//			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
//			departmentService.update(updatemap, wheremap);
//		} catch (Exception e) {
//			j.setMsg("操作失败！");
//			j.setObj(oaDepartment);
//			return j;
//		}
//		j.setMsg("操作成功！");
//		return j;
//	}
//	
//	/** 列表初始化 **/
//	@ApiOperation(value = "更新部门页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
//	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "首页初始化")
//	public String oaDepartmentlistindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return "/xxpt/index";
//	}
//	
//	/** 获取列表 **/
//	@ApiOperation(value = "部门列表页面", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "分页列表")
//	public Page getOaDepartment_List(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DepartmentQvo qvo = getServletOaDepartmentQvo(request);
//		departmentService.query(qvo);
//		return null;
//	}
//	
//	/** 删除 **/
//	@ApiOperation(value = "删除部门", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
//	// @SystemLog(module = "OaDepartment管理", methods = "删除")
//	public Json removeOaDepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Json j = new Json();
//		j.setSuccess(true);
//		String[] ids = ServletUtil.getStringParamDefaultBlank(request, "ids").trim().split(",");
//		try {
//			for (String id : ids) {
//				BaseMap<String, Object> condition = new BaseMap<String, Object>();
//				condition.put("`id`", id);
//				departmentService.remove(condition);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			j.setMsg("操作失败");
//			return j;
//		}
//		j.setMsg("操作成功");
//		return j;
//	}
//}
