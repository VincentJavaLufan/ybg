package com.ybg.mq.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mq.domian.MQproducer;

@Repository
public class MQproducerDaoImpl extends BaseDao implements MQproducerDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public MQproducer getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,topic,url,ak,sk,producerid from sys_mq_producer sp ");
		List<MQproducer> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(MQproducer.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : new MQproducer();
	}
}
