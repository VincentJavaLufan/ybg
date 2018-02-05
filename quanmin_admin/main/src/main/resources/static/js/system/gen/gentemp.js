var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        genTemp : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.genTemp = {};
        },
        update : function(event) {
            var id = getSelectedRow('genTemptable', 'id');
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate : function(event) {
            var url = vm.genTemp.id == null ? "/sys/gentemp_do/create.do" : "/sys/gentemp_do/update.do";
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.genTemp),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function(event) {
            var ids = getSelectedRows('genTemptable', 'id');
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/sys/gentemp_do/remove.do",
                    data : {
                        ids : ids
                    },
                    success : function(r) {
                        alert(r.msg);
                    }
                });
            });
        },
        getInfo : function(id) {
            $.get(rootPath + "/sys/gentemp_do/get.do?id=" + id, function(r) {
                vm.genTemp = r.genTemp;
            });
        },
        reload : function(event) {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#genTemptable' // 选定是那个DIV
                    ,
                    url : rootPath + '/sys/gentemp_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                title : '',
                                minWidth : 100,
                                templet : function(data) {
                                   
                                    return data.id;
                                }
                            }, {
                                field : 'description',
                                title : '描述',
                                minWidth : 100,
                                templet : function(data) {
                                   
                                    return data.description;
                                }
                            }, {
                                field : 'genfilename',
                                title : '文件名称',
                                minWidth : 100,
                                templet : function(data) {
                                   
                                    return data.genfilename;
                                }
                            }, {
                                field : 'gencontext',
                                title : '文本内容',
                                minWidth : 100,
                                templet : function(data) {
                                   
                                    return data.gencontext;
                                }
                            }, {
                                field : 'state',
                                title : '是否启用',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.state;
                                }
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