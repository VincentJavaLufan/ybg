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
            var id = getSelectedRow('commoditytable', 'id');
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate : function(event) {
            var url = vm.commodity.id == null ? "/pay/commodity/create.do" : "/pay/commodity/update.do";
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
            var ids = getSelectedRows('commoditytable', 'id');
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
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#commoditytable' // 选定是那个DIV
                    ,
                    url :  rootPath + '/pay/commodity/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                width : 180,
                                title : 'ID'
                            }, {
                                field : 'name',
                                width : 180,
                                title : '商品名称'
                            }, {
                                field : 'type',
                                title : '类型',
                                width : 100
                            }, {
                                field : 'price',
                                title : '价格',
                                width : 100
                            }, {
                                field : 'type',
                                title : '类型',
                                width : 100
                            }, {
                                field : 'picture',
                                title : '价格',
                                width : 100
                            }, {
                                field : 'description',
                                title : '商品描述',
                                width : 100
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                    where : $("#searchForm").serializeJSON()
                });
            });
        }
    }
});
vm.reload();
