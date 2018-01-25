Vue.directive('select2', {
    inserted: function(el, binding, vnode) {
        let options = binding.value || {};

        $(el).select2(options).on("select2:select", (e)=>{
            // v-model looks for
            // - an event named "change"
            // - a value with property path "$event.target.value"
            el.dispatchEvent(new Event('change', {
                target: e.target
            })); // 说好的双向绑定，竟然不安套路
            console.log("fire change in insert");
        });
    },
    update: function(el, binding, vnode) {
        for (var i = 0; i < vnode.data.directives.length; i++) {
            if (vnode.data.directives[i].name == "model") {
                $(el).val(vnode.data.directives[i].value);
                console.log("new value in update:" + vnode.data.directives[i].value);
            }
        }
        $(el).trigger("change");
        console.log("fire change in update");
    }
});
var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        user: {
            roleids: []
        },
        roleoptions: null
    },
    methods: {
        query: function() {
            vm.reload();
        },
        toupdate: function() {
            var userid = getSelectedRow('usertable', 'id');
            if (userid == null) {
                return;
            }
            
            vm.showList = false;
            vm.title = "编辑用户";
            vm.user = {
                roleids: []
            };
            vm.getUser(userid);
        },
        update: function() {

            $.ajax({
                type: "POST",
                url: rootPath + "/user/user_do/update.do",
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function(data) {
                    alert(data.msg);
                }
            });
        },
        del: function(event) {
            var userids = getSelectedRows('usertable', 'id');
            if (userids == null) {
                return;
            }

            var cbox =  getSelectedRows('usertable', 'id');
            confirm('确定要删除选中的记录？',
            function() {
                $.ajax({
                    type: "POST",
                    url: rootPath + '/user/user_do/remove.do',
                    dataType: "json",
                    data: {
                        ids: userids
                    },
                    success: function(r) {
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
        getUser: function(userid) {

            $.get(rootPath + '/role/role_do/select.do',
            function(r) {
                vm.roleoptions = r.roleselect;
                $.get(rootPath + '/user/user_do/get.do?id=' + userid,
                function(r) {
                    vm.user = r.user;
                });
            });
        },
        reload: function() {
            vm.showList = true;
            layui.use('table',
            function() {
                var table = layui.table;
                table.render({
                    elem: '#usertable' // 选定是那个DIV
                    ,
                    url: '/user/user_do/list.do',
                    cols: [[{
                        type: 'checkbox'
                    },
                    {
                        field: 'id',
                        width: 180,
                        title: 'ID'
                    },
                    {
                        field: 'username',
                        width: 180,
                        title: '用户名'
                    },
                    {
                        field: 'phone',
                        width: 180,
                        title: '手机号'
                    },
                    {
                        field: 'email',
                        width: 180,
                        title: '邮箱'
                    },
                    {
                        field: 'state',
                        title: '账号状态',
                        minWidth: 100,
                        templet: function(data) {
                            if (data.state == "OK") {
                                return "正常";
                            }
                            if (data.state == "lock") {
                                return "<font color='red'>封锁</font>";
                            }
                            if (data.state == "die") {
                                return "未激活";
                            }
                            return "数据异常";
                        }
                    }                   
                    ]],
                    page: true ,// 开启分页                    
                    request:laypagerequest,
                    response: laypageresponse,
                     where:$("#searchForm").serializeJSON()
                });
            });
        },
        download: function() {
            location.href = "/user/user_do/export.do?" + $("#searchForm").serialize();
        }
    }
});

vm.reload();
$('.select2').select2();