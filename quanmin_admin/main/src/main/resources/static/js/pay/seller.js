var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			paytype : null
		},
		showList : true,
		title : null,
		seller : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.seller = {};
		},
		update : function() {
			var payid = getSelectedRow();
			if (payid == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(payid);
		},
		getInfo : function(payid) {
			$.get(rootPath + "/pay/seller/get.do?id=" + payid, function(r) {
				vm.payAccount = r.payAccount;
			});
		},

		saveOrUpdate : function(event) {
			var url = vm.seller.payid == null ? "/pay/seller/create.do"
					: "/pay/seller/update.do";
			$.ajax({
				type : "POST",
				url : rootPath + url,
				contentType : "application/json",
				data : JSON.stringify(vm.seller),
				success : function(r) {
					vm.reload();
					alert(r.msg);

				}
			});
		},
		del : function() {
			var payids = getSelectedRows();
			if (payids == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : rootPath + "/pay/seller/remove.do",
					data : {
						ids : payids
					},
					success : function(r) {

						alert(r.msg);
						vm.reload();

					}
				});
			});
		},
		reload : function(event) {
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
			colkey : "payid",
			name : "支付账号id",
			width : "50px",
			hide : true
		}, {
			colkey : "partner",
			name : "商户ID/平台ID "
		}, {
			colkey : "appid",
			name : "应用id"
		}, {
			colkey : "publickey",
			name : "支付平台公钥",
			renderData : function(rowindex, data, rowdata, column) {
				if (data.length > 32) {
					return data.substring(0, 32) + "...";
				}
				return data;
			}
		}, {
			colkey : "privatekey",
			name : "应用私钥(生成签名)",
			renderData : function(rowindex, data, rowdata, column) {
				if (data.length > 32) {
					return data.substring(0, 32) + "...";
				}
				return data;
			}
		}, {
			colkey : "notifyurl",
			name : "异步回调地址"
		}, {
			colkey : "returnurl",
			name : "同步回调地址"
		}, {
			colkey : "seller",
			name : "收款账号, 针对支付宝"
		}, {
			colkey : "signtype",
			name : "签名类型"
		}, {
			colkey : "inputcharset",
			name : "枚举值，字符编码 utf-8,gbk等等"
		}, {
			colkey : "paytype",
			name : "支付类型,"
		}, {
			colkey : "msgtype",
			name : "消息类型，text,xml,json"
		}, {
			colkey : "test",
			name : "是否为测试环境"
		} ],
		jsonUrl : '/pay/seller/list.do',
		checkbox : true,
		checkValue : 'payid',
	});

});
