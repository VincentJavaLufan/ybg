package com.ybg.quartz.schedule.dao;
import java.util.List;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobQuery;

/** 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57 */
public interface ScheduleJobDao {
	
	/** 批量更新状态 */
	int updateBatch(int status, final Long[] job_id);
	
	ScheduleJobEntity queryObject(Long jobId);
	
	void deleteBatch(Long[] jobIds);
	
	void update(ScheduleJobEntity scheduleJob);
	
	void save(ScheduleJobEntity scheduleJob) throws Exception;
	
	List<ScheduleJobEntity> queryList(ScheduleJobQuery qvo);
	
	Page queryList(Page page, ScheduleJobQuery qvo);
}
