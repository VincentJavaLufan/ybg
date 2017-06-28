package com.ybg.base.jdbc;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;

/*** 1.数据库所有逻辑主键使用 UUID方式 ，不要设置id为主键，设置它的主码索引就可以<br>
 * 2.数据库字段一律小写 <br>
 * 3.数据库字段不得使用下划线，表名可以。 <br>
***/
@ComponentScan
public class BaseDao extends BaseSQL {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/** 新增
	 * 
	 * @throws Exception **/
	@SuppressWarnings("unused")
	public String baseCreate(BaseMap<String, Object> createmap, String table_name, String id_name) throws Exception {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replaceAll("-", "");
		StringBuilder sql = new StringBuilder();
		
		sql.append(INSERT).append(INTO).append(table_name).append("(");
		
		if (createmap != null && createmap.size() > 0) {
			if (id_name != null && id_name.trim().length() > 0) {
				createmap.put(id_name, id);
			}
			createmap.put("gmt_modified", DateUtil.getDateTime());
			createmap.put("gmt_create", DateUtil.getDateTime());
			for (Entry<String, Object> entry : createmap.entrySet()) {
				sql.append(entry.getKey()).append(",");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		else {
			throw new Exception();
		}
		sql.append("  )");
		sql.append(VALUES).append(" (");
		if (createmap != null && createmap.size() > 0) {
			for (Entry<String, Object> entry : createmap.entrySet()) {
				sql.append("?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		sql.append("  )");
		getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				int count = 1;
				for (Entry<String, Object> entry : createmap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// count++;// 需要新的数据类型自己添加
					// }
				}
			}
		});
		return id;
	}
	
	/** 新增数据（单个） 不推荐使用
	 * 
	 * @param createmap
	 *            数据集合
	 * @param 表名称
	 * @return id 是否返回id
	 * @param T
	 *            返回的类型
	 * @throws Exception **/
	@SuppressWarnings("unused")
	@Deprecated
	public Object basecreate(final Map<String, Object> createmap, String table_name, boolean returnid, Object idtype) throws Exception {
		final BaseMap<String, Object> basemap = new BaseMap<String, Object>();
		for (Entry<String, Object> entry : createmap.entrySet()) {
			basemap.put(entry.getKey(), entry.getValue());
		}
		final StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO " + table_name + "(");
		if (basemap != null && basemap.size() > 0) {
			for (Entry<String, Object> entry : basemap.entrySet()) {
				sql.append(entry.getKey() + ",");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		else {
			throw new Exception();
		}
		sql.append("  )");
		sql.append(" VALUES (");
		if (basemap != null && basemap.size() > 0) {
			for (Entry<String, Object> entry : basemap.entrySet()) {
				sql.append("?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		sql.append("  )");
		if (returnid) {
			KeyHolder idkey = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString());
					int count = 1;
					for (Entry<String, Object> entry : basemap.entrySet()) {
						if (entry.getValue() instanceof Integer) {
							ps.setInt(count++, (Integer) entry.getValue());
						}
						else if (entry.getValue() instanceof String) {
							ps.setString(count++, (String) entry.getValue());
						}
						else if (entry.getValue() instanceof Float) {
							ps.setFloat(count++, (Float) entry.getValue());
						}
						else if (entry.getValue() instanceof Blob) {
							ps.setBlob(count++, (Blob) entry.getValue());
						}
						else if (entry.getValue() instanceof Boolean) {
							ps.setBoolean(count++, (Boolean) entry.getValue());
						}
						else if (entry.getValue() instanceof Byte) {
							ps.setByte(count++, (Byte) entry.getValue());
						}
						else if (entry.getValue() instanceof Short) {
							ps.setShort(count++, (Short) entry.getValue());
						}
						else if (entry.getValue() instanceof Long) {
							ps.setLong(count++, (Long) entry.getValue());
						}
						else if (entry.getValue() instanceof Double) {
							ps.setDouble(count++, (Double) entry.getValue());
						}
						else if (entry.getValue() instanceof BigDecimal) {
							ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
						}
						else if (entry.getValue() instanceof java.sql.Date) {
							ps.setDate(count++, (java.sql.Date) entry.getValue());
						}
						else if (entry.getValue() instanceof Time) {
							ps.setTime(count++, (Time) entry.getValue());
						}
						else if (entry.getValue() instanceof Timestamp) {
							ps.setTimestamp(count++, (Timestamp) entry.getValue());
						}
						// else {
						// // count++;// 需要新的数据类型自己添加
						// }
					}
					return ps;
				}
			}, idkey);
			if (idtype instanceof Integer) {
				return idkey.getKey().intValue();
			}
			else if (idtype instanceof Long) {
				return idkey.getKey().longValue();
			}
			else {
				return idkey.getKey().toString();
			}
		}
		else {
			getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					int count = 1;
					for (Entry<String, Object> entry : basemap.entrySet()) {
						if (entry.getValue() instanceof Integer) {
							ps.setInt(count++, (Integer) entry.getValue());
						}
						else if (entry.getValue() instanceof String) {
							ps.setString(count++, (String) entry.getValue());
						}
						else if (entry.getValue() instanceof Float) {
							ps.setFloat(count++, (Float) entry.getValue());
						}
						else if (entry.getValue() instanceof Blob) {
							ps.setBlob(count++, (Blob) entry.getValue());
						}
						else if (entry.getValue() instanceof Boolean) {
							ps.setBoolean(count++, (Boolean) entry.getValue());
						}
						else if (entry.getValue() instanceof Byte) {
							ps.setByte(count++, (Byte) entry.getValue());
						}
						else if (entry.getValue() instanceof Short) {
							ps.setShort(count++, (Short) entry.getValue());
						}
						else if (entry.getValue() instanceof Long) {
							ps.setLong(count++, (Long) entry.getValue());
						}
						else if (entry.getValue() instanceof Double) {
							ps.setDouble(count++, (Double) entry.getValue());
						}
						else if (entry.getValue() instanceof BigDecimal) {
							ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
						}
						else if (entry.getValue() instanceof java.sql.Date) {
							ps.setDate(count++, (java.sql.Date) entry.getValue());
						}
						else if (entry.getValue() instanceof Time) {
							ps.setTime(count++, (Time) entry.getValue());
						}
						else if (entry.getValue() instanceof Timestamp) {
							ps.setTimestamp(count++, (Timestamp) entry.getValue());
						}
						// else {
						// // count++;// 需要新的数据类型自己添加
						// }
					}
				}
			});
		}
		return null;
	}
	
	// 无法确定更新还是增加。这个方法需要提供 主码map
	@SuppressWarnings("unused")
	public String saveOrUpdate(BaseMap<String, Object> createmap, String table_name, String id_name, String[] unionkey) throws Exception {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replaceAll("-", "");
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT).append(INTO).append(table_name).append("(");
		if (createmap != null && createmap.size() > 0) {
			if (id_name != null && id_name.trim().length() > 0) {
				createmap.put(id_name, id);
			}
			createmap.put("gmt_modified", DateUtil.getDateTime());
			createmap.put("gmt_create", DateUtil.getDateTime());
			for (Entry<String, Object> entry : createmap.entrySet()) {
				sql.append(entry.getKey() + ",");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		else {
			throw new Exception();
		}
		sql.append("  )");
		sql.append(VALUES).append(" (");
		if (createmap != null && createmap.size() > 0) {
			for (Entry<String, Object> entry : createmap.entrySet()) {
				sql.append("?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		sql.append("  )");
		if (unionkey != null) {
			sql.append(ON).append(DUPLICATE).append(KEY).append(UPDATE);
		}
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		updatemap.putAll(createmap);
		for (String u : unionkey) {
			updatemap.remove(u);
		}
		updatemap.remove("gmt_create");
		updatemap.remove(id_name);
		if (updatemap != null && updatemap.size() > 0) {
			for (Entry<String, Object> entry : updatemap.entrySet()) {
				sql.append(entry.getKey() + "=?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				int count = 1;
				for (Entry<String, Object> entry : createmap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// // count++;// 需要新的数据类型自己添加
					// }
				}
				for (Entry<String, Object> entry : updatemap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// // count++;// 需要新的数据类型自己添加
					// }
				}
			}
		});
		return id;
	}
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	public void baseupdate(final BaseMap<String, Object> updatemap, final BaseMap<String, Object> wheremap, String table_name) {
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE + table_name + SET);
		if (updatemap != null && updatemap.size() > 0) {
			updatemap.put("gmt_modified", DateUtil.getDateTime());
			
			
			for (Entry<String, Object> entry : updatemap.entrySet()) {
				sql.append(entry.getKey() + "=?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		else {
			return;
		}
		sql.append(WHERE + " 1=1 ");
		if (wheremap != null && wheremap.size() > 0) {
			for (Entry<String, Object> entry : wheremap.entrySet()) {
				sql.append(AND + entry.getKey() + "=?");
			}
		}
		else {
			return;
		}
		getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				int count = 1;
				for (Entry<String, Object> entry : updatemap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// // count++;// 需要新的数据类型自己添加
					// }
				}
				for (Entry<String, Object> entry : wheremap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// // count++;// 需要新的数据类型自己添加
					// }
				}
			}
		});
	}
	
	/** 删除 但是不允许全表删除 条件不允许为空
	 * 
	 * @author Deament
	 * @param conditionmap
	 *            删除的字段和值
	 * 
	 * @param table_name
	 *            表的名称 **/
	public void baseremove(final BaseMap<String, Object> conditionmap, String table_name) {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE + FROM + table_name + WHERE + " 1=1 ");
		if (conditionmap == null || conditionmap.size() == 0) {
			return;
		}
		for (Entry<String, Object> entry : conditionmap.entrySet()) {
			sql.append(AND + entry.getKey() + "=? ");
		}
		getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				int count = 1;
				for (Entry<String, Object> entry : conditionmap.entrySet()) {
					if (entry.getValue() instanceof Integer) {
						ps.setInt(count++, (Integer) entry.getValue());
					}
					else if (entry.getValue() instanceof String) {
						ps.setString(count++, (String) entry.getValue());
					}
					else if (entry.getValue() instanceof Float) {
						ps.setFloat(count++, (Float) entry.getValue());
					}
					else if (entry.getValue() instanceof Blob) {
						ps.setBlob(count++, (Blob) entry.getValue());
					}
					else if (entry.getValue() instanceof Boolean) {
						ps.setBoolean(count++, (Boolean) entry.getValue());
					}
					else if (entry.getValue() instanceof Byte) {
						ps.setByte(count++, (Byte) entry.getValue());
					}
					else if (entry.getValue() instanceof Short) {
						ps.setShort(count++, (Short) entry.getValue());
					}
					else if (entry.getValue() instanceof Long) {
						ps.setLong(count++, (Long) entry.getValue());
					}
					else if (entry.getValue() instanceof Double) {
						ps.setDouble(count++, (Double) entry.getValue());
					}
					else if (entry.getValue() instanceof BigDecimal) {
						ps.setBigDecimal(count++, (BigDecimal) entry.getValue());
					}
					else if (entry.getValue() instanceof java.sql.Date) {
						ps.setDate(count++, (java.sql.Date) entry.getValue());
					}
					else if (entry.getValue() instanceof Time) {
						ps.setTime(count++, (Time) entry.getValue());
					}
					else if (entry.getValue() instanceof Timestamp) {
						ps.setTimestamp(count++, (Timestamp) entry.getValue());
					}
					// else {
					// // count++;// 需要新的数据类型自己添加
					// }
				}
			}
		});
	}
	
	/*** 模糊查询语句 或者精确查询
	 * 
	 * @param isblureed
	 *            是否模糊查询
	 * @param name
	 *            列的具体值 ***/
	public String equalsorlike(boolean isblureed, String name) {
		if (isblureed) {
			return LIKE + " '%" + name + "%'";
		}
		else {
			return " = '" + name + "'";
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * @param isblureed
	 *            是否模糊查询
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * @param qvo
	 *            查看接口的方法 是否属于模糊查询
	 * @return sql.append( and name=/like 'value'/'%value%') ***/
	public void sqlappen(StringBuilder sql, String name, String value, BaseQueryAble qvo) {
		sqlappen(sql, name, value, qvo.isBlurred());
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * @param isblureed
	 *            是否模糊查询
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * @param qvo
	 *            查看接口的方法 是否属于模糊查询
	 * @return sql.append( and name=/like 'value'/'%value%') ***/
	public void sqlappen(StringBuilder sql, String name, String value, boolean blurred) {
		if (QvoConditionUtil.checkString(value)) {
			sql.append(AND).append(name).append(equalsorlike(blurred, value));
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * 
	 * @return sql.append( and name= 'value') ***/
	public void sqlappen(StringBuilder sql, String name, String value) {
		if (QvoConditionUtil.checkString(value)) {
			sql.append(AND).append(name).append(equalsorlike(false, value));
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * 
	 * @return sql.append( and name= 'value') ***/
	public void sqlappen(StringBuilder sql, String name, Double value) {
		if (QvoConditionUtil.checkDouble(value)) {
			sql.append(AND).append(name).append("=").append(value);
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * 
	 * @return sql.append( and name= 'value') ***/
	public void sqlappen(StringBuilder sql, String name, Long value) {
		if (QvoConditionUtil.checkLong(value)) {
			sql.append(AND).append(name).append(equalsorlike(false, value.toString()));
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * @return sql.append(" and " + name + "=" + value); ***/
	public void sqlappen(StringBuilder sql, String name, Integer value) {
		if (QvoConditionUtil.checkInteger(value)) {
			sql.append(AND).append(name).append("=").append(value);
		}
	}
	
	/*** 字符串语句拼接
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param value
	 *            列的值
	 * @return sql.append(" and " + name + "=" + value); ***/
	public void sqlappen(StringBuilder sql, String name, Float value) {
		if (QvoConditionUtil.checkFloat(value)) {
			sql.append(AND).append(name).append("=" + value);
		}
	}
	
	/** 字符串语句拼接- 范围查询
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param object[]
	 *            查询的范围 大于object[0],小于object[1] 长度必须是2
	 * @return sql.append(" and " + name + ">" + object[0]+" and " + name + "<" + object[1] ); ***/
	public void sqlappen(StringBuilder sql, String name, Object[] array) {
		if (QvoConditionUtil.checkObjectArray(array, 2)) {
			sql.append(AND).append(name).append("<").append("'").append(array[0]).append("'");
			sql.append(AND).append(name).append(">").append("'").append(array[1]).append("'");
		}
	}
	
	/*** 字符串语句拼接- 范围查询2
	 * 
	 * @param sql
	 *            操作的sql对象
	 * 
	 * @param name
	 *            列的名称
	 * @param Start
	 *            小于改条件
	 * @param End
	 *            大于改条件
	 * @return 如果Start不为空，sql.append( and name< Start)如果End 不为空，sql.append( and name> End) ***/
	public void sqlappen(StringBuilder sql, String name, Object Start, Object End) {
		if (Start instanceof String && QvoConditionUtil.checkString((String) Start)) {
			sql.append(AND).append(name + "<").append("'").append((String) Start).append("'");
		}
		if (End instanceof String && QvoConditionUtil.checkString((String) End)) {
			sql.append(AND).append(name + ">").append("'").append((String) End).append("'");
		}
		if (Start instanceof Integer && QvoConditionUtil.checkInteger((Integer) Start)) {
			sql.append(AND).append(name).append("<" + "'").append((Integer) Start).append("'");
		}
		if (End instanceof Integer && QvoConditionUtil.checkInteger((Integer) End)) {
			sql.append(AND).append(name).append(">").append("'").append((Integer) End).append("'");
		}
		if (Start instanceof Float && QvoConditionUtil.checkFloat((Float) Start)) {
			sql.append(AND).append(name).append("<").append("'").append((Float) Start).append("'");
		}
		if (End instanceof Float && QvoConditionUtil.checkFloat((Float) End)) {
			sql.append(AND).append(name + ">").append("'").append((Float) End).append("'");
		}
	}
	
	/** 用于统计个数 一般用于分页，或者直接得出总记录数 **/
	public int queryForInt(StringBuilder sql) {
		return getJdbcTemplate().queryForObject(new Page().getCountsql(sql), Integer.class);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
