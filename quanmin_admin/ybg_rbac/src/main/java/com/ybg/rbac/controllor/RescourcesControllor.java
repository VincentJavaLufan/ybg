package com.ybg.rbac.controllor;
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
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Json;
import com.ybg.base.util.TreeObject;
import com.ybg.base.util.TreeUtil;
import com.ybg.rbac.resources.domain.SysButtonVO;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQvo;
import com.ybg.rbac.resources.qvo.SysButtonQvo;
import com.ybg.rbac.resources.qvo.SysColorQvo;
import com.ybg.rbac.resources.qvo.SysMenuIconQvo;
import com.ybg.rbac.resources.service.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api("用户访问资源管理接口")
@Controller
@RequestMapping("/res/res_do/")
public class RescourcesControllor {
	
	@Autowired
	ResourcesService	resourcesService;
	
	@ApiOperation(value = "资源页面", notes = "需要授权才可以访问的页面", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		// map.addAttribute("res", findByRes(request));
		return "/system/resources/index";
	}
	
	@ApiOperation(value = "资源页面数据列表", notes = "需要授权才可以访问的页面", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@ModelAttribute ResourcesQvo qvo, ModelMap map) {
		List<TreeObject> list = new ArrayList<TreeObject>();
		List<SysResourcesVO> dblist = resourcesService.query(qvo);
		for (SysResourcesVO r : dblist) {
			TreeObject ts = new TreeObject();
			ts.setDescription(r.getDescription());
			ts.setIcon(r.getIcon());
			ts.setId(r.getId());
			ts.setIshide(r.getIshide());
			ts.setLevel(r.getLevel());
			ts.setName(r.getName());
			ts.setParentId(r.getParentid());
			ts.setResKey(r.getReskey());
			ts.setResUrl(r.getResurl());
			ts.setType(r.getType());
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, "0");
		map.put("page", ns);
		return "/system/resources/list";
	}
	
	@ApiOperation(value = "创建授权资源", notes = "需要授权才可以访问的页面", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute SysResourcesVO res) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			resourcesService.create(res);
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
	public Json update(@ModelAttribute SysResourcesVO res) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			updatemap.put("description", res.getDescription());
			updatemap.put("icon", res.getIcon());
			updatemap.put("ishide", res.getIshide());
			updatemap.put("level", res.getLevel());
			updatemap.put("name", res.getName());
			updatemap.put("parentid", res.getParentid());
			updatemap.put("reskey", res.getReskey());
			updatemap.put("resurl", res.getResurl());
			updatemap.put("type", res.getType());
			updatemap.put("colorid", res.getColorid());
			wheremap.put("id", res.getId());
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
	@ApiImplicitParam(name = "ids", value = "删除资源的ID，用逗号分割", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String ids[] = ids2.split(",");
			for (String id : ids) {
				resourcesService.removebyid(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "添加授权资源页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(ModelMap map) {
		SysMenuIconQvo qvo = new SysMenuIconQvo();
		map.put("iconlist", resourcesService.queryicon(qvo));
		SysColorQvo colorqvo = new SysColorQvo();
		map.put("colorlist", resourcesService.querycolor(colorqvo));
		return "/system/resources/add";
	}
	
	@ApiOperation(value = "更新授权资源页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) {
		map.addAttribute("resources", resourcesService.get(id));
		SysMenuIconQvo qvo = new SysMenuIconQvo();
		map.put("iconlist", resourcesService.queryicon(qvo));
		SysColorQvo colorqvo = new SysColorQvo();
		map.put("colorlist", resourcesService.querycolor(colorqvo));
		return "/system/resources/edit";
	}
	
	/** 树 **/
	@ApiOperation(value = "授权资源树结构", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "reslists.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysResourcesVO> reslists(@ModelAttribute ResourcesQvo qvo) throws Exception {
		List<SysResourcesVO> mps = resourcesService.query(qvo);
		return mps;
	}
	
	@ApiOperation(value = "授权资源 按钮列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "findByButtom.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysButtonVO> findByButtom() {
		return resourcesService.querybutton(new SysButtonQvo());
	}
	
	@ApiOperation(value = "检测授权资源是否存在", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "reskey", value = "资源key", required = true, dataType = "java.lang.String"), @ApiImplicitParam(name = "name", value = "资源名称", required = true, dataType = "java.lang.String") })
	@ResponseBody
	@RequestMapping(value = { "exist.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public boolean exist(@RequestParam(name = "reskey", required = false) String reskey, @RequestParam(name = "name", required = false) String name) {
		if (!QvoConditionUtil.checkString(reskey) && !QvoConditionUtil.checkString(name)) {
			return false;
		}
		ResourcesQvo qvo = new ResourcesQvo();
		qvo.setReskey(reskey);
		qvo.setName(name);
		return resourcesService.query(qvo).size() > 0 ? false : true;
	}
	
	@ApiOperation(value = "权限分配页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleid", value = "角色ID", required = true, dataType = "java.lang.String") })
	@RequestMapping(value = { "permissions.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String permissions(@ModelAttribute ResourcesQvo qvo, @RequestParam(name = "roleid", required = true) String roleid, ModelMap modelmap) {
		List<SysResourcesVO> mps = resourcesService.query(qvo);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (SysResourcesVO map : mps) {
			TreeObject ts = new TreeObject();
			ts.setDescription(map.getDescription());
			ts.setIcon(map.getIcon());
			ts.setId(map.getId());
			ts.setIshide(map.getIshide());
			ts.setLevel(map.getLevel());
			ts.setName(map.getName());
			ts.setParentId(map.getParentid());
			ts.setResKey(map.getReskey());
			ts.setResUrl(map.getResurl());
			ts.setType(map.getType());
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, "0");
		modelmap.addAttribute("permissions", ns);
		modelmap.put("roleid", roleid);
		return "/system/resources/permissions";
	}
	
	@ApiOperation(value = "加载角色所属菜单", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "roleid", value = "角色ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "findRes.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysResourcesVO> findUserRes(@RequestParam(name = "roleid", required = true) String roleid) {
		return resourcesService.getRolesByUserId(roleid);
	}
	
	@ApiOperation(value = "批量新增初始化 ", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "batchindex.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String batchindex(ModelMap map) {
		return "/system/resources/batchadd";
	}
}
