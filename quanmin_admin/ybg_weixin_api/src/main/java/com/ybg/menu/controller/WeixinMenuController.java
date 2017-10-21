package com.ybg.menu.controller;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.api.domain.WeixinJson;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Json;
import com.ybg.menu.WeixinButtonConstant;
import com.ybg.menu.domain.WeixinButtonVO;
import com.ybg.menu.service.WeixinMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Api(tags = "微信菜单管理")
@RequestMapping("/weixin/menu_do/")
@Controller
public class WeixinMenuController {
	
	@Autowired
	WeixinMenuService weixinMenuService;
	
	@ApiOperation(value = "用户页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/weixin/menu";
	}
	
	// 这个要判断很多东西，
	@ApiOperation(value = "创建菜单", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody WeixinButtonVO weixinmenu) throws Exception {
		Json j = new Json();
		if (!QvoConditionUtil.checkString(weixinmenu.getName())) {
			j.setSuccess(false);
			j.setMsg("名称不能为空");
			return j;
		}
		if (weixinmenu.getIfsub() == WeixinButtonConstant.IFSUBYES) {
			// 如果是按钮类型
			if (weixinmenu.getButtonorder() > WeixinButtonConstant.MAXBUTTON || weixinmenu.getButtonorder() < 1) {
				j.setSuccess(false);
				j.setMsg("按钮序号超出范围");
				return j;
			}
			WeixinButtonVO parent = weixinMenuService.get(weixinmenu.getParentid());
			if (parent == null) {
				weixinmenu.setParentid(null);
			}
			else {
				if (parent.getIfsub() == WeixinButtonConstant.IFSUBYES) {
					j.setSuccess(false);
					j.setMsg("操作失败。该父级ID 不能作为上一级");
					return j;
				}
				weixinmenu.setMenuorder(parent.getMenuorder());
			}
			if (QvoConditionUtil.checkString(weixinmenu.getParentid())) {
				List<WeixinButtonVO> sublist = weixinMenuService.buttonlist(weixinmenu.getParentid());
				if (sublist != null && sublist.size() >= WeixinButtonConstant.MAXBUTTON) {
					j.setSuccess(false);
					j.setMsg("操作失败。个数超标");
					return j;
				}
			}
			else {
				List<WeixinButtonVO> menulist = weixinMenuService.menulist();
				if (menulist != null && menulist.size() >= WeixinButtonConstant.MAXMENU) {
					j.setSuccess(false);
					j.setMsg("超出菜单数量最大限制");
					return j;
				}
			}
			// 查询 子菜单个数
			switch (weixinmenu.getType()) {
			case WeixinButtonConstant.TYPE_VIEW:
				if (QvoConditionUtil.checkString(weixinmenu.getUrl())) {
					try {
						weixinMenuService.create(weixinmenu);
					} catch (Exception e) {
						e.printStackTrace();
					}
					j.setSuccess(true);
					j.setMsg("操作成功");
					return j;
				}
				else {
					j.setSuccess(false);
					j.setMsg("操作失败，请填写菜单按钮");
					return j;
				}
			default:
				j.setSuccess(false);
				j.setMsg("尚未开发");
				return j;
			}
		}
		else {// 菜单类型
			if (weixinmenu.getMenuorder() > WeixinButtonConstant.MAXMENU || weixinmenu.getMenuorder() < 1) {
				j.setSuccess(false);
				j.setMsg("菜单序号超出范围");
				return j;
			}
			List<WeixinButtonVO> menulist = weixinMenuService.menulist();
			if (menulist != null && menulist.size() >= WeixinButtonConstant.MAXMENU) {
				j.setSuccess(false);
				j.setMsg("超出菜单数量最大限制");
				return j;
			}
			try {
				WeixinButtonVO menuben = new WeixinButtonVO();
				// 主要去掉一些属性
				menuben.setName(weixinmenu.getName());
				menuben.setMenuorder(weixinmenu.getMenuorder());
				weixinMenuService.create(menuben);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		j.setSuccess(true);
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "删除菜单", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(String id) {
		Json j = new Json();
		BaseMap<String, Object> conditionmap = new BaseMap<>();
		conditionmap.put("id", id);
		weixinMenuService.remove(conditionmap);
		conditionmap.clear();
		conditionmap.put("parentid", id);
		weixinMenuService.remove(conditionmap);
		j.setSuccess(true);
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "菜单列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<WeixinButtonVO> list() {
		List<WeixinButtonVO> list = weixinMenuService.list();
		Collections.sort(list);
		return list;
	}
	
	@ApiOperation(value = "选择列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "select.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> select() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(15);
		result.put("menuList", weixinMenuService.list());
		ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return map;
	}
	
	@ApiOperation(value = "单个菜单信息", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "get.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> get(String id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(15);
		result.put("weixinbutton", weixinMenuService.get(id));
		ResponseEntity<Map<String, Object>> map = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return map;
	}
	
	@ApiOperation(value = "更新菜单", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody WeixinButtonVO weixinmenu) throws Exception {
		Json j = new Json();
		if (!QvoConditionUtil.checkString(weixinmenu.getName())) {
			j.setSuccess(false);
			j.setMsg("名称不能为空");
			return j;
		}
		if (weixinmenu.getIfsub() == WeixinButtonConstant.IFSUBYES) {
			// 如果是按钮类型
			if (weixinmenu.getButtonorder() > WeixinButtonConstant.MAXBUTTON || weixinmenu.getButtonorder() < 1) {
				j.setSuccess(false);
				j.setMsg("按钮序号超出范围");
				return j;
			}
			WeixinButtonVO parent = weixinMenuService.get(weixinmenu.getParentid());
			if (parent == null) {
				weixinmenu.setParentid(null);
			}
			else {
				if (parent.getIfsub() == WeixinButtonConstant.IFSUBYES) {
					j.setSuccess(false);
					j.setMsg("操作失败。该父级ID 不能作为上一级");
					return j;
				}
				weixinmenu.setMenuorder(parent.getMenuorder());
			}
			// 查询 子菜单个数
			switch (weixinmenu.getType()) {
			case WeixinButtonConstant.TYPE_VIEW:
				if (QvoConditionUtil.checkString(weixinmenu.getUrl())) {
					try {
						updatebean(weixinmenu);
					} catch (Exception e) {
						e.printStackTrace();
					}
					j.setSuccess(true);
					j.setMsg("操作成功");
					return j;
				}
				else {
					j.setSuccess(false);
					j.setMsg("操作失败，请填写菜单按钮");
					return j;
				}
			default:
				j.setSuccess(false);
				j.setMsg("尚未开发");
				return j;
			}
		}
		else {// 菜单类型
			try {
				WeixinButtonVO menuben = new WeixinButtonVO();
				// 主要去掉一些属性
				menuben.setName(weixinmenu.getName());
				menuben.setMenuorder(weixinmenu.getMenuorder());
				menuben.setId(weixinmenu.getId());
				updatebean(menuben);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		j.setSuccess(true);
		j.setMsg("操作成功");
		return j;
	}
	
	private void updatebean(WeixinButtonVO weixinmenu) {
		BaseMap<String, Object> wheremap = new BaseMap<>();
		BaseMap<String, Object> updatemap = new BaseMap<>();
		updatemap.put("`name`", weixinmenu.getName());
		updatemap.put("`key`", weixinmenu.getKey());
		updatemap.put("`url`", weixinmenu.getUrl());
		updatemap.put("`media_id`", weixinmenu.getMedia_id());
		updatemap.put("`appid`", weixinmenu.getAppid());
		updatemap.put("`pagepath`", weixinmenu.getPagepath());
		// updatemap.put("`parentid`", weixinmenu.getParentid());
		updatemap.put("`type`", weixinmenu.getType());
		updatemap.put("ifsub", weixinmenu.getIfsub());
		updatemap.put("`menuorder`", weixinmenu.getMenuorder());
		updatemap.put("buttonorder", weixinmenu.getButtonorder());
		wheremap.put("id", weixinmenu.getId());
		weixinMenuService.update(updatemap, wheremap);
	}
	
	@ApiOperation(value = "同步到菜单", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "save.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json save() {
		Json j = new Json();
		WeixinJson json = weixinMenuService.save(weixinMenuService.list());
		j.setSuccess(json.isSuccess());
		j.setMsg(json.getErrorMsg());
		return j;
	}
	
	/** 清空微信菜单 **/
	@ResponseBody
	@RequestMapping(value = { "cleanmenu.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json cleanmenu() {
		Json j = new Json();
		WeixinJson json = weixinMenuService.cleanmenu();
		j.setSuccess(json.isSuccess());
		j.setMsg(json.getErrorMsg());
		return j;
	}
}
