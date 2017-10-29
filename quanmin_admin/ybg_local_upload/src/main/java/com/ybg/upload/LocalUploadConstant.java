package com.ybg.upload;
import java.io.File;

/** @author https://gitee.com/YYDeament/88ybg **/
public class LocalUploadConstant {
	
	/** 基本上传目录 **/
	public static final String BASEPATH = "./upload/";
	
	/** 创建目录 **/
	public static void createdir(String uploadDir) {
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}
}
