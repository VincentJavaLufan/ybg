package com.ybg.quartz.schedule.dao;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQvo;

/** 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:30:02 */
public interface ScheduleJobLogDao {
	
	ScheduleJobLogEntity queryObject(Long jobId);
	
	Page queryList(Page page, ScheduleJobLogQvo qvo);
	
	void save(ScheduleJobLogEntity log) throws Exception;
}
