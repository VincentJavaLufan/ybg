var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        config : {}
    },
    created : function() {
        this.getConfig();
    },
    methods : {
        query : function() {
            vm.reload();
        },
        getConfig : function() {
            $.getJSON(rootPath + "/sys/oss_do/config.do", function(r) {
                vm.config = r.config;
            });
        },
        addConfig : function() {
            vm.showList = false;
            vm.title = "云存储配置";
        },
        saveOrUpdate : function() {
            var url = rootPath + "/sys/oss_do/saveConfig.do";
            $.ajax({
                type : "POST",
                url : url,
                contentType : "application/json",
                data : JSON.stringify(vm.config),
                success : function(r) {
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var ossIds = getSelectedRows('#osstable',"id");
            if (ossIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/sys/oss_do/delete.do",
                    data : {
                        ids : ossIds
                    },
                    success : function(r) {
                        alert(r.msg);
                        vm.reload();
                    }
                });
            });
        },
        reload : function() {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#osstable' // 选定是那个DIV
                    ,
                    url : rootPath + '/sys/oss_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                width : 180,
                                title : 'ID'
                            }, {
                                field : 'url',
                                minWidth : 100,
                                title : '图片',
                                templet : function(data) {
                                    return "<img width='300px' hight='300px' src='" + data.url + "'>";
                                }
                            }, {
                                field : 'createdate',
                                title : '创建时间',
                                minWidth : 100
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