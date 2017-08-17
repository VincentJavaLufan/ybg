package com.ybg.oss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ybg.oss.CloudStorageConfig;
import com.ybg.oss.ConfigConstant;
import com.ybg.oss.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53 */
@Api(tags="系统配置信息")
@Controller
@RequestMapping("/sys/config_do/")
public class SysConfigController {
	
	@Autowired
	private SysConfigService	sysConfigService;
	private final static String	KEY	= ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	@ApiOperation(value = "系统配置信息首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map) {
		CloudStorageConfig bean = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
		map.put("config", bean);
		return "/system/ossconfig/oss_config";
	}
	// @ApiOperation(value = "配置列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// @RequestMapping(value={"list.do"}, method = { RequestMethod.GET, RequestMethod.POST })
	// public Page list(@ModelAttribute SysConfigEntity qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
	// Page page = new Page();
	// page.setCurPage(pageNow);
	// page = sysConfigService.list(page, qvo);
	// page.init();
	// return page;
	// }
	// @ResponseBody
	// /** 配置信息 */
	// @RequestMapping("/info/{id}")
	// public R info(@PathVariable("id") Long id) {
	// SysConfigEntity config = sysConfigService.queryObject(id);
	// return R.ok().put("config", config);
	// }
	//
	// /** 保存配置
	// *
	// * @throws Exception */
	// @ResponseBody
	// @RequestMapping("save.do")
	// public R save(@RequestBody SysConfigEntity config) throws Exception {
	// ValidatorUtils.validateEntity(config);
	// sysConfigService.save(config);
	// return R.ok();
	// }
	//
	// /** 修改配置
	// *
	// * @throws Exception */
	// @ResponseBody
	// @RequestMapping("update.do")
	// public R update(@RequestBody SysConfigEntity config) throws Exception {
	// ValidatorUtils.validateEntity(config);
	// sysConfigService.update(config);
	// return R.ok();
	// }
	//
	// /** 删除配置 */
	// @RequestMapping("delete.do")
	// @ResponseBody
	// public R delete(@RequestBody Long[] ids) {
	// sysConfigService.deleteBatch(ids);
	// return R.ok();
	// }
}
