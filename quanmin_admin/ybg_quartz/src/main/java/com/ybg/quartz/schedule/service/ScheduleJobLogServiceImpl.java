package com.ybg.quartz.schedule.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.dao.ScheduleJobLogDao;
import com.ybg.quartz.schedule.domain.ScheduleJobLogEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQvo;
@Repository
public class ScheduleJobLogServiceImpl implements  ScheduleJobLogService{
	
	@Autowired
	private ScheduleJobLogDao	scheduleJobLogDao;
	
	public Page queryList(Page page, ScheduleJobLogQvo qvo) {
		return scheduleJobLogDao.queryList(page, qvo);
	}
	
	public void save(ScheduleJobLogEntity log) throws Exception {
		scheduleJobLogDao.save(log);
	}
	
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}
}
