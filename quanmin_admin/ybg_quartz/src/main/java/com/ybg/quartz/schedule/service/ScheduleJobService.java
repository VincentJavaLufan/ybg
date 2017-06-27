package com.ybg.quartz.schedule.service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobDO;
import com.ybg.quartz.schedule.qvo.ScheduleJobQuery;

public interface ScheduleJobService {
	
	ScheduleJobDO queryObject(Long jobId);
	
	Page queryList(Page page, ScheduleJobQuery qvo);
	
	List<ScheduleJobDO> queryList(ScheduleJobQuery qvo);
	
	@Transactional
	void save(ScheduleJobDO scheduleJob) throws Exception;
	
	@Transactional
	void update(ScheduleJobDO scheduleJob);
	
	@Transactional
	void deleteBatch(Long[] jobIds);
	
	int updateBatch(Long[] jobIds, int status);
	
	@Transactional
	void run(Long... jobIds);
	
	@Transactional
	void pause(Long... jobIds);
	
	@Transactional
	void resume(Long... jobIds);
}
