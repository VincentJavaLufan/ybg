package com.ybg.base.jdbc;
/** * @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1<br>
 * 		数据库常量 **/
public class DataBaseConstant {
	
	/** 数据源-系统管理 (默认) **/
	public static final String	DB_SYS		= "dataSourceSys";
	/** 数据源-企业管理 **/
	public static final String	DB_OA		= "dataSourceOa";
	/** 数据源-教育管理 **/
	public static final String	DB_EDU		= "dataSourceEdu";
	/** 数据源-定时任务框架 **/
	public static final String	DB_QUARTZ	= "dataSourceQuartz";
	// 分隔线-----------------------------------------------
	/** JDBC模板方法-使用系统数据库 (默认) **/
	public static final String	JD_SYS		= "sysJdbcTemplate";
	/** JDBC模板方法-使用OA数据库- **/
	public static final String	JD_OA		= "oaJdbcTemplate";
	/** JDBC模板方法-使用教育数据库 **/
	public static final String	JD_EDU		= "eduJdbcTemplate";
	/** JDBC模板方法-使用定时任务数据库 **/
	public static final String	JD_QUARTZ	= "quartzJdbcTemplate";
	// 分割线
	/**** 事务 *********/
	public static final String	TM_SYS		= "sysTransactionManager";
	/** 数据库事务-使用OA数据库- **/
	public static final String	TM_OA		= "oaTransactionManager";
	/** 数据库事务-使用教育数据库 **/
	public static final String	TM_EDU		= "eduTransactionManager";
	/** 数据库事务-使用定时任务数据库 **/
	public static final String	TM_QUARTZ	= "quartzTransactionManager";
}
