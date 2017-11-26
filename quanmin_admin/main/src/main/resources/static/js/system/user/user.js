  Vue.directive('select2', {
            inserted: function (el, binding, vnode) {
                let options = binding.value || {};

                $(el).select2(options).on("select2:select", (e) => {
                    // v-model looks for
                    //  - an event named "change"
                    //  - a value with property path "$event.target.value"
                    el.dispatchEvent(new Event('change', { target: e.target })); //说好的双向绑定，竟然不安套路
                    console.log("fire change in insert");
                });
            },
            update: function (el, binding, vnode) {
                for (var i = 0; i < vnode.data.directives.length; i++) {
                    if (vnode.data.directives[i].name == "model") {
                        $(el).val(vnode.data.directives[i].value);
                        console.log("new value in update:"+vnode.data.directives[i].value);
                    }
                }
                $(el).trigger("change");
                console.log("fire change in update");
            }
        });
var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        user : {
        	roleids:[]
        },
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
            vm.user={
                	roleids:[]
            };
            vm.getUser(userid);
        },
        update : function() {
        	
        	console.log(vm.user.roleids);
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
    
});

$('.select2').select2();