package com.ybg.upload.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
//	@RequestMapping(value = "/testDownload", method = RequestMethod.GET)
//	public void testDownload(HttpServletRequest request,HttpServletResponse res) {
//		String fileName = request.getParameter("filename");
//		res.setHeader("content-type", "application/octet-stream");
//		res.setContentType("application/octet-stream");
//		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//		byte[] buff = new byte[1024];
//		BufferedInputStream bis = null;
//		OutputStream os = null;
//		try {
//			os = res.getOutputStream();
//			bis = new BufferedInputStream(new FileInputStream(new File(LocalUploadConstant.BASEPATH + fileName)));
//			int i = bis.read(buff);
//			while (i != -1) {
//				os.write(buff, 0, buff.length);
//			
//				i = bis.read(buff);
//			}
//			os.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		System.out.println("success");
//	}
	 //文件下载相关代码
    @RequestMapping("/testDownload")
    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response){
    	String fileName = request.getParameter("filename");
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = LocalUploadConstant.BASEPATH;
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}