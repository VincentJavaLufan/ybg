package com.ybg.gen.controller;
import com.alibaba.fastjson.JSON;
import com.ybg.base.util.Page;
import com.ybg.gen.qvo.GeneratorQuery;
import com.ybg.gen.service.SysGeneratorService;
import com.ybg.gen.utils.xss.XssHttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58 */
@Controller
@RequestMapping("/sys/generator_do/")
public class SysGeneratorController {
	
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	@RequestMapping(value = "index.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/gen/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute GeneratorQuery qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, ModelMap map) throws Exception {
		Page page = new Page();
		page.setCurPage(pageNow);
		page = sysGeneratorService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 生成代码 */
	@RequestMapping("code.do")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] tableNames = new String[] {};
		// 获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		String tables = orgRequest.getParameter("tables");
		tableNames = tables.split(",");
		byte[] data = sysGeneratorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}
}
