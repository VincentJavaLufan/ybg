package com.ybg.tags.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.tags.domain.WeixinTagsVO;
import io.swagger.annotations.Api;

@Api(tags = "微信用户标签API")
@Controller
@RequestMapping("/weixin/tags/")
public class WeixinTagsController {
	
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/weixin/tags";
	}
	
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list() {
		Page page = new Page();
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody WeixinTagsVO tags) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	//更需必须要 id和name
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody WeixinTagsVO tags) {
		Json j = new Json();
		
		
		
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	//根据ID 删除
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(Integer id) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 批量为用户取消标签
	@ResponseBody
	@RequestMapping(value = { "batchuntagging.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json batchuntagging(String openids,Integer tagid) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 批量为用户打上标签
	@ResponseBody
	@RequestMapping(value = { "batchtagging.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json batchtagging(String openids,Integer tagid) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
}
