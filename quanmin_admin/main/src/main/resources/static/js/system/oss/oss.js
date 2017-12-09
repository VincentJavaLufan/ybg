var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		config : {}
	},
	created : function() {
		this.getConfig();
	},
	methods : {
		query : function() {
			vm.reload();
		},
		getConfig : function() {
			$.getJSON(rootPath + "/sys/oss_do/config.do", function(r) {
				vm.config = r.config;
			});
		},
		addConfig : function() {
			vm.showList = false;
			vm.title = "云存储配置";
		},
		saveOrUpdate : function() {
			var url = rootPath + "/sys/oss_do/saveConfig.do";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify(vm.config),
				success : function(r) {

					alert(r.msg);

				}
			});
		},
		del : function() {
			var ossIds = getSelectedRows();
			if (ossIds == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : rootPath + "/sys/oss_do/delete.do",
					data : {
						ids : ossIds
					},
					success : function(r) {
						alert(r.msg);
						vm.reload();
					}
				});
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
			colkey : "url",
			name : "图片",
			renderData : function(rowindex, data, rowdata, colkeyn) {
				return "<img width='300px' hight='300px' src='" + data + "'>";
			}
		}, {
			colkey : "createdate",
			name : "创建时间"
		} ],
		jsonUrl : rootPath + '/sys/oss_do/list.do',
		checkbox : true
	});

});
