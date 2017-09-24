var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        user : {},
        roleoptions : null
    },
    methods : {
        query : function() {
            vm.reload();
        },
        toupdate : function() {
            var userid = getSelectedRow();
            if (userid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "编辑用户";
            vm.getUser(userid);
        },
        update : function() {
            $.ajax({
                type : "POST",
                url : rootPath + "/user/user_do/update.do",
                contentType : "application/json",
                data : JSON.stringify(vm.user),
                success : function(data) {
                    alert(data.msg);
                }
            });
        },
        del : function(event) {
            var userids = getSelectedRows();
            if (userids == null) {
                return;
            }
            var cbox = grid.getSelectedCheckbox();
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + '/user/user_do/remove.do',
                    dataType : "json",
                    data : {
                        ids : userids
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
        getUser : function(userid) {
            $.get(rootPath + '/role/role_do/select.do', function(r) {
                vm.roleoptions = r.roleselect;
                $.get(rootPath + '/user/user_do/get.do?id=' + userid, function(r) {
                    vm.user = r.user;
                });
            });
        },
        reload : function() {
            var searchParams = $("#searchForm").serializeJson();// 初始化传参数
            grid.setOptions({
                data : searchParams
            });
            vm.showList = true;
            grid.loadData();
        },
        download : function() {
            location.href = "/user/user_do/export.do?" + $("#searchForm").serialize();
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
                }, {
                    colkey : "username",
                    name : "用户名"
                }, {
                    colkey : "phone",
                    name : "手机号",
                }, {
                    colkey : "email",
                    name : "电子邮箱",
                }, {
                    colkey : "rolename",
                    name : "所属角色",
                }, {
                    colkey : "state",
                    name : "账号状态",
                    renderData : function(rowindex, data, rowdata, column) {
                        if (data == "OK") {
                            return "正常";
                        }
                        if (data == "lock") {
                            return "<font color='red'>封锁</font>";
                        }
                        if (data == "die") {
                            return "未激活";
                        }
                        return "数据异常";
                    }
                }, {
                    colkey : "createtime",
                    name : "时间",
                    renderData : function(rowindex, data, rowdata, column) {
                        return new Date(data).format("yyyy-MM-dd hh:mm:ss");
                    }
                }, {
                    name : "操作",
                    renderData : function(rowindex, data, rowdata, colkeyn) {
                        return "测试渲染函数";
                    }
                }],
        jsonUrl : rootPath + '/user/user_do/list.do',
        checkbox : true,
        serNumber : true
    });
    // $("#search").click("click", function() {// 绑定查询按扭
    // var searchParams = $("#searchForm").serializeJson();// 初始化传参数
    // grid.setOptions({
    // data : searchParams
    // });
    // });
    // $("#addAccount").click("click", function() {
    // addAccount();
    // });
    // $("#editAccount").click("click", function() {
    // editAccount();
    // });
    // $("#delAccount").click("click", function() {
    // delAccount();
    // });
    // $("#permissions").click("click", function() {
    // permissions();
    // });
    // $("#downFun").click("click", function() {
    // downFun();
    // });
});
// function downFun() {
// // var searchParams1 = $("#searchForm").serializeJson();
// // var s = CommnUtil.ajax("/user/user_do/export.do", searchParams, "json");
// // CommnUtil.ajax("/user/user_do/export.do",searchParams1,"text" );
// // $.post("/user/user_do/export.do",
// // $("#searchForm").serializeJson(),"file");
// location.href = "/user/user_do/export.do?" + $("#searchForm").serialize();
// }
// function editAccount() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox.length > 1 || cbox == "") {
// alert("只能选中一个");
// return;
// }
// pageii = layer.open({
// title : "编辑",
// type : 2,
// maxmin : true, // 开启最大化最小化按钮
// area : [
// "600px", "80%"],
// content : rootPath + '/user/user_do/toupdate.do?id=' + cbox
// });
// }
// function addAccount() {
// pageii = layer.open({
// title : "新增",
// type : 2,
// maxmin : true, // 开启最大化最小化按钮
// area : [
// "600px", "80%"],
// content : rootPath + '/user/user_do/toadd.do'
// });
// }
// function delAccount() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox == "") {
// alert("请选择删除项！！");
// return;
// }
// layer.confirm('是否删除？', function(index) {
// var url = rootPath + '/user/user_do/remove.do';
// var s = CommnUtil.ajax(url, {
// ids : cbox.join(",")
// }, "json");
// if (s.msg == "操作成功") {
// alert('删除成功');
// grid.loadData();
// } else {
// alert('删除失败');
// }
// });
// }
