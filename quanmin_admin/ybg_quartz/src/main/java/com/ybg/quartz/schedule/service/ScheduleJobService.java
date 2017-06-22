package com.ybg.quartz.schedule.service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobQvo;

public interface ScheduleJobService {
	
	ScheduleJobEntity queryObject(Long jobId);
	
	Page queryList(Page page, ScheduleJobQvo qvo);
	
	List<ScheduleJobEntity> queryList(ScheduleJobQvo qvo);
	
	@Transactional
	void save(ScheduleJobEntity scheduleJob) throws Exception;
	
	@Transactional
	void update(ScheduleJobEntity scheduleJob);
	
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
