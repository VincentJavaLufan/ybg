var vm = new Vue({
    el : '#rrapp',
    data : {
        q : {
            beanName : null
        },
        showList : true,
        title : null,
        tags : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.tags = {};
        },
        update : function() {
            var jobId = getSelectedRow('tagtable','id');
            if (jobId == null) {
                return;
            }
            var tagsname = getSelectedRow('tagtable','name');
            vm.title = "修改";
            vm.showList = false;
            vm.tags.id = jobId;
            vm.tags.name = tagsname;
            // $.get(rootPath + "/weixin/tags/info.do?jobId=" + jobId,
            // function(r) {
            // vm.showList = false;
            // vm.title = "修改";
            // vm.tags = r.tags;
            // });
        },
        saveOrUpdate : function(event) {
            var url = vm.tags.id == null ? rootPath + '/weixin/tags/create.do' : rootPath + '/weixin/tags/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.tags),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var jobIds = getSelectedRows('tagtable','id');
            if (jobIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/tags/remove.do",
                    // contentType : "application/json",
                    data : {
                        ids : jobIds
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
                    elem : '#tagtable' // 选定是那个DIV
                    ,
                    url : rootPath + '/weixin/tags/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                width : 180,
                                title : '标签ID'
                            }, {
                                field : 'name',
                                width : 180,
                                title : '标签名称'
                            }, {
                                field : 'count',
                                title : '标签人数',
                                width : 180
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