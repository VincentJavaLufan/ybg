package com.ybg.rbac.controllor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Json;
import com.ybg.base.util.ValidatorUtils;
import com.ybg.rbac.resources.domain.SysButtonVO;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;
import com.ybg.rbac.resources.qvo.SysButtonQuery;
import com.ybg.rbac.resources.service.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("用户访问资源管理接口")
@Controller
@RequestMapping("/res/res_do/")
public class RescourcesControllor {
	
	@Autowired
	ResourcesService resourcesService;
	
	@ApiOperation(value = "资源页面", notes = "需要授权才可以访问的页面", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map) {
		return "/system/resources/index";
	}
	
	@ApiOperation(value = "资源页面数据列表", notes = "需要授权才可以访问的页面", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysResourcesVO> list(@ModelAttribute ResourcesQuery qvo) throws Exception {
		return resourcesService.list(qvo);
	}
	
	@ApiOperation(value = "选择列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "select.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> select(@ModelAttribute ResourcesQuery qvo) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("menuList", resourcesService.list(qvo));
		ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return map;
	}
	
	@ApiOperation(value = "创建授权资源", notes = "需要授权才可以访问的页面", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody SysResourcesVO res) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			if (res.getType().equals("0")) {
				res.setReskey(res.getName());
				res.setParentid("0");
				res.setResurl(res.getName());
			}
			else {
				res.setReskey(res.getResurl());
			}
			ValidatorUtils.validateEntity(res);
			resourcesService.save(res);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "更新授权资源", notes = "需要授权才可以访问的页面", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody SysResourcesVO menu) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			if (menu.getType().equals("0")) {
				menu.setReskey(menu.getName());
				menu.setParentid("0");
				menu.setResurl(menu.getName());
			}
			else {
				menu.setReskey(menu.getResurl());
			}
			ValidatorUtils.validateEntity(menu);
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			updatemap.put("description", menu.getDescription());
			updatemap.put("icon", menu.getIcon());
			updatemap.put("ishide", menu.getIshide());
			updatemap.put("level", menu.getLevel());
			updatemap.put("name", menu.getName());
			updatemap.put("parentid", menu.getParentid());
			updatemap.put("reskey", menu.getReskey());
			updatemap.put("resurl", menu.getResurl());
			updatemap.put("type", menu.getType());
			updatemap.put("colorid", menu.getColorid());
			wheremap.put("id", menu.getId());
			resourcesService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "删除授权资源", notes = "逻辑删除", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "删除资源的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "id", required = true) String id) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			// String ids[] = ids2.split(",");
			// for (String id : ids) {
			resourcesService.removebyid(id);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	/** 树 **/
	@ApiOperation(value = "授权资源树结构", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "reslists.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> reslists(@ModelAttribute ResourcesQuery qvo) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<SysResourcesVO> list = resourcesService.list(qvo);
		maps.put("menuList", list);
		return maps;
	}
	
	@ApiOperation(value = "授权资源 按钮列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "findByButtom.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysButtonVO> findByButtom() {
		return resourcesService.querybutton(new SysButtonQuery());
	}
	
	@ApiOperation(value = "加载角色所属授权资源", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "roleid", value = "角色ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "findRes.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysResourcesVO> findUserRes(@RequestParam(name = "roleid", required = true) String roleid) throws Exception {
		return resourcesService.getRolesByUserId(roleid);
	}
	
	@ApiOperation(value = "获取单个授权资源", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id", value = "授权资源ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "get.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> get(@RequestParam(name = "id", required = true) String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		SysResourcesVO vo = resourcesService.get(id);
		result.put("menu", vo);
		ResponseEntity<Map<String, Object>> bean = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return bean;
	}
}
