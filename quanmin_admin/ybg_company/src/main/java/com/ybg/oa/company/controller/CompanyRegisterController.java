package com.ybg.oa.company.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.oa.company.domain.CompanyRegisterVO;
import com.ybg.oa.company.service.CompanyRegisterService;
import com.ybg.oa.company.domain.CompanyRegisterDO;
import com.ybg.oa.company.qvo.CompanyRegisterQuery;
import springfox.documentation.annotations.ApiIgnore;

/** @author Deament
 * @email 591518884@qq.com
 * @date 2017-07-08 */
@Api(value="企业认证管理管理",tags="企业认证管理管理")
@Controller
@RequestMapping("/oa/companyregister_do/")
public class CompanyRegisterController {
	
	@Autowired
	private CompanyRegisterService companyRegisterService;
	
	@ApiOperation(value = "CompanyRegister管理页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map) {
		return "/system/companyRegister/index";
	}
	
	@ApiOperation(value = "CompanyRegister分页列表", notes = "JSON ", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "Integer"), @ApiImplicitParam(name = "qvo", value = "查询页数", required = false, dataType = "CompanyRegisterQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute CompanyRegisterQuery qvo,@ModelAttribute Page page, ModelMap map) throws Exception {
		qvo.setBlurred(true);
//		Page page = new Page();
//		page.setCurPage(pageNow);
		page = companyRegisterService.list(page, qvo);
		page.init();
		return page;
	}
	
	/** 新增初始化
	 * 
	 * @throws Exception **/
	@ApiOperation(value = "添加CompanyRegister页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) throws Exception {
		return "/system/companyRegister/toadd";
	}
	
	@ApiOperation(value = "更新CompanyRegister", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute CompanyRegisterVO companyRegister) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			updatemap.put("gmt_create", companyRegister.getGmtCreate());
			updatemap.put("gmt_modified", companyRegister.getGmtModified());
			updatemap.put("business", companyRegister.getBusiness());
			updatemap.put("companytype", companyRegister.getCompanytype());
			updatemap.put("credentialspic", companyRegister.getCredentialspic());
			updatemap.put("fullname", companyRegister.getFullname());
			updatemap.put("shortname", companyRegister.getShortname());
			updatemap.put("deal", companyRegister.getDeal());
			updatemap.put("dealresult", companyRegister.getDealresult());
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			wheremap.put("id", companyRegister.getId());
			companyRegisterService.update(updatemap, wheremap);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID删除companyRegister", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除companyRegister", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
				wheremap.put("id", id);
				companyRegisterService.remove(wheremap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "创建companyRegister", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@ModelAttribute CompanyRegisterVO bean) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			companyRegisterService.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "更新companyRegister页面初始化", notes = " ", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParam(name = "id", value = "companyRegister的ID", required = true, dataType = "java.lang.String")
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, ModelMap map) throws Exception {
		map.put("companyRegister", companyRegisterService.get(id));
		return "/system/companyRegister/edit";
	}
}
