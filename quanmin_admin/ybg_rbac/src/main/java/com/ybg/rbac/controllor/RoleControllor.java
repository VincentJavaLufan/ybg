package com.ybg.rbac.controllor;
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
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;
import com.ybg.rbac.resources.service.ResourcesService;
import com.ybg.rbac.role.domain.RoleResDO;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.role.qvo.RoleQuery;
import com.ybg.rbac.role.service.RoleService;

@Api("角色管理")
@Controller
@RequestMapping("/role/role_do/")
public class RoleControllor  {
	
	@Autowired
	RoleService	roleService;
	@Autowired
	ResourcesService resourcesService;
	
	@ApiOperation(value = "角色管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//map.addAttribute("res", findByRes(request));
		return "/system/role/index";
	}
	
	@ApiOperation(value = "角色分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "RoleQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute RoleQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = roleService.list(page, qvo);
		page.init();
		return page;
	}
	
	@ApiOperation(value = "更新角色", notes = "只更新描述，名称，系统唯一标识,状态 ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute SysRoleVO role) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("description", role.getDescription());
			updatemap.put("name", role.getName());
			updatemap.put("rolekey", role.getRolekey());
			updatemap.put("state", role.getState());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", role.getId());
			roleService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除角色", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除角色的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				roleService.removebyid(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建角色", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute SysRoleVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			roleService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建角色页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd() {
		return "/system/role/add";
	}
	
	@ApiOperation(value = "更新角色页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "角色的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) {
		map.put("role", roleService.get(id));
		return "/system/role/edit";
	}
	
	/** 修改权限 **/
	@ApiOperation(value = "更新角色页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "角色的ID", required = true, dataType = "java.lang.String"), @ApiImplicitParam(name = "resId[]", value = "资源ID,是个数组", required = true, dataType = "java.lang.String[]") })
	@ResponseBody
	@RequestMapping(value = { "addRoleRes.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json addRoleRes(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "resId[]", required = false) String[] resIds, ModelMap modelmap) {
		Json j = new Json();
		j.setSuccess(true);
		List<SysResourcesVO> reslist = resourcesService.list(new ResourcesQuery());
		try {
			List<RoleResDO> list = new ArrayList<RoleResDO>();
			for (SysResourcesVO res : reslist) {
				RoleResDO rr = new RoleResDO();
				rr.setResid(res.getId());
				rr.setRoleid(roleId);
				rr.setState(1);// 禁止使用
				list.add(rr);
			}
			if (resIds != null && resIds.length > 0) {
				for (String resid : resIds) {
					for (RoleResDO rr : list) {
						if (rr.getResid().equals(resid)) {
							rr.setState(0);
						}
					}
				}
			}
			roleService.saveOrupdateRole_Res(list);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
}
