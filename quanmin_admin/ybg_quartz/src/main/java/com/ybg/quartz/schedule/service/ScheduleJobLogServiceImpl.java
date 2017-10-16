package com.ybg.quartz.schedule.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.dao.ScheduleJobLogDao;
import com.ybg.quartz.schedule.domain.ScheduleJobLogDO;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQuery;
@Repository
public class ScheduleJobLogServiceImpl implements  ScheduleJobLogService{
	
	@Autowired
	private ScheduleJobLogDao	scheduleJobLogDao;
	@Override
	public Page queryList(Page page, ScheduleJobLogQuery qvo) throws Exception {
		return scheduleJobLogDao.queryList(page, qvo);
	}
	@Override
	public void save(ScheduleJobLogDO log) throws Exception {
		scheduleJobLogDao.save(log);
	}
	@Override
	public ScheduleJobLogDO queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}
}
