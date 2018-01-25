package com.ybg.quartz.schedule.controller;
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
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQuery;
import com.ybg.quartz.schedule.service.ScheduleJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/** 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:39:52 */
@Api(tags="定时任务日志API")
@Controller
@RequestMapping(value = { "/sys/scheduleLog_do/" })
public class ScheduleJobLogController {
	
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	@ApiOperation(value = "定时任务日志首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "/quartz/log/index";
	}
	
	/** 定时任务日志列表 
	 * @throws Exception */
	@ApiOperation(value = "定是数据列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "java.lang.Integer")
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute Page page) throws Exception {
//		Page page = new Page();
//		page.setCurPage(pageNow);
		ScheduleJobLogQuery qvo = new ScheduleJobLogQuery();
		page = scheduleJobLogService.queryList(page, qvo);
		page.init();
		return page;
	}
	// /** 定时任务日志信息 */
	// @ApiOperation(value = "定时任务详细信息", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// @RequestMapping(value = { "/info.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	// public ScheduleJobLogEntity info(HttpServletRequest request, HttpServletResponse response) {
	// Long logId = ServletUtil.getLongParamDefault0(request, "logId");
	// ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
	// return log;
	// }
}
