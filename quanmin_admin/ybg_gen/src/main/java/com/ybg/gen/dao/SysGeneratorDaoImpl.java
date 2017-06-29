package com.ybg.gen.dao;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.util.Page;
import com.ybg.gen.qvo.GeneratorQuery;

@Repository
public class SysGeneratorDaoImpl extends BaseDao implements SysGeneratorDao {
	
	@Override
	public Page list(Page page, GeneratorQuery qvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append(" 	where table_schema = (select database())");
		return null;
	}
	
	@Override
	public Map<String, String> queryTable(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append("where table_schema = (select database()) and table_name = '" + tableName + "'");
		Map<String, String> map= new LinkedHashMap<String, String>();
		getJdbcTemplate().queryForMap(sql.toString());
		return map;
	}
	
	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns");
		sql.append("where table_name = '" + tableName + "' and table_schema = (select database()) order by ordinal_position");
		Map<String, String> map= new LinkedHashMap<String, String>();
		getJdbcTemplate().queryForMap(sql.toString());
		return map;
	}
}
