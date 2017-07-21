package com.ybg.base.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** 系统常量类 **/
public class SystemConstant {
	
	/** 是否调试模式 **/
	private static Properties	props	= new Properties();
	public static final boolean	DEBUG	= false;
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("systemconstant.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	/** 系统域名 www.???.com **/
	public static String getSystemdomain() {
		if (DEBUG) {
			return "localhost:8080";
		}
		return getValue("systemdomain");
	}
	
	/** 系统名称 **/
	public static String getSystemName() {
		return getValue("systemname");
	}
	
	/** 系统配置邮箱 **/
	public static String getEmailAdress() {
		return getValue("systememail");
	}
	
	/** 系统配置邮箱密码 **/
	public static String getEmailPwd() {
		return getValue("systememailpwd");
	}
	
	/** 系统归属人 **/
	public static String getSystemAuth() {
		return getValue("systemauth");
	}
	
	/** 系统备案号 **/
	public static String getICP() {
		return getValue("systemicp");
	}
	
	private SystemConstant() {
		// 禁止实例化
	}
}
