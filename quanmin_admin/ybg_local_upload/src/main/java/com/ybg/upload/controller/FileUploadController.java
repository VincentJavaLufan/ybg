package com.ybg.upload.controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ybg.upload.LocalUploadConstant;

/** 文件上传的Demo Controller
 * 
 * @author 单红宇(CSDN CATOOP)
 * @create 2017年3月11日 */
@Controller
public class FileUploadController {
	
	// 访问路径为：http://ip:port/upload
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "/fileupload";
	}
	
	// 访问路径为：http://ip:port/upload/batch
	@RequestMapping(value = "/upload/batch", method = RequestMethod.GET)
	public String batchUpload() {
		return "/mutifileupload";
	}
	
	/** 文件上传具体实现方法（单文件上传）
	 *
	 * @param file
	 * @return
	 * 
	 * @author 单红宇(CSDN CATOOP)
	 * @create 2017年3月11日 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				// 这里只是简单例子，文件直接输出到项目路径下。
				// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
				// 还有关于文件格式限制、文件大小限制，详见：中配置。
				String uploadDir = LocalUploadConstant.BASEPATH;
				// 如果目录不存在，自动创建文件夹
				LocalUploadConstant.createdir(uploadDir);
				// 文件后缀名
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				// 上传文件名
				String filename = UUID.randomUUID() + suffix;
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(LocalUploadConstant.BASEPATH + new File(filename)));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return "上传成功";
		}
		else {
			return "上传失败，因为文件是空的.";
		}
	}
	
	/** 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
	 *
	 * @param request
	 * @return
	 * 
	 * @author 单红宇(CSDN CATOOP)
	 * @create 2017年3月11日 */
	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		LocalUploadConstant.createdir(LocalUploadConstant.BASEPATH);
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(LocalUploadConstant.BASEPATH + new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			}
			else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}
}