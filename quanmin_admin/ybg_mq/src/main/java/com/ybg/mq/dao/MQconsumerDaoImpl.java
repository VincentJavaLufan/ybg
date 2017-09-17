package com.ybg.mq.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mq.domian.MQconsumer;

@Repository
public class MQconsumerDaoImpl extends BaseDao implements MQconsumerDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public MQconsumer getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,topic,url,ak,sk,consumerid from sys_mq_consumer mc");
		List<MQconsumer> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<MQconsumer>());
		return QvoConditionUtil.checkList(list) ? list.get(0) : new MQconsumer();
	}
}
