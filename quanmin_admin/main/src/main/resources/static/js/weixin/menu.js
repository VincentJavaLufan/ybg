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
        q : {
            beanName : null
        },
        showList : true,
        title : null,
        weixinmenu : {}
    },
    methods : {
        getMenu : function(menuId) {
            // 加载菜单树
            $.get(rootPath + "/weixin/menu_do/select.do", function(r) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                var node = ztree.getNodeByParam("id", vm.weixinmenu.parentid);
                ztree.selectNode(node);
                try {
                    vm.weixinmenu.parentname = node.name;
                } catch (e) {
                }
            })
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.weixinmenu = {
                parentname : null,
                parentid : null,
                type : 'view',
                ifsub : 2,
                menuorder : 3,
                buttonorder : 5
            };
            vm.getMenu();
        },
        update : function() {
            var menuId = getMenuId();
            if (menuId == null) {
                return;
            }
            $.get(rootPath + "/weixin/menu_do/get.do?id=" + menuId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.weixinmenu = r.weixinbutton;
                vm.getMenu();
            });
        },
        del : function() {
            var menuId = getMenuId();
            if (menuId == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/menu_do/remove.do",
                    data : {
                        id : menuId
                    },
                    success : function(r) {
                        alert(r.msg);
                        vm.reload();
                    }
                });
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.weixinmenu.id == null ? "/weixin/menu_do/create.do" : "/weixin/menu_do/update.do";
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.weixinmenu),
                success : function(r) {
                    alert(r.msg);
                    vm.reload();
                }
            });
        },
        menuTree : function() {
            layer.open({
                type : 1,
                offset : '50px',
                skin : 'layui-layer-molv',
                title : "选择菜单",
                area : [
                        '300px', '450px'],
                shade : 0,
                shadeClose : false,
                content : jQuery("#menuLayer"),
                btn : [
                        '确定', '取消'],
                btn1 : function(index) {
                    var node = ztree.getSelectedNodes();
                    // 选择上级菜单
                    vm.weixinmenu.parentid = node[0].id;
                    vm.weixinmenu.parentname = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload : function() {
            vm.showList = true;
            Menu.table.refresh();
        }
    }
});
var Menu = {
    id : "menuTable",
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
                title : '菜单ID',
                field : 'id',
                visible : false,
                align : 'center',
                valign : 'middle',
                width : '80px'
            }, {
                title : '菜单名称',
                field : 'name',
                align : 'center',
                valign : 'middle',
                sortable : true,
                width : '180px'
            }, {
                title : '类型',
                field : 'type',
                align : 'center',
                valign : 'middle',
                sortable : true,
                width : '100px',
                formatter : function(item, index) {
                    if (item.type == "view") {
                        return '<span class="label label-primary">链接</span>';
                    }
                }
            }, {
                title : '菜单URL',
                field : 'url',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }, {
                title : '菜单标识',
                field : 'key',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '媒体ID',
                field : 'media_id',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '小程序ID',
                field : 'appid',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '小程序路径',
                field : 'pagepath',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '菜单顺序',
                field : 'menuorder',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '按钮顺序',
                field : 'buttonorder',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '按钮类型',
                field : 'ifsub',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter : function(item, index) {
                    if (item.ifsub == "1") {
                        return '<span class="label label-primary">按钮</span>';
                    }
                    if (item.ifsub == "2") {
                        return '<span class="label label-primary">菜单</span>';
                    }
                    return '数据异常';
                }
            }]
    return columns;
};
function getMenuId() {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}
$(function() {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, rootPath + "/weixin/menu_do/list.do", colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentid");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});
