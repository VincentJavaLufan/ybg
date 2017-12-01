var ztree;
var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        role : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        getMenuTree : function(roleId) {
            // 加载菜单树
            $.get(rootPath + "/res/res_do/reslists.do", function(r) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                // 展开所有节点
                ztree.expandAll(true);
                if (roleId != null) {
                    vm.getRole(roleId);
                }
            });
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增角色";
            vm.role = {};
            vm.getMenuTree(null);
        },
        update : function() {
            var roleId = getSelectedRow();
            if (roleId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改角色";
            vm.getRole(roleId);
            vm.getMenuTree(roleId);
        },
        del : function(event) {
            var roleIds = getSelectedRows();
            if (roleIds == null) {
                return;
            }
            var cbox = grid.getSelectedCheckbox();
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + '/role/role_do/remove.do',
                    dataType : "json",
                    data : {
                        ids : roleIds
                    },
                    success : function(r) {
                        if (r.success) {
                            alert(r.msg);
                            vm.reload();
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getRole : function(roleId) {
            $.get(rootPath + '/role/role_do/get.do?id=' + roleId, function(r) {
                vm.role = r.role;
                // 勾选角色所拥有的菜单
                var menuIds = vm.role.menuIdList;
                for (var i = 0; i < menuIds.length; i++) {
                    var node = ztree.getNodeByParam("id", menuIds[i]);
                    ztree.checkNode(node, true, false);
                }
            });
        },
        saveOrUpdate : function() {
            var url = vm.role.id == null ? rootPath + "/role/role_do/create.do" : rootPath + "/role/role_do/update.do";
            var nodes = ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].id);
            }
            vm.role.menuIdList = menuIdList;
            $.ajax({
                type : "POST",
                url : url,
                contentType : "application/json",
                data : JSON.stringify(vm.role),
                success : function(data) {
                	console.log(data.msg);
                	// vm.showList = true;
                    alert(data.msg);
                }
            });
        },
        reload : function() {
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
        l_column : [
                {
                    colkey : "id",
                    name : "id",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "name",
                    name : "角色名"
                }, {
                    colkey : "state",
                    name : "状态",
                    renderData : function(rowindex, data, rowdata, colkeyn) {
                        if (data == 0) {
                            return "正常";
                        }
                        if (data == 1) {
                            return "删除";
                        }
                        return "数据异常";
                    }
                }, {
                    colkey : "rolekey",
                    name : "roleKey"
                }, {
                    colkey : "description",
                    name : "描述"
                }],
        jsonUrl : rootPath + '/role/role_do/list.do',
      
        checkbox : true,// 是否显示复选框
        checkValue : 'id', // 当checkbox为true时，需要设置存放checkbox的值字段 默认存放字段id的值
    });
//    $("#search").click("click", function() {// 绑定查询按扭
//        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
//        grid.setOptions({
//            data : searchParams
//        });
//    });
});
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
    },
    check : {
        enable : true,
        nocheckInherit : true
    }
};

