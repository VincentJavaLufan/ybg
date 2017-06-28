package com.ybg.quartz.schedule.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.dao.ScheduleJobDao;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobQuery;
import com.ybg.quartz.schedule.util.ScheduleUtils;
import com.ybg.quartz.schedule.util.Constant.ScheduleStatus;

@Repository
public class ScheduleJobServiceImpl implements ScheduleJobService {
	
	@Autowired
	private Scheduler		scheduler;
	@Autowired
	private ScheduleJobDao	schedulerJobDao;
	
	/** 项目启动时，初始化定时器 */
	@PostConstruct
	public void init() {
		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new ScheduleJobQuery());
		for (ScheduleJobEntity scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			}
			else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}
	
	public ScheduleJobEntity queryObject(Long jobId) {
		return schedulerJobDao.queryObject(jobId);
	}
	
	public Page queryList(Page page, ScheduleJobQuery qvo) {
		return schedulerJobDao.queryList(page, qvo);
	}
	
	public List<ScheduleJobEntity> queryList(ScheduleJobQuery qvo) {
		return schedulerJobDao.queryList(qvo);
	}
	
	@Transactional
	public void save(ScheduleJobEntity scheduleJob) throws Exception {
		scheduleJob.setCreateTime(DateUtil.getDate());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		schedulerJobDao.save(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}
	
	@Transactional
	public void update(ScheduleJobEntity scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		schedulerJobDao.update(scheduleJob);
	}
	
	@Transactional
	public void deleteBatch(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		}
		// 删除数据
		schedulerJobDao.deleteBatch(jobIds);
	}
	
	public int updateBatch(Long[] jobIds, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", jobIds);
		map.put("status", status);
		return schedulerJobDao.updateBatch(status, jobIds);
	}
	
	@Transactional
	public void run(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, queryObject(jobId));
		}
	}
	
	@Transactional
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}
		updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}
	
	@Transactional
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}
		updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}
}
