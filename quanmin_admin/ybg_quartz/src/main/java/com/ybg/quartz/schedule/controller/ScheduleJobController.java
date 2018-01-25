package com.ybg.quartz.schedule.controller;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
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
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobQuery;
import com.ybg.quartz.schedule.service.ScheduleJobService;
import com.ybg.quartz.schedule.util.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月28日 下午2:16:40 */
@Api(tags="定时任务API")
@Controller
@RequestMapping(value = { "/sys/schedule_do/" })
public class ScheduleJobController {
	
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	@ApiOperation(value = "定时任务列表", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/quartz/job/index";
	}
	
	/** 定时任务列表
	 * 
	 * @throws Exception */
	@ApiOperation(value = "定时任务列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute ScheduleJobQuery qvo, @ModelAttribute Page page, ModelMap map) throws Exception {
//		Page page = new Page();
//		page.setCurPage(pageNow);
		ScheduleJobQuery qvo2 = new ScheduleJobQuery();
		qvo2.setBean_Name(qvo.getBean_Name());
		page = scheduleJobService.queryList(page, qvo2);
		page.init();
		return page;
	}
	
	/** 定时任务信息 */
	/** 定时任务列表 */
	@ApiOperation(value = "定时任务页面", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "info.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, Object>> info(Long jobId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
		result.put("schedule", schedule);
		ResponseEntity<Map<String, Object>> bean = new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		return bean;
	}
	
	/** 保存定时任务
	 * 
	 * @throws Exception */
	@ApiOperation(value = " 保存定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json save(@RequestBody ScheduleJobEntity scheduleJob) throws Exception {
		// 数据校验
		Json json = new Json();
		try {
			verifyForm(scheduleJob);
			scheduleJobService.save(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("操作失败");
			json.setSuccess(false);
			return json;
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	// @RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	// public String toupdate(Long jobId, ModelMap map) {
	// map.put("bean", scheduleJobService.queryObject(jobId));
	// return "/quartz/job/toupdate";
	// }
	/** 修改定时任务 */
	@ApiOperation(value = "修改定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody ScheduleJobEntity scheduleJob) {
		// 数据校验
		Json json = new Json();
		try {
			verifyForm(scheduleJob);
			scheduleJobService.update(scheduleJob);
		} catch (Exception e) {
			json.setMsg("操作失败");
			json.setSuccess(false);
			return json;
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 删除定时任务 */
	@ApiOperation(value = "删除定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "delete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json delete(String jobIds) {
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.deleteBatch(longid);
		}
		Json json = new Json();
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 立即执行任务 */
	@ApiOperation(value = "立即执行任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "run.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json run(String jobIds) {
		Json json = new Json();
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.run(longid);
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 暂停定时任务 */
	@ApiOperation(value = "暂停定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "pause.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json pause(String jobIds) {
		Json json = new Json();
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.pause(longid);
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 恢复定时任务 */
	@ApiOperation(value = "恢复定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "resume.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json resume(String jobIds) {
		Json json = new Json();
		String[] ids = jobIds.split(",");
		for (String idstr : ids) {
			Long[] longid = new Long[1];
			try {
				longid[0] = Long.parseLong(idstr);
			} catch (Exception e) {
				continue;
			}
			scheduleJobService.resume(longid);
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 验证参数是否正确 */
	private void verifyForm(ScheduleJobEntity scheduleJob) {
		if (StringUtils.isBlank(scheduleJob.getBean_Name())) {
			throw new RRException("bean名称不能为空");
		}
		if (StringUtils.isBlank(scheduleJob.getMethod_Name())) {
			throw new RRException("方法名称不能为空");
		}
		if (StringUtils.isBlank(scheduleJob.getCron_Expression())) {
			throw new RRException("cron表达式不能为空");
		}
	}
}
