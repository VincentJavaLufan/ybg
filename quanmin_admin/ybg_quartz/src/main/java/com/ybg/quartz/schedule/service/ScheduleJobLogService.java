package com.ybg.quartz.schedule.service;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogDO;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQuery;

public interface ScheduleJobLogService {
	
	ScheduleJobLogDO queryObject(Long jobId);
	
	// public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
	// return scheduleJobLogDao.queryList(map);
	// }
	//
	//
	// public int queryTotal(Map<String, Object> map) {
	// return scheduleJobLogDao.queryTotal(map);
	// }
	Page queryList(Page page, ScheduleJobLogQuery qvo) throws Exception;
	
	void save(ScheduleJobLogDO log) throws Exception;
}
