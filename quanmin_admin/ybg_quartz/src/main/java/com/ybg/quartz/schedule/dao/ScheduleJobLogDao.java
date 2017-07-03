package com.ybg.quartz.schedule.dao;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogDO;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQuery;

/** 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:30:02 */
public interface ScheduleJobLogDao {
	
	ScheduleJobLogDO queryObject(Long jobId);
	
	Page queryList(Page page, ScheduleJobLogQuery qvo) throws Exception;
	
	void save(ScheduleJobLogDO log) throws Exception;
}
