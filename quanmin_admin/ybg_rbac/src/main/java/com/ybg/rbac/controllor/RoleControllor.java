package com.ybg.rbac.controllor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.base.util.ValidatorUtils;
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
public class RoleControllor {
	
	@Autowired
	RoleService			roleService;
	@Autowired
	ResourcesService	resourcesService;
	
	@ApiOperation(value = "角色管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		// map.addAttribute("res", findByRes(request));
		return "/system/role/index";
	}
	
	@ApiOperation(value = "角色分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "RoleQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute RoleQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow) throws Exception {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = roleService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 角色下拉列表
	 * 
	 * @throws Exception **/
	@ResponseBody
	@RequestMapping(value = { "select.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> select() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("roleselect", roleService.list(new RoleQuery()));
		return map;
	}
	
	@ApiOperation(value = "更新角色", notes = "只更新描述，名称，系统唯一标识,状态 ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.POST })
	public Json update(@RequestBody SysRoleVO role) {
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
			String roleId = role.getId();
			List<SysResourcesVO> reslist = resourcesService.list(new ResourcesQuery());
			List<RoleResDO> list = new ArrayList<RoleResDO>();
			for (SysResourcesVO res : reslist) {
				RoleResDO rr = new RoleResDO();
				rr.setResid(res.getId());
				rr.setRoleid(roleId);
				rr.setState(1);// 禁止使用
				list.add(rr);
			}
			if (role.getMenuIdList() != null && role.getMenuIdList().size() > 0) {
				for (String resid : role.getMenuIdList()) {
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
	
	@ApiOperation(value = "根据ID删除角色", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除角色的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.POST })
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
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.POST })
	public Json create(@RequestBody SysRoleVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		ValidatorUtils.validateEntity(bean);
		try {
			roleService.save(bean);
			String roleId = bean.getId();
			List<SysResourcesVO> reslist = resourcesService.list(new ResourcesQuery());
			List<RoleResDO> list = new ArrayList<RoleResDO>();
			for (SysResourcesVO res : reslist) {
				RoleResDO rr = new RoleResDO();
				rr.setResid(res.getId());
				rr.setRoleid(roleId);
				rr.setState(1);// 禁止使用
				list.add(rr);
			}
			if (bean.getMenuIdList() != null && bean.getMenuIdList().size() > 0) {
				for (String resid : bean.getMenuIdList()) {
					for (RoleResDO rr : list) {
						if (rr.getResid().equals(resid)) {
							rr.setState(0);
						}
					}
				}
			}
			roleService.saveOrupdateRole_Res(list);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			j.setMsg("操作失败,已存在该记录");
			return j;
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "获取单个ROLE", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "角色的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "get.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> get(@RequestParam(name = "id", required = true) String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		SysRoleVO role = roleService.get(id);
		List<SysResourcesVO> menusvo = resourcesService.getRolesByUserId(id);
		List<String> list = new ArrayList<String>();
		for (SysResourcesVO r : menusvo) {
			list.add(r.getId());
		}
		role.setMenuIdList(list);
		result.put("role", role);
		ResponseEntity<Map<String, Object>> bean = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return bean;
	}
}
