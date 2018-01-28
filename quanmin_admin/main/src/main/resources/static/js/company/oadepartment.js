var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        oaDepartment : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.oaDepartment = {};
        },
        update : function(event) {
            var id = getSelectedRow('oaDepartmenttable', 'id');
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate : function(event) {
            var url = vm.oaDepartment.id == null ? "/oa/department_do/create.do" : "/oa/department_do/update.do";
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.oaDepartment),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function(event) {
            var ids = getSelectedRows('oaDepartmenttable', 'id');
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/oa/department_do/remove.do",
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
            $.get(rootPath + "/oa/department_do/get.do?id=" + id, function(r) {
                vm.oaDepartment = r.oaDepartment;
            });
        },
        reload : function(event) {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#oaDepartmenttable' // 选定是那个DIV
                    ,
                    url : rootPath + '/oa/department_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                title : 'ID',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.id;
                                }
                            }, {
                                field : 'name',
                                title : '部门名称',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.name;
                                }
                            }, {
                                field : 'companyid',
                                title : '公司ID',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.companyid;
                                }
                            }, {
                                field : 'gmtCreate',
                                title : '创建时间',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.gmtCreate;
                                }
                            }, {
                                field : 'parentid',
                                title : '父级ID',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.parentid;
                                }
                            }, {
                                field : 'gmtModified',
                                title : '修改时间',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.gmtModified;
                                }
                            }, {
                                field : 'companyname',
                                title : '公司名称',
                                minWidth : 100,
                                templet : function(data) {
                                    // if (data.state == 0) {
                                    // return "正常";
                                    // }
                                    // if (data.state == 1) {
                                    // return "<font color='red'>刪除</font>";
                                    // }
                                    // return "数据异常";
                                    return data.companyname;
                                }
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                    where : $("#searchForm").serializeJSON()
                });// table
            });
        }
    }
// method
});
vm.reload();