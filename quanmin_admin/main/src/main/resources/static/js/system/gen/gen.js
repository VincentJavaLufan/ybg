var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		tablePrefix : null,
		javapackage : null,
		author : null,
		pathName : null,
		email : null
	},

	methods : {
		query : function() {
			vm.reload();
		},

		addConfig : function() {
			vm.showList = false;
			vm.title = "代码生成配置";
			var url = '/sys/generator_do/getsetting.do';
			$.ajax({
				type : "POST",
				url : url,
				// contentType : "application/json",
				dataType : 'json',
				data : {

				},
				success : function(r) {

					vm.javapackage=r.package;
					vm.tablePrefix=r.tablePrefix;
					vm.author=r.author;
					vm.pathName=r.pathName;
					vm.email=r.email;

				}
			});
		},
		saveOrUpdate : function() {
			var url = rootPath + "/sys/generator_do/updatesetting.do";
			$.ajax({
				dataType : 'json',
				type : "POST",
				url : url,
				// contentType : "application/json",
				data : {

					tablePrefix : vm.tablePrefix,
					javapackage : vm.javapackage,
					author : vm.author.value,
					pathName : vm.pathName,
					email : vm.email

				},
				success : function(r) {

					alert(r.msg);

				}
			});
		},

		reload : function() {
			vm.showList = true;
			grid.loadData();
		}
	}
});

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

	$("#delRole").click("click", function() {
		delRole();
	});

});

function delRole() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择生成表！！");
		return;
	}
	layer.confirm('是否生成？', function(index) {
		location.href = '/sys/generator_do/code.do?tables=' + cbox.join(",");

	});
}
