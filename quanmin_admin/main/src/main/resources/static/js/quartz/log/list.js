var vm = new Vue({
    el : "#rrapp",
    data : {
        showList : true
    },
    methods : {
        reload : function() {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#joblogtable2', // 选定是那个DIV
                    url : rootPath + '/sys/scheduleLog_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            },  {
                                field : 'log_Id',
                                width : 180,
                                title : '任务ID'
                            }, {
                                field : 'bean_Name',
                                width : 100,
                                title : 'Spring Bean'
                            }, {
                                field : 'method_Name',
                                title : '方法名',
                                width : 100
                            }, {
                                field : 'params',
                                title : '参数',
                                width : 100
                            }, {
                                field : 'status',
                                title : '状态',
                                width : 100
                                
                            }, {
                                field : 'remark',
                                title : '备注',
                                width : 100
                            }, {
                                field : 'error',
                                title : '失败信息',
                                width : 100
                            }, {
                                field : 'create_Time',
                                title : '创建时间',
                                width : 100
                            }, {
                                field : 'times',
                                title : '耗时',
                                width : 100
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse
                // ,
                // where : $("#searchForm").serializeJSON()
                });
            });// lay
        }
    }
})
vm.reload();