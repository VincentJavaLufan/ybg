var ztree;// 授权
var roletree;// 批量授权-角色
var restree;// 批量授权-菜单
var edittree;// 编辑 父级角色
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
        nocheckInherit : true,
        chkboxType :{ "Y" : "s", "N" : "s" }
    }
};



var rolesetting = {
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
        nocheckInherit : true,
        chkboxType: { "Y" : "s", "N" : "s" }
    },
    callback: {
        onClick: loadrestreedata
    }
};

var editsetting = {
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
        enable : false,
        nocheckInherit : true,
        chkboxType :{ "Y" : "s", "N" : "s" }
    }
};


var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        role : {
            parentname : '无',
            parentid : "0",
        },
        showedit : false,
        showbatch : false,
        menuList:null,// 初始菜单
        batchauth:{
            menuIdList:[],
            roleIdList:[]
        }
    },
    methods : {
        // 查询
        query : function() {
            vm.reload();
        },
        // 授权菜单
        getMenuTree : function(roleId) {
            // 加载菜单树
           // $.get(rootPath + "/res/res_do/reslists.do", function(r) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, vm.menuList);
                // 展开所有节点
                ztree.expandAll(true);
                if (roleId != null) {
                    vm.getRole(roleId);
                }
          // });
        },
        deleteparent:function(){
            vm.role.parentid = null;
         //   vm.role.parentname = '无';
            $("#editparentid").val('无');
       //     alert( vm.role.parentid);
        },
        add : function() {
            vm.showdiv();
            vm.showedit=true;
            vm.title = "新增角色";
            vm.role = {};
            vm.getMenuTree(null);
            //
            var node = edittree.getNodeByParam("id", vm.role.parentid);
            edittree.selectNode(node);
            try {
                vm.role.parentname = node.name;
            } catch (e) {
            }
        },
        update : function() {
            var roleId = getMenuId();// getSelectedRow('roletable', 'id');
            if (roleId == null) {
                return;
            }
            vm.showdiv();
            vm.showedit=true;
            vm.title = "修改角色";
            vm.getRole(roleId);//
            vm.getMenuTree(roleId);
        },
        del : function(event) {
            var roleIds = getMenuId();// getSelectedRows('roletable', 'id');
            if (roleIds == null) {
                return;
            }
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
        // 勾选授权菜单
        getRole : function(roleId) {
            $.get(rootPath + '/role/role_do/get.do?id=' + roleId, function(r) {
                vm.role = r.role;
                // 勾选角色所拥有的菜单
                var menuIds = vm.role.menuIdList;
                for (var i = 0; i < menuIds.length; i++) {
                    var node = ztree.getNodeByParam("id", menuIds[i]);
                    ztree.checkNode(node, true, false);
                }
                //
                var node = edittree.getNodeByParam("id", vm.role.parentid);
                edittree.selectNode(node);
                try {
                    vm.role.parentname = node.name;
                } catch (e) {
                }
            });
            
        },
        // 批量授权时的点击时间
        loadRoletoBatch : function(roleId) {
            restree.checkAllNodes(false);
            $.get(rootPath + '/role/role_do/get.do?id=' + roleId, function(r) {
               // vm.role = r.role;
                // 勾选角色所拥有的菜单
                var menuIds = r.role.menuIdList;
                for (var i = 0; i < menuIds.length; i++) {
                    var node = restree.getNodeByParam("id", menuIds[i]);
                    restree.checkNode(node, true, false);
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
                    // vm.showList = true;
                    alert(data.msg);
                }
            });
        },
        batchauthorizesubmit : function() {
            var nodes = restree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].id);
            }
            
            vm.batchauth.menuIdList = menuIdList;
            
            
            var nodes2 = roletree.getCheckedNodes(true);
            var roleIdList = new Array();
            for (var i = 0; i < nodes2.length; i++) {
                roleIdList.push(nodes2[i].id);
            }
            vm.batchauth.roleIdList = roleIdList;
            
            
            
            $.ajax({
                type : "POST",
                url : rootPath+"/role/role_do/batchauthorize.do",
                contentType : "application/json",
                data : JSON.stringify(vm.batchauth),
                success : function(data) {
                    // vm.showList = true;
                    alert(data.msg);
                }
            });
        },
        batchauthorize : function() {
            vm.showdiv();
            vm.showbatch=true;
            // 加载菜单树
            $.get(rootPath + "/res/res_do/reslists.do", function(r) {
                restree = $.fn.zTree.init($("#batchmenuTree"), setting, r.menuList);
                // 展开所有节点
                restree.expandAll(true);
                
            });
            // 加载角色树
            $.get(rootPath + "/role/role_do/select.do", function(r) {
                roletree = $.fn.zTree.init($("#batchroleTree"), rolesetting, r.roleselect);
                // 展开所有节点
                roletree.expandAll(true);
               
            });
        },
        
        getRoleTree(){
           
           
                layui.use('layer', function() {
                    var layer = layui.layer;
                    layer.open({
                        type : 1,
                        offset : '50px',
                        skin : 'layui-layer-molv',
                        title : "选择菜单",
                        area : [
                            '300px', '450px' ],
                        shade : 0,
                        shadeClose : false,
                        content : jQuery("#roleLayer"),
                        btn : [
                            '确定', '取消' ],
                        btn1 : function(index) {
                            
                            var node = edittree.getSelectedNodes();
                           
                            // 选择上级菜单
                            vm.role.parentid = node[0].id;
                            vm.role.parentname = node[0].name;
                            $("#editparentid").val(vm.role.parentname);
                            
                           
                            
                            layui.use('layer', function() {
                                console.log(vm.role.parentname+":154");
                                var layer = layui.layer;
                                layer.close(index);
                            });
                        }
                    });
                    
                });
                
               
            
            
           
        },
        showdiv : function() {
            vm.showList = false;
            vm.showedit = false;
            vm.showbatch = false;
           
        },
        init:function(){
            // 加载菜单树
            $.get(rootPath + "/res/res_do/reslists.do", function(r) {
                vm.menuList=r.menuList
            });
            
            $.get(rootPath + "/role/role_do/select.do", function(r) {
                edittree = $.fn.zTree.init($("#editroleTree"), editsetting, r.roleselect);
                // 展开所有节点
                edittree.expandAll(true);
                // 选择父级橘色
                var node = edittree.getNodeByParam("id", vm.role.parentid);
                edittree.selectNode(node);
                try {
                    vm.role.parentname = node.name;
                } catch (e) {
                    console.log(e)
                }
              
            });
        },
        reload : function() {
            vm.showdiv();
            vm.showList=true;
            Menu.table.refresh();
           

        }
    }
});
function loadrestreedata(event, treeId, treeNode){
    // alert(treeNode.id + ", " + treeNode.name);
    vm.loadRoletoBatch(treeNode.id)
}
var Menu = {
    id : "roletable",
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
            title : '角色ID',
            field : 'id',
            visible : false,
            align : 'center',
            valign : 'middle',
            width : '80px'
        }, {
            title : '角色名称',
            field : 'name',
            align : 'center',
            valign : 'middle',
            sortable : true,
            width : '180px'
        },
        
        {
            title : '角色状态',
            field : 'state',
            align : 'center',
            valign : 'middle',
            sortable : true,
            width : '100px',
            formatter : function(item, index) {
                if (item.state == "0") {
                    return '正常';
                }
                if (item.state == "1") {
                    return '禁用';
                }
               return "";
            }
        }
        ]
    return columns;
};



function getMenuId() {
    var selected = $('#roletable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}
$(function() {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, rootPath + "/role/role_do/selecttreetable.do", colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentid");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});
vm.init();
vm.reload();