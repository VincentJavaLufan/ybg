package com.ybg.upload.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.upload.LocalUploadConstant;
import com.ybg.upload.support.NameComparator;
import com.ybg.upload.support.SizeComparator;
import com.ybg.upload.support.TypeComparator;
import io.swagger.annotations.Api;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/** @author Deament
 * @Date 2017年11月2日22:46:41 */
@Api(tags = "富文本编辑器")
@Controller
public class KindeditorController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping("/kindeditor/index.do")
	public String index() {
		return "/demo/kindeditor";
	}
	
	/** KindEditor JSP
	 * 
	 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
	 * 
	 * @param privilege
	 *            权限 分为public private group ...
	 * @param user
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/kindeditor/{privilege}/upload_json.do", method = RequestMethod.POST)
	public void uploadFile(@PathVariable String privilege, @AuthenticationPrincipal UserVO user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		if (LocalUploadConstant.FLODER_PRIVILEGE_MAP.get(privilege) == null) {
			writer.println(getError("no PRIVILEGE!无权限"));
			return;
		}
		try {
			// 文件保存目录路径
			String savePath = LocalUploadConstant.BASEPATH + "kindeditor" + File.separatorChar + "products" + File.separatorChar + user.getId() + File.separatorChar + privilege + File.separatorChar;
			String saveUrl = LocalUploadConstant.BASEURL + "kindeditor" + "/" + "products" + "/" + user.getId() + "/" + privilege + "/";
			// 定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			// 最大文件大小
			long maxSize = 1000000;
			response.setContentType("text/html; charset=UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				writer.println(getError("请选择文件。"));
				return;
			}
			File uploadDir = new File(savePath);
			// 判断文件夹是否存在,如果不存在则创建文件夹
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				writer.println(getError("上传目录没有写权限。"));
				return;
			}
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if (!extMap.containsKey(dirName)) {
				writer.println(getError("目录名不正确。"));
				return;
			}
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			String fileName = null;
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, MultipartFile> entry = it.next();
				MultipartFile mFile = entry.getValue();
				fileName = mFile.getOriginalFilename();
				// 检查文件大小
				if (mFile.getSize() > maxSize) {
					writer.println(getError("上传文件大小超过限制。"));
					return;
				}
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				}
				UUID uuid = UUID.randomUUID();
				String path = savePath + uuid.toString() + "." + fileExt;
				saveUrl = saveUrl + uuid.toString() + "." + fileExt;
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
				FileCopyUtils.copy(mFile.getInputStream(), outputStream);
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl);
				writer.println(obj.toString());
			}
		} catch (Exception serviceException) {
			writer.println(getError("未知错误"));
			return;
		}
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
	
	/** KindEditor JSP
	 *
	 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。 */
	/** 文件管理
	 * 
	 * @throws IOException
	 **/
	@RequestMapping("/kindeditor/file_manager_json.do")
	public void file_manager_json(@AuthenticationPrincipal UserVO user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		if (user == null || user.getId() == null) {
			writer.println("non-privileged.");
			return;
		}
		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		// String rootPath = pageContext.getServletContext().getRealPath("/") + "attached/";
		String rootPath = LocalUploadConstant.BASEPATH + "kindeditor" + File.separatorChar + "products" + File.separatorChar + user.getId() + File.separatorChar;
		String rootUrl = LocalUploadConstant.BASEURL + "kindeditor" + "/" + "products" + "/" + user.getId() + "/";
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		// String dirName = request.getParameter("dir");
		// if (dirName != null) {
		// if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
		// // out.println("Invalid Directory name.");
		// writer.println("Invalid Directory name.");
		// return;
		// }
		// rootPath += dirName + "/";
		// rootUrl += dirName + "/";
		// File saveDirFile = new File(rootPath);
		// if (!saveDirFile.exists()) {
		// saveDirFile.mkdirs();
		// }
		// }
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}
		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			writer.println("Access is not allowed.");
			return;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			writer.println("Parameter is not valid.");
			return;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			writer.println("Directory does not exist.");
			return;
		}
		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				}
				else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		}
		else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		}
		else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		// response.setContentType("application/json; charset=UTF-8");
		writer.println(result.toJSONString());
	}
	
	/** 上传回调方法 **/
	@RequestMapping(method = RequestMethod.GET, value = "/upload/kindeditor/products/{userid}/{privilege}/{date}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String privilege, @PathVariable String userid, @PathVariable String date, @PathVariable String filename) {
		try {
			// TODO 此处加上 判断 privilege的逻辑。。。。
			return ResponseEntity.ok(resourceLoader.getResource("file:" + "./upload/kindeditor/products/" + "/" + userid + "/" + privilege + "/" + date + "/" + filename));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	/** 文件管理 **/
}
