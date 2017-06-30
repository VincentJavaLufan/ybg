var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			width : "50px",
			hide : true
		}, {
			colkey : "tableName",
			name : "表名"
		}, {
			colkey : "comments",
			name : "备注"

		} ],
		jsonUrl : rootPath + '/sys/generator_do/list.do',
		checkbox : true,
        checkValue : 'tableName'
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	// $("#addRole").click("click", function() {
	// addRole();
	// });
	// $("#editRole").click("click", function() {
	// editRole();
	// });
	 $("#delRole").click("click", function() {
	 delRole();
	 });
	// $("#permissions").click("click", function() {
	// permissions();
	// });
});
function editRole() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		maxmin : true, // 开启最大化最小化按钮
		area : [ "600px", "60%" ],
		content : rootPath + '/role/role_do/toupdate.do?id=' + cbox
	});
}
function permissions() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/res/res_do/permissions.do?roleid=' + cbox;
	pageii = layer.open({
		title : "分配权限",
		type : 2,
		maxmin : true, // 开启最大化最小化按钮
		area : [ "700px", "60%" ],
		content : url
	});
}
function addRole() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		maxmin : true, // 开启最大化最小化按钮
		area : [ "600px", "60%" ],
		content : rootPath + '/role/role_do/toadd.do'
	});
}
function delRole() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择生成表！！");
		return;
	}
	layer.confirm('是否生成？', function(index) {
		location.href='/sys/generator_do/code.do?tables='+cbox.join(",");
		
	});
}
