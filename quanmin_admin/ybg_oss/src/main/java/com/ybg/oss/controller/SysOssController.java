package com.ybg.oss.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.base.util.ValidatorUtils;
import com.ybg.base.util.webexception.RepostResult;
import com.ybg.base.util.webexception.ResultException;
import com.ybg.oss.CloudStorageConfig;
import com.ybg.oss.ConfigConstant;
import com.ybg.oss.OssConstant;
import com.ybg.oss.OssFactory;
import com.ybg.oss.domian.SysOssEntity;
import com.ybg.oss.service.SysConfigService;
import com.ybg.oss.service.SysOssService;
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
@Api(tags="文件上传")
@Controller
@RequestMapping("/sys/oss_do/")
public class SysOssController {
	
	@Autowired
	private SysOssService		sysOssService;
	@Autowired
	private SysConfigService	sysConfigService;
	private final static String	KEY	= ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	@ApiOperation(value = "文件上传例子首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/system/ossconfig/oss";
	}
	
	/** 列表 */
	@ApiOperation(value = "列表数据", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "SysOssEntity") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute SysOssEntity qvo,@ModelAttribute Page page)  {
//		Page page = new Page();
//		page.setCurPage(pageNow);
		page = sysOssService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 云存储配置信息 */
	@ApiOperation(value = "云存储配置信息", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "config.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public RepostResult config() {
		CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
		return RepostResult.ok().put("config", config);
	}
	
	/** 保存云存储配置信息
	 * 
	 * @throws Exception */
	@ApiOperation(value = "保存云存储配置信息", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "saveConfig.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json saveConfig(@RequestBody CloudStorageConfig config) throws Exception {
		Json j = new Json();
		// 校验类型
		ValidatorUtils.validateEntity(config);
		if (config.getType() == OssConstant.CloudService.QINIU.getValue()) {
			// 校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}
		else if (config.getType() == OssConstant.CloudService.ALIYUN.getValue()) {
			// 校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}
		else if (config.getType() == OssConstant.CloudService.QCLOUD.getValue()) {
			// 校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	/** 上传文件 */
	@ApiOperation(value = "上传文件", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "upload.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new ResultException("上传文件不能为空");
		}
		Map<String, Object> result = new HashMap<String, Object>(1);
		// 上传文件
		String url = OssFactory.build().upload(file.getBytes());
		// 保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreatedate(DateUtil.getDateTime());
		sysOssService.save(ossEntity);
		result.put("url", url);
		result.put("success", true);
		ResponseEntity<Map<String, Object>> bean = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return bean;
	}
	
	/** 删除 */
	@ApiOperation(value = "刪除文件（图片）", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json delete(@RequestParam(name = "ids", required = true) String ids2) {
		Json json = new Json();
		String[] ids = ids2.split(",");
		for (String id : ids) {
			Long id3 = Long.parseLong(id);
			Long[] id4 = new Long[1];
			id4[0] = id3;
			sysOssService.deleteBatch(id4);
		}
		json.setMsg("删除成功");
		json.setSuccess(true);
		return json;
	}
}
