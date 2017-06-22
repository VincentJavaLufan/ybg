package com.ybg.quartz.schedule.service;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQvo;

public interface ScheduleJobLogService {
	
	ScheduleJobLogEntity queryObject(Long jobId);
	
	// public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
	// return scheduleJobLogDao.queryList(map);
	// }
	//
	//
	// public int queryTotal(Map<String, Object> map) {
	// return scheduleJobLogDao.queryTotal(map);
	// }
	Page queryList(Page page, ScheduleJobLogQvo qvo);
	
	void save(ScheduleJobLogEntity log) throws Exception;
}
