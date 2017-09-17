package com.ybg.quartz.schedule.util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.domain.ScheduleJobLogDO;
import com.ybg.quartz.schedule.service.ScheduleJobLogService;
import net.sf.json.JSONObject;

/** 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午12:44:21 */
public class ScheduleJob extends QuartzJobBean {
	
	private Logger			logger	= LoggerFactory.getLogger(getClass());
	private ExecutorService	service	= Executors.newSingleThreadExecutor();
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JSONObject json = null;
		try {
			json = JSONObject.fromObject(context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY));
		} catch (Exception e) {
			return;
		} // springboot 下会，莫名报错 ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);
		ScheduleJobEntity scheduleJob = (ScheduleJobEntity) JSONObject.toBean(json, ScheduleJobEntity.class);
		// 获取spring bean
		ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.applicationContext.getBean(ScheduleJobLogService.class);
		// 数据库保存执行记录
		ScheduleJobLogDO log = new ScheduleJobLogDO();
		log.setJob_Id(scheduleJob.getJob_Id());
		log.setBean_Name(scheduleJob.getBean_Name());
		log.setMethod_Name(scheduleJob.getMethod_Name());
		log.setParams(scheduleJob.getParams());
		log.setCreate_Time(DateUtil.getDate());
		log.setError("");
		// 任务开始时间
		long startTime = System.currentTimeMillis();
		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getJob_Id());
			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBean_Name(), scheduleJob.getMethod_Name(), scheduleJob.getParams());
			Future<?> future = service.submit(task);
			future.get();
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(0);
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJob_Id() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJob_Id(), e);
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		} finally {
			try {
				scheduleJobLogService.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
