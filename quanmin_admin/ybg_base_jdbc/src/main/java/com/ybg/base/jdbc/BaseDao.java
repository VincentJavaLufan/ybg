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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;

/*** * @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1<br>
 *       1.数据库所有逻辑主键使用 UUID方式 ，不要设置id为主键，设置它的主码索引就可以<br>
 *       2.数据库字段一律小写 <br>
 *       3.数据库字段不得使用下划线，表名可以。 <br>
 ***/
public abstract class BaseDao extends BaseSql {
	
	/** 新增 只适用于UUID
	 * 
	 * @param createmap
	 *            新增的map key 为数据库的列，value 为数据库列的值,表中一定要有 gmt_modified,gmt_create 两列，都为dataTime类型
	 * 
	 *            <br>
	 *            createmap 为空则过滤掉该列，空map传递 则取消指向。
	 * @return 返回 UUID主键
	 * @throws Exception
	 **/
	@SuppressWarnings("unused")
	public String baseCreate(BaseMap<String, Object> createmap, String tableName, String idName) throws Exception {
		UUID uuid = UUID.randomUUID();
		//加上时间戳，因为在大并发下 还是会重复。
		String id = uuid.toString().replaceAll("-", "")+System.currentTimeMillis();
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT).append(INTO).append(tableName).append("(");
		if (createmap != null && createmap.size() > 0) {
			if (idName != null && idName.trim().length() > 0) {
				createmap.put(idName, id);
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
			
			@Override
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
	
	/** 新增数据（单个） 不推荐使用 适用于int long 类型的主键的表
	 * 
	 * @param createmap
	 *            新增的map key 为数据库的列，value 为数据库列的值,表中一定要有 gmt_modified,gmt_create 两列，都为dataTime类型
	 * 
	 *            <br>
	 *            createmap 为空则过滤掉该列，空map传递 则取消指向。
	 * @param 表名称
	 * @return id 是否返回id
	 * @param T
	 *            返回的类型
	 * @return 主键
	 * @throws Exception
	 **/
	@SuppressWarnings("unused")
	@Deprecated
	public Object basecreate(final Map<String, Object> createmap, String tableName, boolean returnid, Object idtype) throws Exception {
		final BaseMap<String, Object> basemap = new BaseMap<String, Object>();
		for (Entry<String, Object> entry : createmap.entrySet()) {
			basemap.put(entry.getKey(), entry.getValue());
		}
		final StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO " + tableName + "(");
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
					PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
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
	/** 新增或增加
	 * 
	 * @param createmap
	 *            新增的map key 为数据库的列，value 为数据库列的值,表中一定要有 gmt_modified,gmt_create 两列，都为dataTime类型
	 * 
	 *            <br>
	 *            createmap 为空则过滤掉该列，空map传递 则取消指向。
	 * @param tableName
	 *            表名称
	 * @param idName
	 *            主键列名称
	 * @param unionkey
	 *            主码 ，该表的唯一唯一索引(必须有) 若是新增 则返回ID **/
	@SuppressWarnings("unused")
	public String saveOrUpdate(BaseMap<String, Object> createmap, String tableName, String idName, String[] unionkey) throws Exception {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replaceAll("-", "");
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT).append(INTO).append(tableName).append("(");
		if (createmap != null && createmap.size() > 0) {
			if (idName != null && idName.trim().length() > 0) {
				createmap.put(idName, id);
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
		updatemap.remove(idName);
		if (updatemap != null && updatemap.size() > 0) {
			for (Entry<String, Object> entry : updatemap.entrySet()) {
				sql.append(entry.getKey() + "=?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
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
	 * @param tableName
	 *            表的名称 **/
	public int baseupdate(final BaseMap<String, Object> updatemap, final BaseMap<String, Object> wheremap, String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE + tableName + SET);
		if (updatemap != null && updatemap.size() > 0) {
			updatemap.put("gmt_modified", DateUtil.getDateTime());
			for (Entry<String, Object> entry : updatemap.entrySet()) {
				sql.append(entry.getKey() + "=?,");
			}
			sql.replace(sql.length() - 1, sql.length(), "");
		}
		else {
			return 0;
		}
		sql.append(WHERE + " 1=1 ");
		if (wheremap != null && wheremap.size() > 0) {
			for (Entry<String, Object> entry : wheremap.entrySet()) {
				sql.append(AND + entry.getKey() + "=?");
			}
		}
		else {
			return 0;
		}
		return getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
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
	 * @param tableName
	 *            表的名称 **/
	public int baseremove(final BaseMap<String, Object> conditionmap, String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE + FROM + tableName + WHERE + " 1=1 ");
		if (conditionmap == null || conditionmap.size() == 0) {
			return 0;
		}
		for (Entry<String, Object> entry : conditionmap.entrySet()) {
			sql.append(AND + entry.getKey() + "=? ");
		}
		return getJdbcTemplate().update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
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
	 *            是否模糊查询（禁止左模糊）
	 * @param name
	 *            列的具体值
	 * @throws Exception
	 ***/
	public String equalsorlike(boolean isblureed, String name) throws Exception {
		if (checkSQLinject(name)) {
			// 存在sql 注入
			throw new Exception();
		}
		if (isblureed) {
			return LIKE + " '" + name + "%'";
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
	 * @return sql.append( and name=/like 'value'/'%value%')
	 * @throws Exception
	 ***/
	public void sqlappen(StringBuilder sql, String name, String value, BaseQueryAble qvo) throws Exception {
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
	 * @return sql.append( and name=/like 'value'/'%value%')
	 * @throws Exception
	 ***/
	public void sqlappen(StringBuilder sql, String name, String value, boolean blurred) throws Exception {
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
	 * @return sql.append( and name= 'value')
	 * @throws Exception
	 ***/
	public void sqlappen(StringBuilder sql, String name, String value) throws Exception {
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
	 * @return sql.append( and name= 'value')
	 * @throws Exception
	 ***/
	public void sqlappen(StringBuilder sql, String name, Long value) throws Exception {
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
		int maxNum = 2;
		if (QvoConditionUtil.checkObjectArray(array, maxNum)) {
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
	 * @param start
	 *            小于改条件
	 * @param end
	 *            大于改条件
	 * @return 如果Start不为空，sql.append( and name< Start)如果End 不为空，sql.append( and name> End) ***/
	public void sqlappen(StringBuilder sql, String name, Object start, Object end) {
		if (start instanceof String && QvoConditionUtil.checkString((String) start)) {
			sql.append(AND).append(name + "<").append("'").append((String) start).append("'");
		}
		if (end instanceof String && QvoConditionUtil.checkString((String) end)) {
			sql.append(AND).append(name + ">").append("'").append((String) end).append("'");
		}
		if (start instanceof Integer && QvoConditionUtil.checkInteger((Integer) start)) {
			sql.append(AND).append(name).append("<" + "'").append((Integer) start).append("'");
		}
		if (end instanceof Integer && QvoConditionUtil.checkInteger((Integer) end)) {
			sql.append(AND).append(name).append(">").append("'").append((Integer) end).append("'");
		}
		if (start instanceof Float && QvoConditionUtil.checkFloat((Float) start)) {
			sql.append(AND).append(name).append("<").append("'").append((Float) start).append("'");
		}
		if (end instanceof Float && QvoConditionUtil.checkFloat((Float) end)) {
			sql.append(AND).append(name + ">").append("'").append((Float) end).append("'");
		}
	}
	
	/** 用于统计个数 一般用于分页，或者直接得出总记录数 **/
	public int queryForInt(StringBuilder sql) {
		return getJdbcTemplate().queryForObject(new Page().getCountsql(sql), Integer.class);
	}
	
	/** 获取模板方法
	 * 
	 * @return JdbcTemplate **/
	public abstract JdbcTemplate getJdbcTemplate();
	
	/****/
	/** @param sqlParm
	 *            字段
	 * @return 真， 存在sql 注入 */
	private boolean checkSQLinject(String sqlParm) {
		if (sqlParm == null) {
			return false;
		}
		// 统一转为小写
		sqlParm = sqlParm.toLowerCase();
		// 过滤掉的sql关键字，可以手动添加
		String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" + "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" + "table|from|grant|use|group_concat|column_name|" + "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" + "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (sqlParm.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}
}
