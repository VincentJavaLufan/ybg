package com.ybg.rbac.controllor;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
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
import springfox.documentation.annotations.ApiIgnore;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.base.util.excel.ExcelUtil;
import com.ybg.rbac.role.qvo.RoleQvo;
import com.ybg.rbac.role.service.RoleService;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.qvo.UserQvo;
import com.ybg.rbac.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/*@Api：用在类上，说明该类的作用
 @ApiOperation：用在方法上，说明方法的作用 value .api名称 notes 备注说明
 @ApiImplicitParams：用在方法上包含一组参数说明
 @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 paramType：参数放在哪个地方
 header-->请求参数的获取：@RequestHeader
 query-->请求参数的获取：@RequestParam
 path（用于restful接口）-->请求参数的获取：@PathVariable
 body（不常用）
 form（不常用）
 name：参数名
 dataType：参数类型
 required：参数是否必须传
 value：参数的意思
 defaultValue：参数的默认值
 name 对应方法中接收参数名称
 **分割线**
 value 备注说明
 required 是否必须 boolean
 paramType 参数类型 body、path、query、header、form中的一种 
 body 使用@RequestBody接收数据 POST有效
 path 在url中配置{}的参数
 query 普通查询参数 例如 ?query=q ,jquery ajax中data设置的值也可以，例如 {query:”q”},springMVC中不需要添加注解接收
 header 使用@RequestHeader接收数据
 form 笔者未使用，请查看官方API文档
 dataType 数据类型，如果类型名称相同，请指定全路径，例如 dataType = “java.util.Date”，springfox会自动根据类型生成模型
 @ApiResponses：用于表示一组响应
 @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 code：数字，例如400
 message：信息，例如"请求参数没填好"
 response：抛出异常的类
 @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 @ApiModelProperty：描述一个model的属性
 @ApiModelProperty 对模型中属性添加说明，例如 上面的PageInfoBeen、BlogArticleBeen这两个类中使用，只能使用在类中。

 value 参数名称
 required 是否必须 boolean
 hidden 是否隐藏 boolean 
 其他信息和上面同名属性作用相同，hidden属性对于集合不能隐藏，目前不知道原因
 *
 */
//①Accept：text/html,application/xml,application/json
//      将按照如下顺序进行produces的匹配 ①text/html ②application/xml ③application/json
//②Accept：application/xml;q=0.5,application/json;q=0.9,text/html
//      将按照如下顺序进行produces的匹配 ①text/html ②application/json ③application/xml
//      q参数为媒体类型的质量因子，越大则优先权越高(从0到1)
//③Accept：*/*,text/*,text/html
//      将按照如下顺序进行produces的匹配 ①text/html ②text/* ③*/* 
@Api("用户管理接口")
@Controller
@RequestMapping("/user/user_do/")
public class UserControllor {
	
	@Autowired
	UserService	userService;
	@Autowired
	RoleService	roleService;
	
	/** 首页 **/
	@ApiOperation(value = "用户页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, @ApiIgnore ModelMap modelMap) {
		// modelMap.addAttribute("res", findByRes(request));
		return "/system/user/index";
	}
	
	/** 列表 **/
	@ApiOperation(value = "用户分页列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNow", value = "当前页数", required = true, dataType = "java.lang.Integer"), @ApiImplicitParam(name = "qvo", value = "用户查询条件", required = false, dataType = "UserQvo") })
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list(@ModelAttribute UserQvo qvo, @RequestParam(name = "pageNow", required = false, defaultValue = "0") Integer pageNow, @ApiIgnore ModelMap modelMap) {
		qvo.setBlurred(true);
		Page page = new Page();
		page.setCurPage(pageNow);
		page = userService.query(page, qvo);
		page.init();
		return page;
	}
	
	/** 新增初始化 **/
	@ApiOperation(value = "添加用户页面", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@RequestMapping(value = { "toadd.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toadd(@ApiIgnore ModelMap map) {
		map.put("roleselect", roleService.query(new RoleQvo()));
		return "/system/user/toadd";
	}
	
	//
	// /*** 新增 **/
	// @ResponseBody
	// @RequestMapping("create.do")
	// //@SystemLog(module = "用户管理", methods = "创建用户")
	// public Json create(HttpServletRequest request, HttpServletResponse
	// response, ModelMap map) {
	// Json j = new Json();
	// j.setMsg("操作成功");
	// j.setSuccess(true);
	// PasswordHelper passwordHelper = new PasswordHelper();
	// User usernoid = getServletUser(request);
	// usernoid.setPassword("123456789");
	// passwordHelper.encryptPassword(usernoid);
	// // User usernoid = getServletUser(request);
	// // usernoid.setPassword(EncryptUtil.MD5("123456789"));
	// try {
	// userService.createandid(usernoid);
	// } catch (Exception e) {
	// e.printStackTrace();
	// j.setMsg("操作失败");
	// }
	// return j;
	// }
	/** 检测账号是否存在 **/
	@ApiOperation(value = "检测账号是否存在", notes = "要求传入帐号，email,手机号做检测", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "isexist.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public boolean isexist(@ModelAttribute UserQvo qvo) {
		return userService.checkisExist(qvo);
	}
	
	@ApiOperation(value = "更新初始化", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "java.lang.String") })
	@RequestMapping(value = { "toupdate.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String toupdate(@RequestParam(name = "id", required = true) String id, @ApiIgnore ModelMap map) {
		UserVO user = userService.get(id);
		map.put("updateuser", user);
		map.put("roleselect", roleService.query(new RoleQvo()));
		return "/system/user/edit";
	}
	
	/** 更新用户信息 **/
	@ApiOperation(value = "更新用户信息", notes = "只更新角色，用户状态(封号等)，email ,手机号", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@ModelAttribute UserVO user) {
		Json j = new Json();
		j.setSuccess(true);
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		updatemap.put("roleid", user.getRoleid());
		updatemap.put("state", user.getState());
		updatemap.put("email", user.getEmail());
		updatemap.put("phone", user.getPhone());
		wheremap.put("id", user.getId());
		try {
			userService.update(updatemap, wheremap);
		} catch (Exception e) {
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "根据ID 删除用户（可批量用逗号分割）", notes = "根据ID 删除用户（可批量用逗号分割）", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "用户ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		String[] ids = ids2.trim().split(",");
		try {
			for (String id : ids) {
				userService.removebyid(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	@ApiOperation(value = "导出用户", notes = "导出用户")
	@RequestMapping(value = { "export.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void export(@ModelAttribute UserQvo qvo, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		String filename = "用户导出" + DateUtil.getDate();
		filename = java.net.URLEncoder.encode(filename, "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xls");
		ServletOutputStream out = response.getOutputStream();
		ExcelUtil<UserVO> util = new ExcelUtil<UserVO>(UserVO.class);// 创建工具类.
		util.exportExcel(userService.query(qvo), "用户", out, 2, null, false);// 导出
	}
}
