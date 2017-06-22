package com.ybg.quartz.schedule.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQvo;

@Repository
public class ScheduleJobLogDaoImpl extends BaseDao implements ScheduleJobLogDao {
	
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select log_id,job_id,bean_name,method_name,params,status,error,times,create_time from schedule_job_log where log_id = " + jobId);
		List<ScheduleJobLogEntity> list = getJdbcTemplate().query(sql.toString(), new RowMapper<ScheduleJobLogEntity>() {
			
			@Override
			public ScheduleJobLogEntity mapRow(ResultSet rs, int index) throws SQLException {
				ScheduleJobLogEntity bean = new ScheduleJobLogEntity();
				bean.setBeanName(rs.getString("bean_name"));
				bean.setCreateTime(rs.getString("create_time"));
				bean.setError(rs.getString("error"));
				bean.setJobId(rs.getLong("job_id"));
				bean.setLogId(rs.getLong("log_id"));
				bean.setMethodName(rs.getString("method_name"));
				bean.setParams(rs.getString("params"));
				bean.setStatus(rs.getInt("status"));
				bean.setTimes(rs.getInt("times"));
				return bean;
			}
		});
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void save(ScheduleJobLogEntity log) throws Exception {
		Map<String, Object> createmap = new LinkedHashMap<String, Object>();
		createmap.put("bean_name", log.getBeanName());
		createmap.put("create_time", log.getCreateTime());
		createmap.put("error", log.getError());
		createmap.put("job_id", log.getJobId());
		createmap.put("method_name", log.getMethodName());
		createmap.put("params", log.getParams());
		createmap.put("status", log.getStatus());
		createmap.put("times", log.getTimes());
		basecreate(createmap, "schedule_job_log", false, new Long(0));
	}
	
	@Override
	public Page queryList(Page page, ScheduleJobLogQvo qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select log_id,job_id,bean_name,method_name,params,status,error,times,create_time from schedule_job_log  ");
		sqlappen(sql, " job_id ", qvo.getJobId());
		List<ScheduleJobLogEntity> list = getJdbcTemplate().query(page.getPagesql(sql), new RowMapper<ScheduleJobLogEntity>() {
			
			@Override
			public ScheduleJobLogEntity mapRow(ResultSet rs, int index) throws SQLException {
				ScheduleJobLogEntity bean = new ScheduleJobLogEntity();
				bean.setBeanName(rs.getString("bean_name"));
				bean.setCreateTime(rs.getString("create_time"));
				bean.setError(rs.getString("error"));
				bean.setJobId(rs.getLong("job_id"));
				bean.setLogId(rs.getLong("log_id"));
				bean.setMethodName(rs.getString("method_name"));
				bean.setParams(rs.getString("params"));
				bean.setStatus(rs.getInt("status"));
				bean.setTimes(rs.getInt("times"));
				return bean;
			}
		});
		page.setResult(list);
		page.setTotals(queryForInt(sql));
		return page;
	}
}
