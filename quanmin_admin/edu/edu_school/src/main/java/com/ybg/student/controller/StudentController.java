package com.ybg.student.controller;

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
import com.ybg.student.domain.StudentVO;
import com.ybg.student.service.StudentService;
import com.ybg.student.domain.StudentDO;
import com.ybg.student.qvo.StudentQuery;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * 
 * @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-07
 */
@Api("学生管理")
@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "Student管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(ModelMap map) {
		return "/system/Student/index";
	}

	@ApiOperation(value = "Student分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "StudentQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Page list(
			@ModelAttribute StudentQuery qvo,
			@RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow,
			ModelMap map) throws Exception {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = studentService.list(page, qvo);
		page.init();
		return page;
	}

	/**
	 * 新增初始化
	 * 
	 * @throws Exception
	 **/
	@ApiOperation(value = "添加Student页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) throws Exception {

		return "/system/Student/toadd";
	}

	@ApiOperation(value = "更新Student", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json update(@ModelAttribute StudentVO student) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("studentname", student.getStudentname());
			updatemap.put("studentno", student.getStudentno());
			updatemap.put("classid", student.getClassid());
			updatemap.put("gradeid", student.getGradeid());
			updatemap.put("schoolid", student.getSchoolid());
			updatemap.put("gradename", student.getGradename());
			updatemap.put("classname", student.getClassname());
			updatemap.put("schoolname", student.getSchoolname());
			updatemap.put("identitycard", student.getIdentitycard());
			updatemap.put("headurl", student.getHeadurl());
			updatemap.put("birthday", student.getBirthday());
			updatemap.put("gmt_create", student.getGmtCreate());
			updatemap.put("gmt_modified", student.getGmtModified());
			updatemap.put("studentorign", student.getStudentorign());
			updatemap.put("studentaddress", student.getStudentaddress());
			updatemap.put("parentname", student.getParentname());
			updatemap.put("gender", student.getGender());
			updatemap.put("cityid", student.getCityid());
			updatemap.put("regionid", student.getRegionid());
			updatemap.put("districtid", student.getDistrictid());
			updatemap.put("provinceid", student.getProvinceid());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", student.getId());
			studentService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}

	@ApiOperation(value = "根据ID删除student", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除student", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
				wheremap.put("id", id);
				studentService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}

	@ApiOperation(value = "创建student", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public Json create(@ModelAttribute StudentVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			studentService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}

	@ApiOperation(value = "创建student页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toadd() {
		return "/system/student/add";
	}

	@ApiOperation(value = "更新student页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "student的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toupdate(
			@RequestParam(name = "id", required = true) String id, ModelMap map)
			throws Exception {
		map.put("student", studentService.get(id));
		return "/system/student/edit";
	}

}
