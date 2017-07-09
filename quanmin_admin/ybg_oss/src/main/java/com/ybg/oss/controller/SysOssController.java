package com.ybg.oss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.base.util.webexception.R;
import com.ybg.base.util.webexception.RRException;
import com.ybg.oss.CloudStorageConfig;
import com.ybg.oss.ConfigConstant;
import com.ybg.oss.Constant;
import com.ybg.oss.OSSFactory;
import com.ybg.oss.domian.SysOssEntity;
import com.ybg.oss.service.SysConfigService;
import com.ybg.oss.service.SysOssService;
import com.ybg.oss.validator.ValidatorUtils;
import com.ybg.oss.validator.group.AliyunGroup;
import com.ybg.oss.validator.group.QcloudGroup;
import com.ybg.oss.validator.group.QiniuGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26 */
@Api("文件上传")
@Controller
@RequestMapping("/sys/oss_do/")
public class SysOssController {
	
	@Autowired
	private SysOssService		sysOssService;
	@Autowired
	private SysConfigService	sysConfigService;
	private final static String	KEY	= ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/** 列表 */
	@ApiOperation(value = "列表数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "SysOssEntity") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute SysOssEntity qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) {
		Page page = new Page();
		page.setCurPage(pageNow);
		page = sysOssService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 云存储配置信息 */
	@ApiOperation(value = "云存储配置信息")
	@ResponseBody
	@RequestMapping("config.do")
	public R config() {
		CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
		return R.ok().put("config", config);
	}
	
	/** 保存云存储配置信息
	 * 
	 * @throws Exception */
	@ApiOperation(value = "保存云存储配置信息")
	@ResponseBody
	@RequestMapping("saveConfig.do")
	public Json saveConfig(@RequestBody CloudStorageConfig config) throws Exception {
		Json j = new Json();
		// 校验类型
		ValidatorUtils.validateEntity(config);
		if (config.getType() == Constant.CloudService.QINIU.getValue()) {
			// 校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}
		else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
			// 校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}
		else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
			// 校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
		return j;
	}
	
	/** 上传文件 */
	@ApiOperation(value = "上传文件")
	@ResponseBody
	@RequestMapping("upload.do")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}
		// 上传文件
		String url = OSSFactory.build().upload(file.getBytes());
		// 保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreatedate(DateUtil.getDateTime());
		sysOssService.save(ossEntity);
		return R.ok().put("url", url);
	}
	
	/** 删除 */
	@RequestMapping("delete.do")
	@ResponseBody
	public R delete(@RequestBody Long[] ids) {
		sysOssService.deleteBatch(ids);
		return R.ok();
	}
}
