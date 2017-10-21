package com.ybg.tags.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import com.ybg.tags.domain.WeixinTagsVO;
import com.ybg.tags.service.WeixinTagsService;
import io.swagger.annotations.Api;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Api(tags = "微信用户标签API")
@Controller
@RequestMapping("/weixin/tags/")
public class WeixinTagsController {
	
	@Autowired
	WeixinTagsService weixinTagsService;
	
	@RequestMapping(value = { "index.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/weixin/tags";
	}
	
	@ResponseBody
	@RequestMapping(value = { "list.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Page list() {
		Page page = new Page();
		List<WeixinTagsVO> list = weixinTagsService.list();
		page.setCurPage(1);
		page.setResult(list);
		page.setTotals(list.size());
		page.init();
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value = { "create.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json create(@RequestBody WeixinTagsVO tags) {
		Json j = new Json();
		try {
			weixinTagsService.create(tags);
		} catch (Exception e) {
			j.setMsg("操作失败");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 更需必须要 id和name
	@ResponseBody
	@RequestMapping(value = { "update.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(@RequestBody WeixinTagsVO tags) {
		Json j = new Json();
		weixinTagsService.update(tags.getId(), tags.getName());
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 根据ID 删除
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		String[] ids = ids2.trim().split(",");
		try {
			for (String id : ids) {
				weixinTagsService.remove(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 批量为用户取消标签
	@ResponseBody
	@RequestMapping(value = { "batchuntagging.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json batchuntagging(String openids, Integer tagid) {
		Json j = new Json();
		// weixinTagsService.batchuntagging(openids, tagid);
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
	
	// 批量为用户打上标签
	@ResponseBody
	@RequestMapping(value = { "batchtagging.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Json batchtagging(String openids, Integer tagid) {
		Json j = new Json();
		j.setMsg("操作成功");
		j.setSuccess(true);
		return j;
	}
}
