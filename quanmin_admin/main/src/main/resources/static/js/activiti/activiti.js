var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        m : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.m = {};
        },
        update : function() {
            var jobId = getSelectedRow('acttable', 'id');
            if (jobId == null) {
                return;
            }
            $.get(rootPath + "/models/model_do/info.do?jobId=" + jobId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.m = r.m;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.m.id == null ? rootPath + '/models/model_do/create.do' : rootPath + '/models/model_do/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.m),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        deployment : function() {
            var id = getSelectedRow('acttable', 'id');
            if (id == null) {
                return;
            }
            $.ajax({
                type : "POST",
                url : rootPath + "/models/model_do/deployment.do",
                // contentType : "application/json",
                data : {
                    'id' : id
                },
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var id = getSelectedRows('acttable', 'id');
            if (id == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/models/model_do/remove.do",
                    // contentType : "application/json",
                    data : {
                        ids : id
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        reload : function(event) {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#acttable' // 选定是那个DIV
                    ,
                    url : rootPath + '/models/model_do/list.do',
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
                                title : '流程名称'
                            }, {
                                field : 'key',
                                width : 180,
                                title : '流程关键字'
                            }, {
                                field : 'createTime',
                                width : 180,
                                title : '创建时间'
                            }, {
                              
                                title : '操作',
                                minWidth : 100,
                                templet : function(data) {
                                    return "<a target='_blank' class='btn btn-primary' href='/modeler.html?modelId=" + data.id + "'> <i class='fa fa-pencil-square-o'></i>设计流程</a>";
                                }
                            } ] ],
                    page : true, // 开启分页
                    method:'post',
                    request : laypagerequest,
                    response : laypageresponse,
                    where : $("#searchForm").serializeJSON()
                });
            });
        }
    }
});
vm.reload();
