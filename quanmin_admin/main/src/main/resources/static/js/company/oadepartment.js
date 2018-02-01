var setting = {
    data : {
        simpleData : {
            enable : true,
            idKey : "id",
            pIdKey : "parentid",
            rootPId : "0"
        },
        key : {
            url : "nourl"
        }
    }
};
var ztree;
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
            Menu.table.refresh();
            // layui.use('table', function() {
            // var table = layui.table;
            // table.render({
            // elem : '#oaDepartmenttable' // 选定是那个DIV
            // ,
            // url : rootPath + '/oa/department_do/list.do',
            // cols : [
            // [
            // {
            // type : 'checkbox'
            // }, {
            // field : 'id',
            // title : 'ID',
            // minWidth : 100,
            // templet : function(data) {
            // return data.id;
            // }
            // }, {
            // field : 'name',
            // title : '部门名称',
            // minWidth : 100,
            // templet : function(data) {
            // return data.name;
            // }
            // }, {
            // field : 'companyid',
            // title : '公司ID',
            // minWidth : 100,
            // templet : function(data) {
            // return data.companyid;
            // }
            // }, {
            // field : 'gmtCreate',
            // title : '创建时间',
            // minWidth : 100,
            // templet : function(data) {
            // return data.gmtCreate;
            // }
            // }, {
            // field : 'parentid',
            // title : '父级ID',
            // minWidth : 100,
            // templet : function(data) {
            // return data.parentid;
            // }
            // }, {
            // field : 'gmtModified',
            // title : '修改时间',
            // minWidth : 100,
            // templet : function(data) {
            // return data.gmtModified;
            // }
            // }, {
            // field : 'companyname',
            // title : '公司名称',
            // minWidth : 100,
            // templet : function(data) {
            // return data.companyname;
            // }
            // } ] ],
            // page : true, // 开启分页
            // request : laypagerequest,
            // response : laypageresponse,
            // where : $("#searchForm").serializeJSON()
            // });// table
            // });
        }
    }
// method
});
var Menu = {
    id : "oaDepartmenttable",
    table : null,
    layerIndex : "0"
};
/**
 * 初始化表格的列
 */
Menu.initColumn = function() {
    var columns = [
        {
            field : 'selectItem',
            radio : true
        }, {
            title : '部门ID',
            field : 'id',
            visible : false,
            align : 'center',
            valign : 'middle',
            width : '80px'
        }, {
            title : '部门名称',
            field : 'name',
            align : 'center',
            valign : 'middle',
            sortable : true,
            width : '180px'
        } ]
    return columns;
};
function getMenuId() {
    var selected = $('#oaDepartmenttable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}
$(function() {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, rootPath + '/oa/department_do/treelist.do', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentid");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});
// vm.reload();
