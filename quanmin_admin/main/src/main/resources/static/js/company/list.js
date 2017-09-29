var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ 
 
		{colkey : "id",
			name : "id",			
			renderData : function(rowindex, data, rowdata, column) {
				return data;
			}
		}, 	  
		{colkey : "name",
			name : "name",			
			renderData : function(rowindex, data, rowdata, column) {
				return data;
			}
		}, 	  
		{colkey : "companyid",
			name : "companyid",			
			renderData : function(rowindex, data, rowdata, column) {
				return data;
			}
		}, 	  
		{colkey : "deptid",
			name : "deptid",			
			renderData : function(rowindex, data, rowdata, column) {
				return data;
			}
		} 	  ],
		jsonUrl : rootPath + '/user/user_do/list.do',
		checkbox : true,
		serNumber : false
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams,
		});
	});
	$("#AddFun").click("click", function() {
		AddFun();
	});
	$("#EditFun").click("click", function() {
		EditFun();
	});
	$("#DelFun").click("click", function() {
		DelFun();
	});
	
});
function EditFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		alert("只能选中一个");	
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		maxmin : true, // 开启最大化最小化按钮
		area : [ "600px", "80%" ],
		content : rootPath + '/xxp2t/oaEmployee_do/toupdate.do?id=' + cbox,
	});
}
function AddFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		maxmin : true, // 开启最大化最小化按钮
		area : [ "600px", "80%" ],
		content : rootPath + '/xxp2t/oaEmployee_do/toadd.do'
	});
}
function DelFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		alert("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/xxp2t/oaEmployee_do/remove.do';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(","),
		}, "json");
		if (s.msg == "操作成功") {
			alert('删除成功');
			grid.loadData();
		} else {
			alert('删除失败');
		};
	});
};
