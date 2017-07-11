//package com.ybg.oss.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import com.ybg.base.util.Page;
//import com.ybg.base.util.webexception.R;
//import com.ybg.oss.CloudStorageConfig;
//import com.ybg.oss.ConfigConstant;
//import com.ybg.oss.domian.SysConfigEntity;
//import com.ybg.oss.domian.SysOssEntity;
//import com.ybg.oss.service.SysConfigService;
//import com.ybg.oss.validator.ValidatorUtils;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///** 系统配置信息
// * 
// * @author chenshun
// * @email sunlightcs@gmail.com
// * @date 2016年12月4日 下午6:55:53 */
//@Controller
//@RequestMapping("/sys/config_do/")
//public class SysConfigController {
//	
//	@Autowired
//	private SysConfigService	sysConfigService;
//	// /** 所有配置列表 */
//	// @RequestMapping("/list")
//	// public R list(@RequestParam Map<String, Object> params) {
//	// // 查询列表数据
//	//// Query query = new Query(params);
//	//// List<SysConfigEntity> configList = sysConfigService.queryList(query);
//	//// int total = sysConfigService.queryTotal(query);
//	//// PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
//	//
//	//
//	// return R.ok().put("page", pageUtil);
//	// }
//	private final static String	KEY	= ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
//	
//	@RequestMapping("index.do")
//	public String index(ModelMap map) {
//		CloudStorageConfig bean = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
//		map.put("config", bean);
//		return "/system/ossconfig/oss_config";
//	}
//	
//	@ResponseBody
//	@RequestMapping("list.do")
//	public Page list(@ModelAttribute SysConfigEntity qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
//		Page page = new Page();
//		page.setCurPage(pageNow);
//		page = sysConfigService.list(page, qvo);
//		page.init();
//		return page;
//	}
//	
//	@ResponseBody
//	/** 配置信息 */
//	@RequestMapping("/info/{id}")
//	public R info(@PathVariable("id") Long id) {
//		SysConfigEntity config = sysConfigService.queryObject(id);
//		return R.ok().put("config", config);
//	}
//	
//	/** 保存配置
//	 * 
//	 * @throws Exception */
//	@ResponseBody
//	@RequestMapping("save.do")
//	public R save(@RequestBody SysConfigEntity config) throws Exception {
//		ValidatorUtils.validateEntity(config);
//		sysConfigService.save(config);
//		return R.ok();
//	}
//	
//	/** 修改配置
//	 * 
//	 * @throws Exception */
//	@ResponseBody
//	@RequestMapping("update.do")
//	public R update(@RequestBody SysConfigEntity config) throws Exception {
//		ValidatorUtils.validateEntity(config);
//		sysConfigService.update(config);
//		return R.ok();
//	}
//	
//	/** 删除配置 */
//	@RequestMapping("delete.do")
//	@ResponseBody
//	public R delete(@RequestBody Long[] ids) {
//		sysConfigService.deleteBatch(ids);
//		return R.ok();
//	}
//}
