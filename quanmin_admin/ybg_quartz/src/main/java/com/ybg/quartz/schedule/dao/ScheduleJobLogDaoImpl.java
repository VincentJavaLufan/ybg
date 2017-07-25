package com.ybg.quartz.schedule.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobLogDO;
import com.ybg.quartz.schedule.qvo.ScheduleJobLogQuery;

@Repository
public class ScheduleJobLogDaoImpl extends BaseDao implements ScheduleJobLogDao {
	
	@Autowired
//	@Qualifier(DataBaseConstant.JD_OA)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public ScheduleJobLogDO queryObject(Long jobId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select log_id,job_id,bean_name,method_name,params,status,error,times,create_time from schedule_job_log where log_id = " + jobId);
		List<ScheduleJobLogDO> list = getJdbcTemplate().query(sql.toString(), new RowMapper<ScheduleJobLogDO>() {
			
			@Override
			public ScheduleJobLogDO mapRow(ResultSet rs, int index) throws SQLException {
				ScheduleJobLogDO bean = new ScheduleJobLogDO();
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
	public void save(ScheduleJobLogDO log) throws Exception {
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
	public Page queryList(Page page, ScheduleJobLogQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select log_id,job_id,bean_name,method_name,params,status,error,times,create_time from schedule_job_log  ");
		sqlappen(sql, " job_id ", qvo.getJobId());
		List<ScheduleJobLogDO> list = getJdbcTemplate().query(page.getPagesql(sql), new RowMapper<ScheduleJobLogDO>() {
			
			@Override
			public ScheduleJobLogDO mapRow(ResultSet rs, int index) throws SQLException {
				ScheduleJobLogDO bean = new ScheduleJobLogDO();
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
