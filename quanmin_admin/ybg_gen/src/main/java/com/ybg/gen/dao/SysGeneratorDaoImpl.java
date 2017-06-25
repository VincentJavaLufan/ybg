package com.ybg.gen.dao;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;

@Repository
public class SysGeneratorDaoImpl extends BaseDao implements SysGeneratorDao {
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables ");
		sql.append(" 	where table_schema = (select database())");
		return null;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Map<String, String> queryTable(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
}
