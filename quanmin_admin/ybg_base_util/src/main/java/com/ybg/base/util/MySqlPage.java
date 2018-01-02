package com.ybg.base.util;
import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;

/** Mysql 分页类
 * 
 * @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0 */
public class MySqlPage extends AbstractPage {
	
	/** 自动生成分页语句 **/
	@Override
	public String getPagesql(StringBuilder sql) {
		int startindex = 0;
		if (super.getCurPage() != 1) {
			startindex = (super.getCurPage() - 1) * super.getPageSize();
		}
		return PagerUtils.limit(sql.toString(), JdbcConstants.MYSQL, startindex, getPageSize() * super.getCurPage());
	}
	
	/** 自动统计总页数 **/
	@Override
	public String getCountsql(StringBuilder sql) {
		return PagerUtils.count(sql.toString(), JdbcConstants.MYSQL);
	}
}
