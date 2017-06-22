var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "name",
			name : "参数名"
		}, {
			colkey : "paramtype",
			name : "参数类型",
		}, {
			colkey : "paramValue",
			name : "参数值",
		}, {
			colkey : "description",
			name : "参数描述",
		}, {
			colkey : "state",
			name : "参数状态",
			renderData : function(rowindex, data, rowdata, column) {
				if (data == "1") {
					return "启用";
				}
				if (data == "0") {
					return "<font color='red'>停用</font>";
				}
				return "数据异常";
			}
		}],
		jsonUrl : rootPath + '/gzbg/ywparam_do/list.do',
		checkbox : true,
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	$("#addFun").click("click", function() {
		addFun();
	});
	$("#editFun").click("click", function() {
		editFun();
	});
	$("#delFun").click("click", function() {
		delFun();
	});
});
function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/gzbg/ywparam_do/toupdate.do?id=' + cbox
	});
}
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/gzbg/ywparam_do/toadd.do'
	});
}
function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/gzbg/ywparam_do/remove.do';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s.msg == "操作成功") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}