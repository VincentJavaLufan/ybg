package com.ybg.activiti.rest.editor.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Json;
import com.ybg.base.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.List;

/** 懒得写，直接参照了：http://www.jianshu.com/p/cf766a713a86 自己增加的controller，用于对model进行操作，返回值随便弄的，需要修改 Created by chenhai on 2017/6/6. */
@Api(tags = "工作流-模型API")
@Controller
@RequestMapping("/models/model_do/")
public class ModelCreateController {
	
	@Autowired
	ProcessEngine	processEngine;
	@Autowired
	ObjectMapper	objectMapper;
	
	/*** 首页 **/
	@ApiOperation(value = "首页", notes = "", produces = MediaType.TEXT_HTML_VALUE)
	@GetMapping(value = { "index.do" })
	public String index() {
		return "/activiti/activiti";
	}
	
	/** 新建一个空模型
	 * 
	 * @return
	 * @throws UnsupportedEncodingException */
	@ApiOperation(value = "创建模型", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "ModelEntity", value = "模型实体", required = true, dataType = "org.activiti.engine.impl.persistence.entity.ModelEntity") })
	@ResponseBody
	@PostMapping("create.do")
	public Json newModel(@RequestBody ModelEntity m) throws UnsupportedEncodingException {
		Json json = new Json();
		if (!QvoConditionUtil.checkString(m.getName())) {
			json.setSuccess(false);
			json.setMsg("操作失败，名称为空");
			return json;
		}
		if (!QvoConditionUtil.checkString(m.getKey())) {
			json.setSuccess(false);
			json.setMsg("操作失败，关键字为空");
			return json;
		}
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 初始化一个空模型
		Model model = repositoryService.newModel();
		// 设置一些默认信息
		// String name = "new-process";
		String description = "";
		int revision = 1;
		String key = m.getKey();
		ObjectNode modelNode = objectMapper.createObjectNode();
		modelNode.put(ModelDataJsonConstants.MODEL_NAME, m.getName());
		modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
		model.setName(m.getName());
		model.setKey(key);
		model.setMetaInfo(modelNode.toString());
		repositoryService.saveModel(model);
		String id = model.getId();
		// 完善ModelEditorSource
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);
		repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
		json.setSuccess(true);
		json.setMsg("操作成功");
		return json;
	}
	
	/** 获取所有模型
	 * 
	 * @return */
	@ApiOperation(value = "模型列表", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PostMapping("list.do")
	public Page modelList() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<Model> models = repositoryService.createModelQuery().list();
		Page page = new Page();
		page.setCurPage(1);
		page.setResult(models);
		page.setTotals(models.size());
		page.init();
		return page;
	}
	
	@ApiOperation(value = "根据ID删除模型", notes = " ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "ids", value = "删除模型的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@RequestMapping(value = { "remove.do" }, method = { RequestMethod.POST })
	public Json remove(@RequestParam(name = "ids", required = true) String ids2) {
		Json j = new Json();
		j.setSuccess(true);
		try {
			String[] ids = ids2.split(",");
			for (String id : ids) {
				RepositoryService repositoryService = processEngine.getRepositoryService();
				repositoryService.deleteModel(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("操作失败");
			return j;
		}
		j.setMsg("操作成功");
		return j;
	}
	
	/** 发布模型为流程定义
	 *
	 * @param id
	 * @return
	 * @throws Exception */
	@ApiOperation(value = "发布模型", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParam(name = "id[]", value = "发布模型的ID", required = true, dataType = "java.lang.String")
	@ResponseBody
	@PostMapping("deployment.do")
	public Json deploy(@RequestParam(name = "id[]", required = true) String id) throws Exception {
		Json j = new Json();
		// 获取模型
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Model modelData = repositoryService.getModel(id);
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
		if (bytes == null) {
			j.setSuccess(false);
			j.setMsg("模型数据为空，请先设计流程并成功保存，再进行发布。");
			return j;
		}
		JsonNode modelNode = new ObjectMapper().readTree(bytes);
		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0) {
			j.setSuccess(false);
			j.setMsg("数据模型不符要求，请至少设计一条主线流程。");
			return j;
		}
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
		// 发布流程
		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);
		j.setSuccess(true);
		j.setMsg("操作成功");
		return j;
	}
}
