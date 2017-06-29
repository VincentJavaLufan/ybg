package com.ybg.gen.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.util.Page;
import com.ybg.gen.entity.TableEntity;
import com.ybg.gen.qvo.GeneratorQuery;

@Repository
public class SysGeneratorDaoImpl extends BaseDao implements SysGeneratorDao {
	
	@Override
	public Page list(Page page, GeneratorQuery qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append(" 	where table_schema = (select database())");
		sqlappen(sql, "table_name", qvo.getTable_name());
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			List<TableEntity> list = getJdbcTemplate().query(page.getPagesql(sql), new RowMapper<TableEntity>() {
				
				@Override
				public TableEntity mapRow(ResultSet rs, int index) throws SQLException {
					TableEntity bean = new TableEntity();
					bean.setTableName(rs.getString("tableName"));
					bean.setComments(rs.getString("tableComment"));
					return bean;
				}
			});
			page.setResult(list);
		}
		else {
			page.setResult(new ArrayList<TableEntity>());
		}
		return page;
	}
	
	@Override
	public Map<String, String> queryTable(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append("where table_schema = (select database()) and table_name = '" + tableName + "'");
		Map<String, String> map = new LinkedHashMap<String, String>();
		getJdbcTemplate().query(sql.toString(), new RowMapper<Object>() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				map.put("tableName", rs.getString("tableName"));
				map.put("engine", rs.getString("engine"));
				map.put("tableComment", rs.getString("tableComment"));
				map.put("createTime", rs.getString("createTime"));
				return null;
			}
		});
		return map;
	}
	
	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns");
		sql.append("where table_name = '" + tableName + "' and table_schema = (select database()) order by ordinal_position");
		return getJdbcTemplate().query(sql.toString(), new RowMapper<Map<String, String>>() {
			
			@Override
			public Map<String, String> mapRow(ResultSet rs, int index) throws SQLException {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("columnName", rs.getString("columnName"));
				map.put("dataType", rs.getString("dataType"));
				map.put("columnComment", rs.getString("columnComment"));
				map.put("columnKey", rs.getString("columnKey"));
				map.put("extra", rs.getString("extra"));
				return map;
			}
		});
	}
}
