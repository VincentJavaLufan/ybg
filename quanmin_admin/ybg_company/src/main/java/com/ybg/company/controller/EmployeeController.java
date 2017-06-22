//package com.ybg.company.controller;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.ybg.base.jdbc.BaseMap;
//import com.ybg.base.util.BaseControllor;
//import com.ybg.base.util.Json;
//import com.ybg.base.util.Page;
//import com.ybg.base.util.ServletUtil;
//import com.ybg.company.domain.Employee;
//import com.ybg.company.qvo.EmployeeQvo;
//import com.ybg.company.service.EmployeeService;
//import com.ybg.component.org.inter.Organization;
//import io.swagger.annotations.Api;
//@Api("员工管理")
//@Controller
//@RequestMapping("/org/employee_do/")
//public class EmployeeController extends BaseControllor {
//	
//	@Autowired
//	EmployeeService employeeService;
//	
//	/** 新增初始化 **/
//	@RequestMapping("toadd.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "创建初始化")
//	public String toadd(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		return "/xxpt/add";
//	}
//	
//	/** 更新初始化 **/
//	@RequestMapping("toupdate.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "更新初始化 ")
//	public String toupdate(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//		String id = ServletUtil.getStringParamDefaultBlank(request, "id");
//		Organization employee = employeeService.get(id);
//		map.put("oaEmployee", employee);
//		return "/xxpt/edit";
//	}
//	
//	/** 新增 **/
//	@ResponseBody
//	@RequestMapping("create.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "创建")
//	public Json add(HttpServletRequest request) throws Exception {
//		Json j = new Json();
//		try {
//			Employee employee = getServletOaEmployee(request);
//			employeeService.create(employee);
//			j.setSuccess(true);
//			j.setMsg("新增成功！");
//			j.setObj(employee);
//		} catch (Exception e) {
//			e.printStackTrace();
//			j.setMsg(e.getMessage());
//		}
//		return j;
//	}
//	
//	// 获取信息
//	private Employee getServletOaEmployee(HttpServletRequest request) {
//		return (Employee) getCommandObject(request, Employee.class);
//	}
//	
//	// 获取信息-查询
//	private EmployeeQvo getServletOaEmployeeQvo(HttpServletRequest request) {
//		return (EmployeeQvo) getCommandObject(request, EmployeeQvo.class);
//	}
//	
//	/** 更新 **/
//	@ResponseBody
//	@RequestMapping("update.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "更新")
//	public Json updateoaEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Employee employee = getServletOaEmployee(request);
//		Json j = new Json();
//		j.setSuccess(true);
//		try {
//			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
//			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
//			employeeService.update(updatemap, wheremap);
//		} catch (Exception e) {
//			j.setMsg("操作失败！");
//			j.setObj(employee);
//			return j;
//		}
//		j.setMsg("操作成功！");
//		return j;
//	}
//	
//	/** 列表初始化 **/
//	@RequestMapping("index.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "首页初始化")
//	public String oaEmployeelistindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return "/xxpt/index";
//	}
//	
//	/** 获取列表 **/
//	@ResponseBody
//	@RequestMapping("list.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "分页列表")
//	public Page getOaEmployee_List(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		EmployeeQvo qvo = getServletOaEmployeeQvo(request);
//		Page page = new Page();
//		page.setCurPage(getCurrentPage(request));
//		page = employeeService.query(page, qvo);
//		page.init();
//		return page;
//	}
//	
//	/** 删除 **/
//	@ResponseBody
//	@RequestMapping("remove.do")
//	// @SystemLog(module = "OaEmployee管理", methods = "删除")
//	public Json removeOaEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Json j = new Json();
//		j.setSuccess(true);
//		String[] ids = ServletUtil.getStringParamDefaultBlank(request, "ids").trim().split(",");
//		try {
//			for (String id : ids) {
//				BaseMap<String, Object> condition = new BaseMap<String, Object>();
//				condition.put("id", id);
//				employeeService.remove(condition);
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
