package com.ybg.quartz.schedule.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.quartz.schedule.domain.ScheduleJobEntity;
import com.ybg.quartz.schedule.qvo.ScheduleJobQuery;

@Repository
public class ScheduleJobDaoImpl extends BaseDao implements ScheduleJobDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_QUARTZ)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public int updateBatch(int status, final Long[] job_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("update schedule_job set status = " + status + " where job_id =? ");
		int[] result = getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setLong(1, job_id[index]);
			}
			
			@Override
			public int getBatchSize() {
				return job_id.length;
			}
		});
		return 0;
	}
	
	@Override
	public ScheduleJobEntity queryObject(Long jobId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select job_id,bean_name,method_name,params,cron_expression,status,remark,create_time from schedule_job where job_id = " + jobId);
		List<ScheduleJobEntity> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<ScheduleJobEntity>());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
	
	@Override
	public void deleteBatch(final Long[] jobIds) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from schedule_job where job_id =? ");
		getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setLong(1, jobIds[index]);
			}
			
			@Override
			public int getBatchSize() {
				return jobIds.length;
			}
		});
	}
	
	@Override
	public void update(ScheduleJobEntity scheduleJob) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		updatemap.put("bean_name", scheduleJob.getBean_Name());
		updatemap.put("create_time", scheduleJob.getCreate_Time());
		updatemap.put("cron_expression", scheduleJob.getCron_Expression());
		updatemap.put("method_name", scheduleJob.getMethod_Name());
		updatemap.put("params", scheduleJob.getParams());
		updatemap.put("remark", scheduleJob.getRemark());
		updatemap.put("status", scheduleJob.getStatus());
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("job_id", scheduleJob.getJob_Id());
		baseupdate(updatemap, wheremap, "schedule_job");
	}
	
	@Override
	public void save(ScheduleJobEntity scheduleJob) throws Exception {
		Map<String, Object> createmap = new LinkedHashMap<String, Object>();
		createmap.put("bean_name", scheduleJob.getBean_Name());
		createmap.put("create_time", scheduleJob.getCreate_Time());
		createmap.put("cron_expression", scheduleJob.getCron_Expression());
		createmap.put("method_name", scheduleJob.getMethod_Name());
		createmap.put("params", scheduleJob.getParams());
		createmap.put("remark", scheduleJob.getRemark());
		createmap.put("status", scheduleJob.getStatus());
		Object id = basecreate(createmap, "schedule_job", true, new Long(0));
		scheduleJob.setJob_Id(new Long((long) id));
	}
	
	@Override
	public Page queryList(Page page, ScheduleJobQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select job_id,bean_name,method_name,params,cron_expression,status,remark,create_time  from schedule_job job ");
		sqlappen(sql, "bean_name", qvo.getBean_Name());
		List<ScheduleJobEntity> list = getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<ScheduleJobEntity>());
		page.setResult(list);
		page.setTotals(queryForInt(sql));
		return page;
	}
	
	@Override
	public List<ScheduleJobEntity> queryList(ScheduleJobQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select job_id,bean_name,method_name,params,cron_expression,status,remark,create_time  from schedule_job job ");
		sql.append(" where 1=1 ");
		sqlappen(sql, "beanname", qvo.getBean_Name());
		sql.append(" and status = 0 ");
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<ScheduleJobEntity>());
		// return new ArrayList<ScheduleJobEntity>();
	}
}
