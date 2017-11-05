package com.ybg.upload;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author https://gitee.com/YYDeament/88ybg **/
public class LocalUploadConstant {
	
	/** 上传目录名称 **/
	public static final String	UPLOADFOLDER	= "upload";
	/** 存储的上传目录 **/
	public static final String	BASEPATH		= "." + File.separatorChar + UPLOADFOLDER + File.separatorChar;
	/** 转换http的url路径 **/
	public static final String	BASEURL			= "/" + UPLOADFOLDER + "/";
	
	/** 创建目录 **/
	public static void createdir(String uploadDir) {
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}
	
	/**************** 文件夹权限逻辑常量 ********************************************************/
	/** 无需审核的 **/
	public static final String				FLODER_PRIVILEGE_PUBLIC		= "public";
	/** 需要登录的 **/
	public static final String				FLODER_PRIVILEGE_LOGIN		= "login";
	/** 固定分组的 **/
	public static final String				FLODER_PRIVILEGE_GROUP		= "group";
	/** 只有自己能看的 **/
	public static final String				FLODER_PRIVILEGE_PRIVATE	= "private";
	/** 全新类型集合 **/
	public static final Map<String, String>	FLODER_PRIVILEGE_MAP		= new LinkedHashMap<>();
	static {
		FLODER_PRIVILEGE_MAP.put(FLODER_PRIVILEGE_PUBLIC, FLODER_PRIVILEGE_PUBLIC);
		FLODER_PRIVILEGE_MAP.put(FLODER_PRIVILEGE_LOGIN, FLODER_PRIVILEGE_LOGIN);
		FLODER_PRIVILEGE_MAP.put(FLODER_PRIVILEGE_GROUP, FLODER_PRIVILEGE_GROUP);
		FLODER_PRIVILEGE_MAP.put(FLODER_PRIVILEGE_PRIVATE, FLODER_PRIVILEGE_PRIVATE);
	}
}
