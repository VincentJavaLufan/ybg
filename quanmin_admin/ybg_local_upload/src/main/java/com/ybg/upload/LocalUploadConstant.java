package com.ybg.upload;
import java.io.File;

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
}
