package com.ybg.mq.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.mq.domian.MQproducer;

@Repository
public class MQproducerDaoImpl extends BaseDao implements MQproducerDao {
	
	@Override
	public MQproducer getIsUse() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,topic,url,ak,sk,producerid from sys_mq_producer sp ");
		List<MQproducer> list = getJdbcTemplate().query(sql.toString(), new RowMapper<MQproducer>() {
			
			@Override
			public MQproducer mapRow(ResultSet rs, int index) throws SQLException {
				MQproducer bean = new MQproducer();
				bean.setAk(rs.getString("ak"));
				bean.setId(rs.getString("id"));
				bean.setProducerid(rs.getString("producerid"));
				bean.setSk(rs.getString("sk"));
				bean.setTopic(rs.getString("topic"));
				bean.setUrl(rs.getString("url"));
				return bean;
			}
		});
		return QvoConditionUtil.checkList(list) ? list.get(0) : new MQproducer();
	}
}
