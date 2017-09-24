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
			name : "公司名称"
		}, {
			colkey : "fzr",
			name : "负责人",
			width : '90px'
		}, {
			colkey : "fzrlxdh",
			name : "负责人电话",
			width : '90px'
		}, {
			colkey : "dz",
			name : "地址",
		}],
		jsonUrl : rootPath + '/gzbg/company_do/list.do',
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
		addCompany();
	});
	$("#editFun").click("click", function() {
		editCompany();
	});
	$("#delFun").click("click", function() {
		delCompany();
	}); 
});
function editCompany() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/gzbg/company_do/toupdate.do?id=' + cbox
	});
}
function addCompany() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/gzbg/company_do/toadd.do'
	});
}
function delCompany() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/gzbg/company_do/remove.do';
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