package com.ybg.base.jdbc;
/** 数据库常量 **/
public class DataBaseConstant {
	
	/** 数据源-系统管理 (默认)**/
	public static final String	DB_SYS	= "dataSourceSys";
	/** 数据源-企业管理 **/
	public static final String	DB_OA	= "dataSourceOa";
	/** 数据源-教育管理 **/
	public static final String	DB_EDU	= "dataSourceEdu";
	// 分隔线-----------------------------------------------
	/** JDBC模板方法-使用系统数据库  (默认)**/
	public static final String	JD_SYS	= "sysJdbcTemplate";
	/** JDBC模板方法-使用OA数据库- **/
	public static final String	JD_OA	= "oaJdbcTemplate";
	/** JDBC模板方法-使用教育数据库 **/
	public static final String	JD_EDU	= "eduJdbcTemplate";
}
