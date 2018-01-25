package com.ybg.oa.department.controller;
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
import com.ybg.oa.department.domain.DepartmentVO;
import com.ybg.oa.department.service.DepartmentService;
import com.ybg.oa.department.domain.DepartmentDO;
import com.ybg.oa.department.qvo.DepartmentQuery;
import springfox.documentation.annotations.ApiIgnore;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-15 */
@Api(tags="Department管理")
@Controller
@RequestMapping("/oa/department_do/")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@ApiOperation(value = "Department管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map) {
		return "/system/department/index";
	}
	
	@ApiOperation(value = "Department分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "DepartmentQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute DepartmentQuery qvo, @ModelAttribute Page page, ModelMap map) throws Exception {
		qvo.setBlurred(true);
//		Page page = new Page();
//		page.setCurPage(pageNow);
		page = departmentService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 新增初始化
	 * 
	 * @throws Exception **/
	@ApiOperation(value = "添加Department页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) throws Exception {
		return "/system/department/toadd";
	}
	
	@ApiOperation(value = "更新Department", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute DepartmentVO department) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("name", department.getName());
			updatemap.put("companyid", department.getCompanyid());
			updatemap.put("gmt_create", department.getGmtCreate());
			updatemap.put("parentid", department.getParentid());
			updatemap.put("gmt_modified", department.getGmtModified());
			updatemap.put("companyname", department.getCompanyname());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", department.getId());
			departmentService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除department", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除department", required = true, dataType = "java.lang.String")
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
				departmentService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建department", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute DepartmentVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			departmentService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "更新department页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "department的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) throws Exception {
		map.put("department", departmentService.get(id));
		return "/system/department/edit";
	}
}
