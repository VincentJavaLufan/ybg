package com.ybg.mq.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mq.domian.MQconsumer;

@Repository
public class MQconsumerDaoImpl extends BaseDao implements MQconsumerDao {
	
	@Override
	public MQconsumer getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,topic,url,ak,sk,consumerid from sys_mq_consumer mc");
		List<MQconsumer> list = getJdbcTemplate().query(sql.toString(), new RowMapper<MQconsumer>() {
			
			@Override
			public MQconsumer mapRow(ResultSet rs, int index) throws SQLException {
				MQconsumer bean = new MQconsumer();
				bean.setId(rs.getString("id"));
				bean.setAk(rs.getString("ak"));
				bean.setSk(rs.getString("sk"));
				bean.setTopic(rs.getString("topic"));
				bean.setUrl(rs.getString("url"));
				bean.setConsumerid(rs.getString("consumerid"));
				return bean;
			}
		});
		return QvoConditionUtil.checkList(list) ? list.get(0) : new MQconsumer();
	}
}
