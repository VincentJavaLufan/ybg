package com.ybg.quartz.schedule.controller;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@Api("定时任务API")
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
	
	/** 定时任务列表 */
	@ApiOperation(value = "定时任务列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute ScheduleJobQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) {
		Page page = new Page();
		page.setCurPage(pageNow);
		ScheduleJobQuery qvo2 = new ScheduleJobQuery();
		qvo2.setBeanName(qvo.getBeanName());
		page = scheduleJobService.queryList(page, qvo2);
		page.init();
		return page;
	}
	
	/** 定时任务信息 */
	/** 定时任务列表 */
	@ApiOperation(value = "定时任务页面", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "info.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ScheduleJobEntity info(Long jobId) {
		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
		return schedule;
		// return R.ok().put("schedule", schedule);
	}
	
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd() {
		return "/quartz/job/toadd";
	}
	
	/** 保存定时任务
	 * 
	 * @throws Exception */
	@ApiOperation(value = " 保存定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json save(@ModelAttribute ScheduleJobEntity scheduleJob) throws Exception {
		// 数据校验
		Json json = new Json();
		try {
			verifyForm(scheduleJob);
			scheduleJobService.save(scheduleJob);
		} catch (Exception e) {
			json.setMsg("操作失败");
			json.setSuccess(false);
			return json;
		}
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(Long jobId, ModelMap map) {
		map.put("bean", scheduleJobService.queryObject(jobId));
		return "/quartz/job/toupdate";
	}
	
	/** 修改定时任务 */
	@ApiOperation(value = "修改定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute ScheduleJobEntity scheduleJob) {
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
	public Json delete(Long[] jobIds) {
		Json json = new Json();
		scheduleJobService.deleteBatch(jobIds);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 立即执行任务 */
	@ApiOperation(value = "立即执行任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "run.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json run(@RequestParam(name = "jobIds[]", required = true) Long jobIds) {
		Json json = new Json();
		scheduleJobService.run(jobIds);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 暂停定时任务 */
	@ApiOperation(value = "暂停定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "pause.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json pause(@RequestParam(name = "jobIds[]", required = true) Long jobIds) {
		Json json = new Json();
		scheduleJobService.pause(jobIds);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 恢复定时任务 */
	@ApiOperation(value = "恢复定时任务", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "resume.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json resume(@RequestParam(name = "jobIds[]", required = true) Long jobIds) {
		Json json = new Json();
		scheduleJobService.resume(jobIds);
		json.setMsg("操作成功");
		json.setSuccess(true);
		return json;
	}
	
	/** 验证参数是否正确 */
	private void verifyForm(ScheduleJobEntity scheduleJob) {
		if (StringUtils.isBlank(scheduleJob.getBeanName())) {
			throw new RRException("bean名称不能为空");
		}
		if (StringUtils.isBlank(scheduleJob.getMethodName())) {
			throw new RRException("方法名称不能为空");
		}
		if (StringUtils.isBlank(scheduleJob.getCronExpression())) {
			throw new RRException("cron表达式不能为空");
		}
	}
}
