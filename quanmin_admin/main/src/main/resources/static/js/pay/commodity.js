var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		commodity : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.commodity = {};
		},
		update : function(event) {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		saveOrUpdate : function(event) {
			var url = vm.commodity.id == null ? "/pay/commodity/create.do"
					: "/pay/commodity/update.do";
			$.ajax({
				type : "POST",
				url : rootPath + url,
				contentType : "application/json",
				data : JSON.stringify(vm.commodity),
				success : function(r) {
					vm.reload();
					alert(r.msg);

				}
			});
		},
		del : function(event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : rootPath + "/pay/commodity/remove.do",
					data : {
						ids : ids
					},
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo : function(id) {
			$.get(rootPath + "/pay/commodity/get.do?id=" + id, function(r) {
				vm.commodity = r.commodity;
			});
		},
		reload : function(event) {
			var searchParams = $("#searchForm").serializeJson();// 初始化传参数
			grid.setOptions({
				data : searchParams
			});
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
			name : "",
			width : "50px",
			hide : true
		}, {
			colkey : "name",
			name : "商品名称"
		}, {
			colkey : "type",
			name : "类型"
		}, {
			colkey : "price",
			name : "价格"
		}, {
			colkey : "picture",
			name : "图片地址"
		}, {
			colkey : "description",
			name : "商品描述"
		} ],
		jsonUrl : rootPath + '/pay/commodity/list.do',
		checkbox : true
	});
});
