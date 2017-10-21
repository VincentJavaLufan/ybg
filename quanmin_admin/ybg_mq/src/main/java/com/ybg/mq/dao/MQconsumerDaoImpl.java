package com.ybg.mq.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mq.domian.MqConsumer;

@Repository
public class MQconsumerDaoImpl extends BaseDao implements MqConsumerDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public MqConsumer getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,topic,url,ak,sk,consumerid from sys_mq_consumer mc");
		List<MqConsumer> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<MqConsumer>(MqConsumer.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : new MqConsumer();
	}
}
